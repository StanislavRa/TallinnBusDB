/*
package controller;

import db.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TimetableController {


    public void listAllTimetables() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Statement statement = databaseHandler.createStatement();

        try {
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
        Statement statement = databaseHandler.createStatement();

        try {
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
        Statement statement = databaseHandler.createStatement();
        try {
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

}
*/
