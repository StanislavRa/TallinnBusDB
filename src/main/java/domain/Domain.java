package domain;

import controller.BusController;
import controller.DriverController;

public class Domain {

	public static void main(String[] args) {

		DriverController driverController = new DriverController();
		BusController busController = new BusController();

		driverController.listAllDrivers();
		busController.listAllBuses();
		System.out.println("Let's add new Driver");
		driverController.create("Ivanka Trump", "Tall building", "+1002555",25,174);
		driverController.listAllDrivers();
	}
}
