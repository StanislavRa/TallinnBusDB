package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author StanislavR
 */
public class Location implements Service<models.Location> {

    @Override
    public models.Location get(Long id) {
        try {

            String sql = "SELECT * FROM locations where id = ? limit 1";
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
    public ObservableList<models.Location> getAll() {
        ObservableList<models.Location> locations = FXCollections.observableArrayList();
        System.out.println("List of all locations:");

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM locations";
            ResultSet resultSetBuses = statement.executeQuery(sql);

            while (resultSetBuses.next()) {
                models.Location location = extractObjectFromResultSet(resultSetBuses);
                locations.add(location);
                System.out.println(location.toString());
            }

            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return locations;
    }

    @Override
    public boolean save(models.Location location) {
        try {

            String sql = "INSERT locations (stopName)" +
                    " VALUES (?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, location.getStopName());


            int result = ps.executeUpdate();
            System.out.println(location.toString());

            ps.close();

            return result == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(models.Location location) {
        try {
            String sql = "UPDATE locations " +
                    "SET stopName = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, location.getStopName());
            ps.setLong(2, location.getId());

            int result = ps.executeUpdate();

            ps.close();

            return result == 1;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(models.Location location) {
        try {

            String sql = "DELETE FROM locations WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, location.getId());
            int result = ps.executeUpdate();

            ps.close();

            return result == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public models.Location extractObjectFromResultSet(ResultSet rs) throws SQLException {

        models.Location location = new models.Location();
        location.setId(rs.getLong("id"));
        location.setStopName(rs.getString("stopName"));

        return location;
    }

    public List<String> getAllNames() {

        //Connection connection = DatabaseHandler_ol.getInstance().getConnection();

        List<String> locationNames = new ArrayList<>();
        System.out.println("List of all location names:");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM locations");

            while (resultSet.next()) {
                String locationName = resultSet.getString("stopName");
                locationNames.add(locationName);
            }

            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return locationNames;
    }

    public models.Location findLocationByStopName(String stopName) {

        try {

            String sql = "SELECT * FROM locations where stopName = ? limit 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, stopName);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {

                return extractObjectFromResultSet(resultSet);

            } else System.out.println("Location does not exist");

            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
