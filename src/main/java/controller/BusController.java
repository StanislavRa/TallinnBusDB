package controller;

import db.DatabaseHandler;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BusController {

    public void listAllBuses() {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Statement statement = databaseHandler.createStatement();

        System.out.println("List of all buses:");

        try {
            ResultSet resultSetBuses = statement.executeQuery("SELECT * FROM buses");

            while (resultSetBuses.next()) {

                int id = resultSetBuses.getInt("id");
                String busNumber = resultSetBuses.getString("busNumber");
                int driverId = resultSetBuses.getInt("driverId");
                float fuel = resultSetBuses.getFloat("fuel");
                Timestamp createdOn = resultSetBuses.getTimestamp("createdOn");
                Date purchasedOn = resultSetBuses.getDate("purchasedOn");

                System.out.println(id + " " + busNumber + " " + driverId + " " +
                        fuel + " " + createdOn + " " + purchasedOn);
            }
            statement.close();
            databaseHandler.closeConnection();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void findBusByNumber(String busNumber) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Statement statement = databaseHandler.createStatement();

        try {
            ResultSet resultSetBuses = statement.executeQuery(
                    "SELECT * FROM buses where busNumber = '" + busNumber + "' limit 1");

            if (resultSetBuses.next()) {

                int driverId = resultSetBuses.getInt("driverId");
                float fuel = resultSetBuses.getFloat("fuel");
                Timestamp createdOn = resultSetBuses.getTimestamp("createdOn");
                Date purchasedOn = resultSetBuses.getDate("purchasedOn");

                System.out.println("Here is found bus " + busNumber
                        + ": driver id - " + driverId
                        + ", fuel - " + fuel
                        + " created on - " + createdOn
                        + ", purchased on - " + purchasedOn);

            } else System.out.println("Bus does not exist");

            statement.close();
            databaseHandler.closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void create(String busNumber, int driverId, float fuel, String purchasedOn) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Statement statement = databaseHandler.createStatement();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date purchasedOnStr = formatter.parse(purchasedOn);
            java.sql.Date purchasedOnDB = new java.sql.Date(purchasedOnStr.getTime());

            statement.executeUpdate("INSERT INTO buses (busNumber, driverId, fuel, purchasedOn) VALUES" +
                    "('" + busNumber + "', '" + driverId + "', '" + fuel + "', '" + purchasedOnDB + "')");

			System.out.println("Inserted into the table bus " + busNumber
					+ ": driver id - " + driverId
					+ ", fuel - " + fuel
					+ ", purchased on - " + purchasedOn);

            statement.close();
            databaseHandler.closeConnection();

        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }
    }
}
