package controller;

import db.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocationController {

    DatabaseHandler databaseHandler = new DatabaseHandler();
    Statement statement = databaseHandler.createStatement();

    public void listAllLocations() {
        try {
            ResultSet resultSetLocations = statement.executeQuery("SELECT * FROM locations");
            while (resultSetLocations.next()) {
                int locationID = resultSetLocations.getInt("id");
                String stopName = resultSetLocations.getString("stopName");
                System.out.println(locationID + " - " + stopName);
            }
            databaseHandler.closeConnection();
            statement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
