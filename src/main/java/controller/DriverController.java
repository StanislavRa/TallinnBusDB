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

	public void create(String name, String address, String phone, int age, float height) {
		Statement statement = databaseHandler.createStatement();

		if (statement != null) {
			try {
				statement.executeUpdate("INSERT INTO drivers (fullName, address, phone, age, height) VALUES" +
						"('" + name + "', '" + address + "', '" + phone + "', '" + age + "', '"+ height + "')");
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
