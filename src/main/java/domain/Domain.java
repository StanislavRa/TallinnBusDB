package domain;

import controller.BusController;

public class Domain {

	public static void main(String[] args) {

		// Buses operations
		BusController busController = new BusController();
		busController.listAllBuses();
		System.out.println("*****************************************************************************************");
		busController.findBusByNumber("16A");
		System.out.println("*****************************************************************************************");
		busController.create("125",2,5.5f, "13-05-1988");
		System.out.println("*****************************************************************************************");

		// Drivers operations

		// Passengers operations

		// Location operations

	}
}
