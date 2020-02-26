package controller;

import db.DatabaseHandler;
import entity.Bus;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BusController {

	public int listAllBuses() {
		DatabaseHandler databaseHandler = new DatabaseHandler();
		Statement statement = databaseHandler.createStatement();
		int id = -1;
		System.out.println("List of all buses:");
		try {
			if (databaseHandler.getDbConnection() != null) {
			ResultSet resultSetBuses = statement.executeQuery("SELECT * FROM buses");
			while (resultSetBuses.next()) {
				id = resultSetBuses.getInt("id");
				String busNumber = resultSetBuses.getString("busNumber");
				int driverId = resultSetBuses.getInt("driverId");
				float fuel = resultSetBuses.getFloat("fuel");
				Timestamp createdOn = resultSetBuses.getTimestamp("createdOn");
				Date purchasedOn = resultSetBuses.getDate("purchasedOn");

				Bus newBus = new Bus(id, busNumber, driverId, fuel, createdOn, purchasedOn);

				System.out.println(newBus.toString());
			}
			statement.close();
			databaseHandler.closeConnection();
			}
			return id;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return id;
		}
	}

	public int findBusByNumber(String busNumber) {
		DatabaseHandler databaseHandler = new DatabaseHandler();
		Statement statement = databaseHandler.createStatement();
		int id = -1;
		try {
			if (databaseHandler.getDbConnection() != null) {
			ResultSet resultSetBuses = statement.executeQuery(
					"SELECT * FROM buses where busNumber = '" + busNumber + "' limit 1");
			boolean next = resultSetBuses.next();
				if (next) {
					id = resultSetBuses.getInt("id");
					busNumber = resultSetBuses.getString("busNumber");
					int driverId = resultSetBuses.getInt("driverId");
					float fuel = resultSetBuses.getFloat("fuel");
					Timestamp createdOn = resultSetBuses.getTimestamp("createdOn");
					Date purchasedOn = resultSetBuses.getDate("purchasedOn");
					Bus newBus = new Bus(id, busNumber, driverId, fuel, createdOn, purchasedOn);

					System.out.println("Bus with id: " + id);
					System.out.println(newBus.toString());
				}
			}
			return id;
		} catch (Exception ex) {
			ex.printStackTrace();
			return id;
		}
	}

	public void create(String busNumber, int driverId, float fuel, String purchasedOn) {
		DatabaseHandler databaseHandler = new DatabaseHandler();
		Statement statement = databaseHandler.createStatement();

		if (statement != null) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date purchasedOnStr = formatter.parse(purchasedOn);
				java.sql.Date purchasedOnDB = new java.sql.Date(purchasedOnStr.getTime());

				statement.executeUpdate("INSERT INTO buses (busNumber, driverId, fuel, purchasedOn) VALUES" +
						"('" + busNumber + "', '" + driverId + "', '" + fuel + "', '"+ purchasedOnDB + "')");

				System.out.println("Inserted:");
				findBusByNumber(busNumber);

				statement.close();
				databaseHandler.closeConnection();

			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
		}
	}
}
