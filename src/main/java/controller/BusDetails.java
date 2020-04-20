package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Bus;

import java.io.IOException;

public class BusDetails extends General {

    @FXML
    private Label driverFullNameLabel;
    @FXML
    private Label busNumberLabel;
    @FXML
    private Label fuelLabel;
    @FXML
    private Label purchasedOnLabel;

    public void initData(Bus bus) {

        driverFullNameLabel.setText(bus.getDriver().getFullName());
        busNumberLabel.setText(bus.getBusNumber());
        fuelLabel.setText(String.valueOf(bus.getFuel()));
        purchasedOnLabel.setText(bus.getPurchasedOn().toString());
    }

    public void backToBusesButtonPushed(ActionEvent event) throws IOException {

        changeScreen(event, "/buses.fxml");
    }
}
