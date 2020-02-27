package controller;

import db.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverController {
	DatabaseHandler databaseHandler = new DatabaseHandler();

	public void listAllDrivers() {
		Statement statement = databaseHandler.createStatement();

		try {
			ResultSet resultSetDrivers = statement.executeQuery("SELECT * FROM drivers");

			while (resultSetDrivers.next()) {
				int myDriverId = resultSetDrivers.getInt("id");
				String myDriverName = resultSetDrivers.getString("fullName");
				int myDriverAge = resultSetDrivers.getInt("age");
				float myDriverHeight = resultSetDrivers.getFloat("height");
				System.out.println(myDriverId + " " + myDriverName + " " + myDriverAge + " " + myDriverHeight);
			}
			statement.close();
		} catch (SQLException ex) {
				System.out.println(ex);
			}
	}


	public void createDriver (String name, String address, String phoneNr, int age, double height) {
		Statement statement = databaseHandler.createStatement();

		try {
			String newDriver = "INSERT INTO drivers (name, address, phoneNr, age, height) VALUES (\""
					+ name + "\"," + "\"" + address + "\"," + "\"" + phoneNr  + "\"," + age  + "," +  height + ")";
			statement.executeUpdate(newDriver);

			statement.close();
			databaseHandler.closeConnection();

		}catch (SQLException e) {
			System.out.println(e);
		}
	}
}
