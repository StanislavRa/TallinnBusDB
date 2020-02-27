package domain;

import controller.BusController;
import controller.DriverController;
import controller.TimetableController;

public class Domain {

	public static void main(String[] args) {

		DriverController driverController = new DriverController();
		BusController busController = new BusController();
		TimetableController timetableController = new TimetableController();

		//driverController.listAllDrivers();
		busController.listAllBuses();

		System.out.println("Let's add a new Driver");
		driverController.createDriver("Ivanka Trump", "Tall building", "+1002555",25,174);
		driverController.listAllDrivers();

		System.out.println("Let's add a new Location");
		timetableController.createTimetable(1,"Monday", "07:25:00");
		timetableController.listAllTimetables();
	}
}
