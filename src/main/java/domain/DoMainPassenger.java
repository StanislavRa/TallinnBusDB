package domain;

import controller.PassengersController;

public class DoMainPassenger {
    public static void main(String[] args) {
        PassengersController passengersController = new PassengersController();

        passengersController.listAllPassengers();
        System.out.println("=========================================");
        System.out.println("Let's add new Passenger");
//        passengersController.createByUsingStatement("Som Son",
//                "somson@ukr.net",
//                "+10202555",
//                21,
//                1,
//                2,
//                3);
        passengersController.listAllPassengers();
        System.out.println("=========================================");
        System.out.println("Passenger with id #3 information:");
        passengersController.findByPassengerIdByUsingStatement(1);

        passengersController.updatePassengersByUsingPreparedStatement("newEmail@email.com", "no number", 5);
        passengersController.deletePassengersByUsingPreparedStatement(6);
        passengersController.deletePassengersByUsingPreparedStatement(7);
        passengersController.listAllPassengers();
    }
}