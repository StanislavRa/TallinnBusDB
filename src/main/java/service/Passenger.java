package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Passenger implements Service<models.Passenger> {

    @Override
    public models.Passenger get(Long id) {

        try {

            String sql = "SELECT * FROM passengers where id = ? limit 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {

                return extractObjectFromResultSet(resultSet);

            } else System.out.println("Bus does not exist");

            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<models.Passenger> getAll() {

        ObservableList<models.Passenger> passengers = FXCollections.observableArrayList();
        System.out.println("List of all passengers:");

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM passengers";
            ResultSet resultSetBuses = statement.executeQuery(sql);

            while (resultSetBuses.next()) {
                models.Passenger passenger = extractObjectFromResultSet(resultSetBuses);
                passengers.add(passenger);
                System.out.println(passenger.toString());
            }

            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return passengers;
    }

    @Override
    public boolean save(models.Passenger passenger) {

        try {

            String sql = "INSERT passengers (fullName, email, phoneNumber, age, startLocationId, stopLocationId, busId)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, passenger.getFullName());
            ps.setString(2, passenger.getEmail());
            ps.setString(3, passenger.getPhoneNumber());
            ps.setInt(4, passenger.getAge());
            ps.setLong(5, passenger.getStartLocation().getId());
            ps.setLong(6, passenger.getStopLocation().getId());
            ps.setLong(7, passenger.getBus().getId());

            int result = ps.executeUpdate();
            System.out.println(passenger.toString());

            ps.close();

            return result == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(models.Passenger passenger) {

        try {
            String sql = "UPDATE passengers " +
                    "SET fullName = ?, email = ?, phoneNumber = ?, age = ?, startLocationId = ?, stopLocationId = ?, busId = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, passenger.getFullName());
            ps.setString(2, passenger.getEmail());
            ps.setString(3, passenger.getPhoneNumber());
            ps.setInt(4, passenger.getAge());
            ps.setLong(5, passenger.getStartLocation().getId());
            ps.setLong(6, passenger.getStopLocation().getId());
            ps.setLong(7, passenger.getBus().getId());
            ps.setLong(8, passenger.getId());

            int result = ps.executeUpdate();

            ps.close();

            return result == 1;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(models.Passenger passenger) {

        try {

            String sql = "DELETE FROM passengers WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, passenger.getId());
            int result = ps.executeUpdate();

            ps.close();

            return result == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public models.Passenger findPassengerByFullName(String fullName) {

        try {

            String sql = "SELECT * FROM passengers where fullName = ? limit 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fullName);

            ResultSet resultSetBuses = ps.executeQuery();

            if (resultSetBuses.next()) {

                return extractObjectFromResultSet(resultSetBuses);

            } else System.out.println("Bus does not exist");

            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ObservableList<models.Passenger> findPassengersByAge(int age) {

        ObservableList<models.Passenger> passengers = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM passengers where age = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, age);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                models.Passenger passenger = extractObjectFromResultSet(resultSet);
                passengers.add(passenger);
                System.out.println(passenger.toString());
            }

            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return passengers;
    }

    public ObservableList<models.Passenger> findPassengersByBusNumber(String busNumber) {

        ObservableList<models.Passenger> passengers = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM passengers INNER JOIN buses b" +
                    " on passengers.busId = b.id where b.busNumber = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, busNumber);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                models.Passenger passenger = extractObjectFromResultSet(resultSet);
                passengers.add(passenger);
                System.out.println(passenger.toString());
            }

            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return passengers;
    }

    public ObservableList<models.Passenger> findPassengersByStartLocation(String locationName) {

        ObservableList<models.Passenger> passengers = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM passengers INNER JOIN locations l" +
                    " on passengers.startLocationId = l.id where l.stopName = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, locationName);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                models.Passenger passenger = extractObjectFromResultSet(resultSet);
                passengers.add(passenger);
                System.out.println(passenger.toString());
            }

            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return passengers;
    }

    public ObservableList<models.Passenger> findPassengersByStopLocation(String locationName) {

        ObservableList<models.Passenger> passengers = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM passengers INNER JOIN locations l" +
                    " on passengers.stopLocationId = l.id where l.stopName = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, locationName);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                models.Passenger passenger = extractObjectFromResultSet(resultSet);
                passengers.add(passenger);
                System.out.println(passenger.toString());
            }

            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return passengers;
    }

    models.Passenger extractObjectFromResultSet(ResultSet rs) throws SQLException {

        models.Location startLocation = new Location().get(rs.getLong("startLocationId"));
        models.Location stopLocation = new Location().get(rs.getLong("stopLocationId"));
        models.Bus bus = new Bus().get(rs.getLong("busId"));

        models.Passenger passenger = new models.Passenger();
        passenger.setId(rs.getLong("id"));
        passenger.setFullName(rs.getString("fullName"));
        passenger.setEmail(rs.getString("email"));
        passenger.setPhoneNumber(rs.getString("phoneNumber"));
        passenger.setAge(rs.getInt("age"));
        passenger.setStartLocation(startLocation);
        passenger.setStopLocation(stopLocation);
        passenger.setBus(bus);

        return passenger;
    }
}
