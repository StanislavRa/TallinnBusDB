package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Driver;

import java.io.IOException;

public class DriverDetails extends General {

    @FXML
    private Label fullNameLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label heightLabel;
    @FXML
    void backToDriversButtonPushed(ActionEvent event) throws IOException {

        changeScreen(event, "/drivers.fxml");
    }

    public void initData(Driver driver) {

        fullNameLabel.setText(driver.getFullName());
        phoneLabel.setText(driver.getPhone());
        ageLabel.setText(String.valueOf(driver.getAge()));
        addressLabel.setText(driver.getAddress());
        heightLabel.setText(String.valueOf(driver.getHeight()));
    }
}
