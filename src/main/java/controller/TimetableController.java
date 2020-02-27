package controller;

import db.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class TimetableController {
    DatabaseHandler databaseHandler = new DatabaseHandler();

    public void listAllTimetables() {
        Statement statement = databaseHandler.createStatement();

        try {
            ResultSet results = statement.executeQuery("SELECT * FROM timetable");

            while (results.next()) {
                int myTimetableId = results.getInt("id");
                int myTimetableLocationId = results.getInt("locationId");
                String myTimetableWeekDay = results.getString("weekday");
                String myTimetableArrivalTime = results.getString("arrivalTime");

                System.out.println(myTimetableId+" "+myTimetableLocationId+" "+myTimetableWeekDay+" "+myTimetableArrivalTime);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void createTimetable (int locationId, String weekDay, String arrivalTime) {
        Statement statement = databaseHandler.createStatement();

        try {
            String newTimetable = "INSERT INTO timetable (locationId, weekDay, arrivalTime) VALUES ("
                    + locationId + "," + "\"" + weekDay + "\"," + "\"" + arrivalTime + "\")";
            statement.executeUpdate(newTimetable);

            statement.close();
            databaseHandler.closeConnection();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }

}
