package service;

import db.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Bus implements Service<models.Bus> {

    @Override
    public models.Bus get(int id) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        try {

            String sql = "SELECT * FROM buses where id = ? limit 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSetBuses = ps.executeQuery();

            if (resultSetBuses.next()) {

                return extractBusFromResultSet(resultSetBuses);

            } else System.out.println("Bus does not exist");

            ps.close();
            databaseHandler.closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<models.Bus> getAll() {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        ObservableList<models.Bus> buses = FXCollections.observableArrayList();
        System.out.println("List of all buses:");

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM buses";
            ResultSet resultSetBuses = statement.executeQuery(sql);

            while (resultSetBuses.next()) {
                models.Bus bus = extractBusFromResultSet(resultSetBuses);
                buses.add(bus);
                System.out.println(bus.toString());
            }

            statement.close();
            databaseHandler.closeConnection();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return buses;
    }

    @Override
    public boolean save(models.Bus bus) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        try {

            String sql = "INSERT buses (busNumber, driverId, fuel, purchasedOn) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bus.getBusNumber().toString());
            ps.setInt(2, bus.getDriver().getId());
            ps.setFloat(3, bus.getFuel());
            ps.setDate(4, new java.sql.Date(bus.getPurchasedOn().getTime()));

            int i = ps.executeUpdate();

            ps.close();
            databaseHandler.closeConnection();

            System.out.println("Saved bus:\n" + bus.toString());

            return i == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(models.Bus bus) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        try {
            String sql = "UPDATE buses " +
                    "SET busNumber = ?, driverId = ?, fuel = ?, purchasedOn = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, bus.getBusNumber().toString());
            ps.setInt(2, bus.getDriver().getId());
            ps.setFloat(3, bus.getFuel());
            ps.setDate(4, new java.sql.Date(bus.getPurchasedOn().getTime()));
            ps.setInt(5, bus.getId());

            int result = ps.executeUpdate();

            ps.close();
            databaseHandler.closeConnection();

            return result == 1;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(models.Bus bus) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        try {

            String sql = "DELETE FROM buses WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, bus.getId());

            int result = ps.executeUpdate();

            ps.close();
            databaseHandler.closeConnection();

            return result == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public models.Bus findBusByNumber(String busNumber) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        try {

            String sql = "SELECT * FROM buses where busNumber = ? limit 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, busNumber);

            ResultSet resultSetBuses = ps.executeQuery();

            if (resultSetBuses.next()) {

                return extractBusFromResultSet(resultSetBuses);

            } else System.out.println("Bus does not exist");

            ps.close();
            databaseHandler.closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    models.Bus extractBusFromResultSet(ResultSet rs) throws SQLException {

        models.Driver getDriver = new Driver().get(rs.getInt("driverId"));

        models.Bus bus = new models.Bus();
        bus.setId(rs.getInt("id"));
        bus.setBusNumber(rs.getString("busNumber"));
        bus.setDriver(getDriver);
        bus.setFuel(rs.getFloat("fuel"));
        bus.setCreatedOn(rs.getTimestamp("createdOn"));
        bus.setPurchasedOn((rs.getDate("purchasedOn")));

        return bus;
    }
}
