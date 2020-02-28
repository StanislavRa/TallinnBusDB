package domain;

import controller.BusController;
import controller.DriverController;
import controller.PassengersController;
import controller.TimetableController;

public class Domain {

	public static void main(String[] args) {

		DriverController driverController = new DriverController();
		BusController busController = new BusController();
    TimetableController timetableController = new TimetableController();

		PassengersController passengersController = new PassengersController();
    

		driverController.listAllDrivers();
		busController.listAllBuses();
		passengersController.listAllPassengers();
    driverController.findDriverByBusNumber("20B");
		driverController.findDriverByBusNumber("20A");
    timetableController.listAllTimetables();
		timetableController.findTimetableForBusNumber("20A");
// 		System.out.println("Let's add new Driver");
// 		driverController.create("Ivanka Trump", "Tall building", "+1002555",25,174);
// 		driverController.listAllDrivers();
// 		System.out.println("Let's add a new Location");
// 		timetableController.createTimetable(1, 1, "Monday", "07:25:00");



// 		/*
// 		@Oleks
// 		 */
// 		System.out.println("=========================================");
// 		System.out.println("Let's add new Passenger");
// 		passengersController.create("Som Son",
// 								"somson@ukr.net",
// 								"+10202555",
// 								21,
// 								1,
// 								2,
// 								3);
// 		passengersController.listAllPassengers();

// 		System.out.println("=========================================");
// 		System.out.println("Passenger with id #3 information:");
// 		passengersController.findByPassengerID(3);
		/*
		@Oleks
		 */
	}
}
