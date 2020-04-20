package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Timetable implements Service<models.Timetable> {


    @Override
    public models.Timetable get(Long id) {
        try {

            String sql = "SELECT * FROM timetable where id = ? limit 1";
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
    public ObservableList<models.Timetable> getAll() {
        ObservableList<models.Timetable> times = FXCollections.observableArrayList();
        System.out.println("List of all times:");

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM timetable";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                models.Timetable timetable = extractObjectFromResultSet(resultSet);
                times.add(timetable);
                System.out.println(timetable.toString());
            }

            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return times;
    }

    @Override
    public boolean save(models.Timetable timetable) {
        try {

            String sql = "INSERT timetable (weekday, arrivalTime, locationId, busId)" +
                    " VALUES (?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, timetable.getWeekday().getName());
            ps.setTime(2, timetable.getArrivalTime());
            ps.setLong(3, timetable.getLocation().getId());
            ps.setLong(4, timetable.getBus().getId());

            int result = ps.executeUpdate();
            System.out.println(timetable.toString());

            ps.close();

            return result == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(models.Timetable timetable) {
        try {
            String sql = "UPDATE timetable " +
                    "SET weekday = ?, arrivalTime = ?, locationId = ?, busId = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, timetable.getWeekday().getName());
            ps.setTime(2, timetable.getArrivalTime());
            ps.setLong(3, timetable.getLocation().getId());
            ps.setLong(4, timetable.getBus().getId());
            ps.setLong(5, timetable.getId());

            int result = ps.executeUpdate();

            ps.close();

            return result == 1;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(models.Timetable timetable) {
        try {

            String sql = "DELETE FROM timetable WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, timetable.getId());
            int result = ps.executeUpdate();

            ps.close();

            return result == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    models.Timetable extractObjectFromResultSet(ResultSet rs) throws SQLException {

        models.Location location = new Location().get(rs.getLong("locationId"));
        models.Bus bus = new Bus().get(rs.getLong("busId"));

        models.Timetable timetable = new models.Timetable();
        timetable.setId(rs.getLong("id"));
        timetable.setWeekday(Enum.valueOf(models.Timetable.Weekday.class, rs.getString("weekday").toUpperCase()));
        timetable.setArrivalTime(rs.getTime("arrivalTime"));

        timetable.setLocation(location);
        timetable.setBus(bus);

        return timetable;
    }

    public ObservableList<models.Timetable> findByWeekday(String weekday) {

        ObservableList<models.Timetable> times = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM timetable where weekday = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, weekday);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                models.Timetable timetable = extractObjectFromResultSet(resultSet);
                times.add(timetable);
                System.out.println(timetable.toString());
            }

            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return times;
    }

    public ObservableList<models.Timetable> findByStopName(String stopName) {

        ObservableList<models.Timetable> times = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM timetable INNER JOIN locations l on timetable.locationId = l.id" +
                    " where l.stopName = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, stopName);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                models.Timetable timetable = extractObjectFromResultSet(resultSet);
                times.add(timetable);
                System.out.println(timetable.toString());
            }

            ps.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return times;
    }

    public ObservableList<models.Timetable> findByBusNumber(String busNumber) {

        ObservableList<models.Timetable> times = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM timetable INNER JOIN buses b on timetable.busId = b.id " +
                    "where b.busNumber = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, busNumber);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                models.Timetable timetable = extractObjectFromResultSet(resultSet);
                times.add(timetable);
                System.out.println(timetable.toString());
            }

            ps.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return times;

    }
}
