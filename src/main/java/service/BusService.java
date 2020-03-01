package service;

import db.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Bus;

import java.sql.*;

public class BusService {
    public ObservableList<Bus> listAllBuses() {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        ObservableList<Bus> buses = FXCollections.observableArrayList();
        System.out.println("List of all buses:");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSetBuses = statement.executeQuery("SELECT * FROM buses");

            while (resultSetBuses.next()) {
                Bus bus = extractBusFromResultSet(resultSetBuses);
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

    public boolean save(Bus bus) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT buses (busNumber, driverId, fuel, purchasedOn) VALUES (?, ?, ?, ?)");
            ps.setString(1, bus.getBusNumber());
            ps.setInt(2, bus.getDriverId());
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

    public Bus findBusByNumber(String busNumber) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSetBuses = statement.executeQuery(
                    "SELECT * FROM buses where busNumber = '" + busNumber + "' limit 1");

            if (resultSetBuses.next()) {

                return extractBusFromResultSet(resultSetBuses);

            } else System.out.println("Bus does not exist");

            statement.close();
            databaseHandler.closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean delete(Bus bus) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM buses WHERE id=" + bus.getId());
            stmt.close();
            databaseHandler.closeConnection();
            System.out.println("Deleted bus:\n" + bus.toString());
            return i == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    Bus extractBusFromResultSet(ResultSet rs) throws SQLException {
        Bus bus = new Bus();
        bus.setId(rs.getInt("id"));
        bus.setBusNumber(rs.getString("busNumber"));
        bus.setDriverId(rs.getInt("driverId"));
        bus.setFuel(rs.getFloat("fuel"));
        bus.setCreatedOn(rs.getTimestamp("createdOn"));
        bus.setPurchasedOn((rs.getDate("purchasedOn")));

        return bus;
    }
}
