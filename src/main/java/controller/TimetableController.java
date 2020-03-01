package controller;

import db.DatabaseHandler;

import java.sql.*;

public class TimetableController {


    public void listAllTimetables() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        try {
            Statement statement = databaseHandler.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM timetable");

            while (results.next()) {
                int myTimetableId = results.getInt("id");
                String myTimetableWeekDay = results.getString("weekday");
                String myTimetableArrivalTime = results.getString("arrivalTime");
                int myTimetableLocationId = results.getInt("locationId");
                int myTimetableBusId = results.getInt("busId");
                String myTimetableCreationDate = results.getString("createdOn");

                System.out.println(myTimetableId+" "+myTimetableWeekDay+" "+myTimetableArrivalTime+" "+
                        myTimetableBusId+" "+myTimetableLocationId+" "+myTimetableCreationDate);

            }
            statement.close();
            databaseHandler.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }


    public void createTimetable (int busId, int locationId, String weekDay, String arrivalTime) {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        try {
            Statement statement = databaseHandler.createStatement();
            String newTimetable = "INSERT INTO timetable (weekDay, arrivalTime, locationId, busId) VALUES ("
                    + "\"" + weekDay + "\"," + "\"" + arrivalTime + "\"," + locationId + "," + busId + ")";

            statement.executeUpdate(newTimetable);

            statement.close();
            databaseHandler.closeConnection();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void findTimetableForBusNumber(String busNumber) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try {
            Statement statement = databaseHandler.createStatement();
            String findTimetable = "\tSELECT * FROM timetable\n" +
                    "\tINNER JOIN buses on buses.id = timetable.busId\n" +
                    "\tWHERE buses.busNumber = \"" + busNumber + "\"";
            ResultSet busTimetable = statement.executeQuery(findTimetable);
            if (busTimetable.next()){
                String myBusWeekDay = busTimetable.getString("weekday");
                String myBusArrivalTime = busTimetable.getString("arrivalTime");
                int myBusLocationId = busTimetable.getInt("locationId");

                System.out.println("Here is timetable for bus number " + busNumber
                        + ": weekday - " + myBusWeekDay + ", arrival time - " + myBusArrivalTime
                        + " location - " + myBusLocationId);
            } else
                System.out.println("Bus or timetable does not exist");

            statement.close();
            databaseHandler.closeConnection();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertToTimetable(int busId, int locationId, String weekday, String arrivalTime) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        try {
            Connection connector = databaseHandler.getDbConnection();
            String INSERTDTimetableSQL = "INSERT INTO timetable (busId, locationId, weekday, arrivalTime)" +
                    "VALUES (?, ?, ?, ?);";
            PreparedStatement insertTimetable = connector.prepareStatement(INSERTDTimetableSQL);
            insertTimetable.setInt(1, busId);
            insertTimetable.setInt(2, locationId);
            insertTimetable.setString(3, weekday);
            insertTimetable.setString(4, arrivalTime);

            int result = insertTimetable.executeUpdate();

            if (result ==1) {
                System.out.println("timetable added");
            } else {
                System.out.println("timetable not added");
            }
            databaseHandler.closeConnection();
            connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTimetable (int busId, int locationId, String weekday, String arrivalTime) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        try {
            Connection connector = databaseHandler.getDbConnection();
            String UPDATETimetableSQL = "UPDATE timetable SET locationId=?, weekday=?, arrivalTime=? WHERE busId=?";
            PreparedStatement updateTimetable = connector.prepareStatement(UPDATETimetableSQL);
            updateTimetable.setInt(1, locationId);
            updateTimetable.setString(2, weekday);
            updateTimetable.setString(3, arrivalTime);
            updateTimetable.setInt(4, busId);

            int result = updateTimetable.executeUpdate();

            if (result ==1) {
                System.out.println("timetable updated");
            } else {
                System.out.println("timetable not updated");
            }
            databaseHandler.closeConnection();
            connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteTimetable(int id) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        try {
            Connection connector = databaseHandler.getDbConnection();
            String DELETETIMETABLESQL = "DELETE FROM timetable WHERE id=?";
            PreparedStatement deleteTimetable = connector.prepareStatement(DELETETIMETABLESQL);
            deleteTimetable.setInt(1, id);

            int result = deleteTimetable.executeUpdate();

            if (result ==1) {
                System.out.println("timetable deleted");
            } else {
                System.out.println("timetable not deleted");
            }
            databaseHandler.closeConnection();
            connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
