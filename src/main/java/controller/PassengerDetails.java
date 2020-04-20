package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Passenger;

import java.io.IOException;

public class PassengerDetails extends General {

    @FXML
    private Label fullNameLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label startLocationLabel;
    @FXML
    private Label stopLocationLabel;
    @FXML
    private Label busNumberLabel;

    public void initData(Passenger passenger) {

        fullNameLabel.setText(passenger.getFullName());
        phoneNumberLabel.setText(passenger.getPhoneNumber());
        ageLabel.setText(String.valueOf(passenger.getAge()));
        emailLabel.setText(passenger.getEmail());
        startLocationLabel.setText(passenger.getStartLocation().getStopName());
        stopLocationLabel.setText(passenger.getStopLocation().getStopName());
        busNumberLabel.setText(passenger.getBus().getBusNumber());
    }

    @FXML
    void backToPassengersButtonPushed(ActionEvent event) throws IOException {

        changeScreen(event, "/passengers.fxml");
    }
}
