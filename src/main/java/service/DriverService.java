/*
package controller;

import db.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverController {
	DatabaseHandler databaseHandler = new DatabaseHandler();

	public void listAllDrivers() {


		try {
			Statement statement = databaseHandler.createStatement();
			ResultSet resultSetDrivers = statement.executeQuery("SELECT * FROM drivers");

			while (resultSetDrivers.next()) {
				int myDriverId = resultSetDrivers.getInt("id");
				String myDriverName = resultSetDrivers.getString("fullName");
				int myDriverAge = resultSetDrivers.getInt("age");
				float myDriverHeight = resultSetDrivers.getFloat("height");

				System.out.println(myDriverId + " " + myDriverName + " " + myDriverAge + " " + myDriverHeight);
			}
			databaseHandler.closeConnection();
			statement.close();
			databaseHandler.closeConnection();
		} catch (SQLException ex) {

				System.out.println(ex);
				ex.printStackTrace();
			}
	}


	public void createDriver (String fullName, String address, String phone, int age, double height) {
		Statement statement = databaseHandler.createStatement();
		Statement statement1 = databaseHandler.createStatement();

		try {
			String newDriver = "INSERT INTO drivers (fullName, address, phone, age, height) VALUES (\""
					+ fullName + "\"," + "\"" + address + "\"," + "\"" + phone  + "\"," + age  + "," +  height + ")";
			statement1.executeUpdate(newDriver);

			statement1.close();
			databaseHandler.closeConnection();
		}catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void findDriverByBusNumber(String busNumber) {
		Statement statement = databaseHandler.createStatement();
		Statement statement2 = databaseHandler.createStatement();
		try {
			String findDriver = "\tSELECT drivers.fullName FROM drivers\n" +
					"\tINNER JOIN buses on buses.driverId = drivers.id\n" +
					"\tWHERE buses.busNumber = \"" + busNumber + "\"";
			ResultSet findDriverResults = statement2.executeQuery(findDriver);
			if (findDriverResults.next()){
				String myDriverName = findDriverResults.getString("fullName");
				System.out.println("Driver " + myDriverName + " rides bus number " + busNumber);
			} else
				System.out.println("Bus or driver does not exist");

			statement2.close();
			databaseHandler.closeConnection();

		}catch (SQLException e) {
			System.out.println(e);
		}
	}
}
*/
