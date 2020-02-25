package domain;

import controller.BusController;
import controller.DriverController;
import controller.PassengersController;

public class Domain {

	public static void main(String[] args) {

		DriverController driverController = new DriverController();
		BusController busController = new BusController();
		PassengersController passengersController = new PassengersController();

		driverController.listAllDrivers();
		busController.listAllBuses();
		passengersController.listAllPassengers();
		System.out.println("Let's add new Driver");
		driverController.create("Ivanka Trump", "Tall building", "+1002555",25,174);
		driverController.listAllDrivers();



		/*
		@Oleks
		 */
		System.out.println("=========================================");
		System.out.println("Let's add new Passenger");
		passengersController.create("Som Son",
								"somson@ukr.net",
								"+10202555",
								21,
								1,
								2,
								3);
		passengersController.listAllPassengers();

		System.out.println("=========================================");
		System.out.println("Passenger with id #3 information:");
		passengersController.findByPassengerID(3);
		/*
		@Oleks
		 */
	}
}
