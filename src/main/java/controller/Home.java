package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home extends Controller implements Initializable {

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
    }

    public void findBusButtonPushed(ActionEvent event) throws IOException {

        changeScreen(event, "/buses.fxml");
    }

    public void exitButtonPushed() {

        // get a handle to the stage
        Stage stage = (Stage) exitButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
