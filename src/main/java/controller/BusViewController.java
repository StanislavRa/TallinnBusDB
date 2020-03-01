package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.Bus;

import java.net.URL;
import java.util.ResourceBundle;

public class BusViewController extends BusController implements Initializable {

    private Bus selectedBus;

    @FXML
    private Button homeButton;

    @FXML
    private Label busNumberLabel;

    @FXML
    private Label fuelLabel;

    @FXML
    private Label purchasedOnLabel;

    public void initData(Bus bus) {
        selectedBus = bus;
        busNumberLabel.setText(selectedBus.getBusNumber());
        fuelLabel.setText(String.valueOf(selectedBus.getFuel()));
        purchasedOnLabel.setText(selectedBus.getPurchasedOn().toString());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
