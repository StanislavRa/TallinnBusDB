package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * @author StanislavR
 */
public class Developers extends General {
    @FXML
    private Button backToHomeButton;
    @FXML
    private Label developerNameLabel;
    @FXML
    private Label developerEmailLabel;
    @FXML
    private Label developerSurnameLabel;
    @FXML
    void backToHomeButtonPushed(ActionEvent event) throws IOException {
        changeScreen(event, "/home.fxml");

    }

    public void setDeveloperNameLabel(String developerName) {
        this.developerNameLabel = new Label(developerName);
    }

    public void setDeveloperEmailLabel(String developerEmail) {
        this.developerEmailLabel = new Label(developerEmail);
    }

    public void setDeveloperSurnameLabel(String developerSurname) {
        this.developerSurnameLabel = new Label(developerSurname);
    }
}
