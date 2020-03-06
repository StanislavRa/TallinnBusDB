package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Bus;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BusDetailed extends Controller implements Initializable {

    @FXML
    private Button backToBusesButton;
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

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/buses.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
