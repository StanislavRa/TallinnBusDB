package service;

import db.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Driver implements Service<models.Driver> {

    @Override
    public models.Driver get(int id) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        try {
            String sql = "SELECT * FROM drivers where id = ? limit 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSetDrivers = ps.executeQuery();

            if (resultSetDrivers.next()) {

                return extractDriverFromResultSet(resultSetDrivers);

            } else System.out.println("Driver does not exist");

            ps.close();
            databaseHandler.closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<models.Driver> getAll() {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        ObservableList<models.Driver> drivers = FXCollections.observableArrayList();
        System.out.println("List of all drivers:");

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM drivers";
            ResultSet resultSetDrivers = statement.executeQuery(sql);

            while (resultSetDrivers.next()) {
                models.Driver driver = extractDriverFromResultSet(resultSetDrivers);
                drivers.add(driver);
                System.out.println(driver.toString());
            }

            statement.close();
            databaseHandler.closeConnection();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return drivers;
    }


    @Override
    public boolean save(models.Driver driver) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        try {
            String sql = "INSERT drivers (fullName, address, phone, age, height) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, driver.getFullName());
            ps.setString(2, driver.getAddress());
            ps.setString(3, driver.getPhone());
            ps.setInt(4, driver.getAge());
            ps.setFloat(5, driver.getHeight());

            int i = ps.executeUpdate();

            ps.close();
            databaseHandler.closeConnection();

            System.out.println("Saved driver:\n" + driver.toString());

            return i == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(models.Driver driver) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        try {
            String sql = "UPDATE drivers" +
                    " SET fullName = ?, address = ?, phone = ?, age = ?, height = ?" +
                    " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, driver.getFullName());
            ps.setString(2, driver.getAddress());
            ps.setString(3, driver.getPhone());
            ps.setInt(4, driver.getAge());
            ps.setFloat(5, driver.getHeight());
            ps.setFloat(6, driver.getId());

            int i = ps.executeUpdate();

            ps.close();
            databaseHandler.closeConnection();

            System.out.println("Updated driver:\n" + driver.toString());

            return i == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(models.Driver driver) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        try {

            String sql = "DELETE FROM drivers WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, driver.getId());

            int result = ps.executeUpdate();

            ps.close();
            databaseHandler.closeConnection();

            return result == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public models.Driver findDriverByFullName(String driverFullName) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        try {

            String sql = "SELECT * FROM drivers where fullName = ? limit 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, driverFullName);

            ResultSet resultSetDrivers = ps.executeQuery();

            if (resultSetDrivers.next()) {

                return extractDriverFromResultSet(resultSetDrivers);

            } else System.out.println("Driver does not exist");

            ps.close();
            databaseHandler.closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<String> getAllNames() {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        List<String> driverNames = new ArrayList<>();
        System.out.println("List of all driver names:");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSetDrivers = statement.executeQuery("SELECT * FROM drivers");

            while (resultSetDrivers.next()) {
                String driverName = resultSetDrivers.getString("fullName");
                driverNames.add(driverName);
            }

            statement.close();
            databaseHandler.closeConnection();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return driverNames;
    }

    private models.Driver extractDriverFromResultSet(ResultSet rs) throws SQLException {

        models.Driver driver = new models.Driver();
        driver.setId(rs.getInt("id"));
        driver.setFullName(rs.getString("fullName"));
        driver.setAddress(rs.getString("address"));
        driver.setPhone(rs.getString("phone"));
        driver.setAge(rs.getInt("age"));
        driver.setHeight(rs.getFloat("height"));
        driver.setCreatedOn(rs.getTimestamp("createdOn"));

        return driver;
    }
}

