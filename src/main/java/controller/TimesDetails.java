package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Timetable;

import java.io.IOException;

/**
 * @author StanislavR
 */
public class TimesDetails extends General {

    @FXML
    private Label weekdayLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label busNumberLabel;
    @FXML
    private Label arrivalTimeLabel;

    public void initData(Timetable timetable) {

        weekdayLabel.setText(timetable.getWeekday().toString());
        locationLabel.setText(timetable.getLocation().getStopName());
        busNumberLabel.setText(timetable.getBus().getBusNumber());
        arrivalTimeLabel.setText(timetable.getArrivalTime().toString());
    }

    @FXML
    void backToTimesButtonPushed(ActionEvent event) throws IOException {

        changeScreen(event, "/timetable.fxml");
    }
}
