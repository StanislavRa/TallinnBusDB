package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home extends General implements Initializable {

    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void busesButtonPushed(ActionEvent event) throws IOException {

        changeScreen(event, "/buses.fxml");
    }

    public void driversButtonPushed(ActionEvent event) throws IOException {

        changeScreen(event, "/drivers.fxml");
    }

    public void passengersButtonPushed(ActionEvent event) throws IOException {

        changeScreen(event, "/passengers.fxml");
    }

    public void timetableButtonPushed(ActionEvent event) throws IOException {

        changeScreen(event, "/timetable.fxml");
    }

    public void developersButtonPushed(ActionEvent event) throws IOException {

        Developers controller = changeScreen(event, "/developers.fxml").getController();
        controller.setDeveloperNameLabel("Stanislav");
        controller.setDeveloperSurnameLabel("Ratšinski");
        controller.setDeveloperEmailLabel("s.ratsinski@hotmail.com");
    }

    public void exitButtonPushed() {

        // get a handle to the stage
        Stage stage = (Stage) exitButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
