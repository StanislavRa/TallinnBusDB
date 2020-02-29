package controller;

import db.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class LocationController {

    DatabaseHandler databaseHandler = new DatabaseHandler();

    public void listAllLocations() {
        Statement statement = databaseHandler.createStatement();

        try {
            ResultSet resultSetLocations = statement.executeQuery("SELECT * FROM locations");

            while (resultSetLocations.next()) {
                int locationID = resultSetLocations.getInt("id");
                String stopName = resultSetLocations.getString("stopName");
                Timestamp createdOn = resultSetLocations.getTimestamp("createdOn");
                System.out.println(locationID + " - " + stopName + " - " + createdOn);
            }
            statement.close();
            databaseHandler.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void findByLocationID(int id) {
        Statement statement = databaseHandler.createStatement();

        try {
            ResultSet resultSetLocation = statement.executeQuery("SELECT * FROM locations WHERE id = '" + id + "' ");

            if (resultSetLocation.next()) {
                int locationID = resultSetLocation.getInt("id");
                String stopName = resultSetLocation.getString("stopName");
                Timestamp createdOn = resultSetLocation.getTimestamp("createdOn");

                System.out.println(locationID + " - " + stopName + " - " + createdOn);
            } else {
                System.out.println("Location ID "+ id +" does not exist!");
            }
            statement.close();
            databaseHandler.closeConnection();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}