package controller;

import db.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TimetableController {
    DatabaseHandler databaseHandler = new DatabaseHandler();

    public void listAllTimetables() {
        Statement statement = databaseHandler.createStatement();

        try {
            ResultSet results = statement.executeQuery("SELECT * FROM timetable");

            while (results.next()) {
                int myTimetableId = results.getInt("id");
                int myTimetableBusId = results.getInt("busId");
                int myTimetableLocationId = results.getInt("locationId");
                String myTimetableWeekDay = results.getString("weekday");
                String myTimetableArrivalTime = results.getString("arrivalTime");

                System.out.println(myTimetableId+" "+myTimetableBusId+" "+myTimetableLocationId+" "+myTimetableWeekDay+" "+myTimetableArrivalTime);

            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }


    public void createTimetable (int busId, int locationId, String weekDay, String arrivalTime) {
        Statement statement = databaseHandler.createStatement();

        try {
            String newTimetable = "INSERT INTO timetable (locationId, weekDay, arrivalTime) VALUES ("
                    + busId + "," + locationId + "," + "\"" + weekDay + "\"," + "\"" + arrivalTime + "\")";

            statement.executeUpdate(newTimetable);

            statement.close();
            databaseHandler.closeConnection();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void findTimetableForBusNumber(int busNumber) {
        Statement statement = databaseHandler.createStatement();
        try {
            String findTimetable = "\tSELECT * FROM timetable\n" +
                    "\tINNER JOIN buses on buses.driverId = drivers.id\n" +
                    "\tWHERE buses.busNumber = " + busNumber;
            ResultSet busTimetable = statement.executeQuery(findTimetable);
            if (busTimetable != null){
                System.out.println("Here is timetable for bus number " + busNumber);
            } else
                System.out.println("Bus or timetable does not exist");

            statement.close();
            databaseHandler.closeConnection();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }

}
