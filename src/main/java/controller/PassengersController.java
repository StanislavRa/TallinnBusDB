package controller;

import db.DatabaseHandler;

import java.sql.*;

public class PassengersController  {


    public void listAllPassengers() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
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
            databaseHandler.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void createByUsingStatement(String name,
                       String email,
                       String phoneNumber,
                       int age,
                       int startLocationId,
                       int stopLocationId,
                       int busId) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
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

    public void findByPassengerIdByUsingStatement(int id) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
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
    public void insertToPassengersByUsingPreparedStatement(String name,
                       String email,
                       String phoneNumber,
                       int age,
                       int startLocationId,
                       int stopLocationId,
                       int busId) {

            try {
                DatabaseHandler databaseHandler = new DatabaseHandler();
                Connection connector = databaseHandler.getDbConnection();
                String INSERTPASSENGERSQL = "INSERT INTO passengers " +
                        "(fullName," +
                        " email," +
                        " phoneNumber," +
                        " age," +
                        " startLocationId," +
                        " stopLocationId," +
                        " busId)" +
                        " VALUES (? ,?, ?, ?, ?, ?, ?)";
                PreparedStatement insertPassenger = connector.prepareStatement(INSERTPASSENGERSQL);
                insertPassenger.setString(1, name);
                insertPassenger.setString(2, email);
                insertPassenger.setString(3, phoneNumber);
                insertPassenger.setInt(4, age);
                insertPassenger.setInt(5, startLocationId);
                insertPassenger.setInt(6, stopLocationId);
                insertPassenger.setInt(7, busId);

                int result = insertPassenger.executeUpdate();

                if (result==1) System.out.println("passenger is added");
                else System.out.println("ERROR - passenger is NOT added");
            databaseHandler.closeConnection();
            connector.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
        }
    }

    public void updatePassengersByUsingPreparedStatement(String email,
                                                         String phoneNumber,
                                                         int id) {

        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            Connection connector = databaseHandler.getDbConnection();
            String UPDATEPASSENGERSQL = "UPDATE passengers " +
                                        "SET email = ?, phoneNumber = ? " +
                                        "WHERE id = ?";
            PreparedStatement updatePassenger = connector.prepareStatement(UPDATEPASSENGERSQL);
            updatePassenger.setString(1, email);
            updatePassenger.setString(2, phoneNumber);
            updatePassenger.setInt(3, id);

            int result = updatePassenger.executeUpdate();

            if (result==1) System.out.println("passenger is updated");
            else System.out.println("ERROR - passenger is NOT updated");
            databaseHandler.closeConnection();
            connector.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void deletePassengersByUsingPreparedStatement(int id) {

        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            Connection connector = databaseHandler.getDbConnection();
            String DELETEPASSENGERSQL = "DELETE FROM passengers " +
                    "WHERE id = ?";
            PreparedStatement deletePassenger = connector.prepareStatement(DELETEPASSENGERSQL);

            deletePassenger.setInt(1, id);

            int result = deletePassenger.executeUpdate();

            if (result==1) System.out.println("passenger is deleted");
            else System.out.println("ERROR - passenger is NOT deleted");
            databaseHandler.closeConnection();
            connector.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
