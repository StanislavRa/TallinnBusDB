package controller;

import db.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BusController {
	DatabaseHandler databaseHandler = new DatabaseHandler();
	Statement statement = databaseHandler.createStatement();

	public void listAllBuses() {
		try {
			ResultSet resultSetBuses = statement.executeQuery("SELECT * FROM buses");
			while (resultSetBuses.next()) {
				int myDriverId = resultSetBuses.getInt("id");
				String myBusNumber = resultSetBuses.getString("busNumber");
				float myBusFuel = resultSetBuses.getFloat("fuel");
				System.out.println(myDriverId + " " + myBusNumber + " " + myBusFuel);
			}
			databaseHandler.closeConnection();

		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
