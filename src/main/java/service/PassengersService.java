/*
package controller;

import db.DatabaseHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class PassengersController extends DatabaseHandler {
        DatabaseHandler databaseHandler = new DatabaseHandler();

    public void listAllPassengers() {
        Statement statement = databaseHandler.createStatement();
        try {
            ResultSet resultSetPassengers = statement.executeQuery("SELECT * FROM passengers");
            System.out.println("=========================================");
            System.out.println("Passengers table");

            while (resultSetPassengers.next()) {
                int myPassengerId = resultSetPassengers.getInt("id");
                String myPassengerFullName = resultSetPassengers.getString("fullName");
                String myPassengerEmail = resultSetPassengers.getString("email");
                String myPassengerPhoneNumber = resultSetPassengers.getString("phoneNumber");
                int myPassengerAge = resultSetPassengers.getInt("age");
                int myPassengerStartLocationId = resultSetPassengers.getInt("startLocationId");
                int myPassengerStopLocationId = resultSetPassengers.getInt("stopLocationId");
                int myPassengerBusID = resultSetPassengers.getInt("busId");
                Timestamp timestamp = resultSetPassengers.getTimestamp("createdOn");

                System.out.println(myPassengerId
                        + " - " + myPassengerFullName
                        + " - " + myPassengerEmail
                        + " - " + myPassengerPhoneNumber
                        + " - " + myPassengerAge
                        + " - " + myPassengerStartLocationId
                        + " - " + myPassengerStopLocationId
                        + " - " + myPassengerBusID
                        + " - " + timestamp);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void create(String name,
                       String email,
                       String phoneNumber,
                       int age,
                       int startLocationId,
                       int stopLocationId,
                       int busId) {
        Statement statement = databaseHandler.createStatement();

        if (statement != null) {
            try {
                statement.executeUpdate("INSERT INTO passengers " +
                        "(fullName," +
                        " email," +
                        " phoneNumber," +
                        " age," +
                        " startLocationId," +
                        " stopLocationId," +
                        " busId)" +
                        " VALUES" +
                        "('" + name +
                        "', '" + email +
                        "', '" + phoneNumber +
                        "', '" + age +
                        "', '" + startLocationId +
                        "', '" + stopLocationId +
                        "', '" + busId + "')");
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void findByPassengerID(int id) {
        Statement statement = databaseHandler.createStatement();
        try {

            ResultSet resultSetPassengers = statement.executeQuery("SELECT * FROM passengers WHERE id = " + id +";");
            System.out.println("=========================================");
            System.out.println("Passenger by id # " + id + " information from 'passengers' table");

            if (resultSetPassengers.next()){
                int myPassengerId = resultSetPassengers.getInt("id");
                String myPassengerFullName = resultSetPassengers.getString("fullName");
                String myPassengerEmail = resultSetPassengers.getString("email");
                String myPassengerPhoneNumber = resultSetPassengers.getString("phoneNumber");
                int myPassengerAge = resultSetPassengers.getInt("age");
                int myPassengerStartLocationId = resultSetPassengers.getInt("startLocationId");
                int myPassengerStopLocationId = resultSetPassengers.getInt("stopLocationId");
                int myPassengerBusID = resultSetPassengers.getInt("busId");
                Timestamp timestamp = resultSetPassengers.getTimestamp("createdOn");

                    System.out.println(myPassengerId
                            + " - " + myPassengerFullName
                            + " - " + myPassengerEmail
                            + " - " + myPassengerPhoneNumber
                            + " - " + myPassengerAge
                            + " - " + myPassengerStartLocationId
                            + " - " + myPassengerStopLocationId
                            + " - " + myPassengerBusID
                            + " - " + timestamp);

                } else {
                    System.out.println("ID value is out of the list");
                }
            statement.close();
            databaseHandler.closeConnection();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
*/
