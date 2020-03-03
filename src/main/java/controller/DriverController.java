package controller;

import db.DatabaseHandler;

import java.sql.*;

public class DriverController {

	public void listAllDrivers() {
		DatabaseHandler databaseHandler = new DatabaseHandler();

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
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


	public void createDriver (String fullName, String address, String phone, int age, double height) {
		DatabaseHandler databaseHandler = new DatabaseHandler();

		try {
			Statement statement1 = databaseHandler.createStatement();
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
		DatabaseHandler databaseHandler = new DatabaseHandler();
		try {
			Statement statement2 = databaseHandler.createStatement();
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

	public void insertToDrivers(String name, String address, String phoneNr, int age, double height) {

		DatabaseHandler databaseHandler = new DatabaseHandler();
		try {
			Connection connector = databaseHandler.getDbConnection();
			String INSERTDRIVERSQL = "INSERT INTO drivers (fullName, address, phone, age, height)" +
					"VALUES (?, ?, ?, ?, ?);";
			PreparedStatement insertDriver = connector.prepareStatement(INSERTDRIVERSQL);
			insertDriver.setString(1, name);
			insertDriver.setString(2,address);
			insertDriver.setString(3, phoneNr);
			insertDriver.setInt(4,age);
			insertDriver.setDouble(5, height);

			int result = insertDriver.executeUpdate();

			if (result ==1) {
				System.out.println("driver added");
			} else {
				System.out.println("driver not added");
			}
			databaseHandler.closeConnection();
			connector.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDrivers(String address, String phoneNr, int id) {

		DatabaseHandler databaseHandler = new DatabaseHandler();
		try {
			Connection connector = databaseHandler.getDbConnection();
			String UPDATEDRIVERSQL = "UPDATE drivers SET address=?, phone=? WHERE id=?";
			PreparedStatement updateDriver = connector.prepareStatement(UPDATEDRIVERSQL);
			updateDriver.setString(1,address);
			updateDriver.setString(2, phoneNr);
			updateDriver.setDouble(3, id);

			int result = updateDriver.executeUpdate();

			if (result ==1) {
				System.out.println("driver updated");
			} else {
				System.out.println("driver not updated");
			}
			databaseHandler.closeConnection();
			connector.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteDrivers(int id) {

		DatabaseHandler databaseHandler = new DatabaseHandler();
		try {
			Connection connector = databaseHandler.getDbConnection();
			String DELETEDRIVERSQL = "DELETE FROM drivers WHERE id=?";
			PreparedStatement updateDriver = connector.prepareStatement(DELETEDRIVERSQL);
			updateDriver.setDouble(1, id);

			int result = updateDriver.executeUpdate();

			if (result ==1) {
				System.out.println("driver deleted");
			} else {
				System.out.println("driver not deleted");
			}
			databaseHandler.closeConnection();
			connector.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
