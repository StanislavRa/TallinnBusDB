package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends Controller implements Initializable {

    @FXML
    private Button busesButton;
    @FXML
    private Button driversButton;
    @FXML
    private Button passengersButton;
    @FXML
    private Button timetableButton;
    @FXML
    private Button developersButton;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        busesButton.setOnAction(event -> {
            openNewScene("/buses.fxml", busesButton);
        });

    }
}
