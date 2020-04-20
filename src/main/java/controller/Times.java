package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Timetable;
import util.Validator;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

/**
 * @author StanislavR
 */
public class Times extends Models<Timetable> implements Initializable {

    @FXML
    private TableColumn<Timetable, String> weekdayColumn;
    @FXML
    private TableColumn<Timetable, Time> arrivalTimeColumn;
    @FXML
    private TableColumn<Timetable, String> locationColumn;
    @FXML
    private TableColumn<Timetable, String> busNumberColumn;
    @FXML
    private ChoiceBox<String> weekdayChoiceBox;
    @FXML
    private TextField arrivalTimeTextField;
    @FXML
    private ChoiceBox<String> locationChoiceBox;
    @FXML
    private ChoiceBox<String> busNumberChoiceBox;
    @FXML
    private RadioButton findByWeekdayRadioButton;
    @FXML
    private RadioButton findByLocationRadioButton;
    @FXML
    private RadioButton findByBusNumberRadioButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpTableColumns();
        //load data
        tableView.setItems(timetableService.getAll());

        weekdayChoiceBox.getItems().addAll(parser.getNames(Timetable.Weekday.class));
        locationChoiceBox.getItems().addAll(locationService.getAllNames());
        busNumberChoiceBox.getItems().addAll(busService.getAllNames());
    }

    @Override
    public void setUpTableColumns() {

        weekdayColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getWeekday().getName()));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        locationColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getLocation().getStopName()));
        busNumberColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getBus().getBusNumber()));
    }

    @Override
    public Timetable getObjectFromFields() {

        Timetable timetable = new Timetable();
        timetable.setArrivalTime(parser.getTimeFromString(arrivalTimeTextField.getText()));
        timetable.setWeekday(Enum.valueOf(Timetable.Weekday.class, weekdayChoiceBox.getValue()));
        timetable.setLocation(locationService.findLocationByStopName(locationChoiceBox.getValue()));
        timetable.setBus(busService.findBusByNumber(busNumberChoiceBox.getValue()));

        return timetable;
    }

    @Override
    public void showAllButtonPushed(ActionEvent event) {
        tableView.setItems(timetableService.getAll());
    }

    @Override
    public void addButtonPushed() {

        if (checkAllUserInput()) {
            boolean result = timetableService.save(getObjectFromFields());
            if (result) {
                tableView.setItems(timetableService.getAll());
            } else {
                System.out.println("was not not saved to a database");
            }
        }
    }

    @Override
    public void updateButtonPushed() {
        Timetable changeableTimetable = getObjectFromSelectedTableRow();

        if (!arrivalTimeTextField.getText().isEmpty())
            changeableTimetable.setArrivalTime(parser.getTimeFromString(arrivalTimeTextField.getText()));
        if (locationService.findLocationByStopName(locationChoiceBox.getValue()) != null)
            changeableTimetable.setLocation(locationService.findLocationByStopName(locationChoiceBox.getValue()));
        if (busService.findBusByNumber(busNumberChoiceBox.getValue()) != null)
            changeableTimetable.setBus(busService.findBusByNumber(busNumberChoiceBox.getValue()));
        if (weekdayChoiceBox.getValue() != null)
            changeableTimetable.setWeekday(Enum.valueOf(Timetable.Weekday.class, weekdayChoiceBox.getValue()));

        System.out.println(timetableService.update(changeableTimetable));

        tableView.setItems(timetableService.getAll());
        tableView.refresh();
    }

    @Override
    public void deleteButtonPushed() {

        ObservableList<Timetable> selectedRows, allTimes;
        allTimes = tableView.getItems();

        //this gives us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Bus objects from the table
        for (Timetable timetable : selectedRows) {
            allTimes.remove(timetable);
            System.out.println(timetableService.delete(timetable));
        }
    }

    @Override
    public void findButtonPushed() {

        if (findByWeekdayRadioButton.isSelected() &&
                Validator.isChoiceBoxHasValue(weekdayChoiceBox,"Select weekday"))
            tableView.setItems(timetableService.findByWeekday(weekdayChoiceBox.getValue()));
        if (findByLocationRadioButton.isSelected() &&
                Validator.isChoiceBoxHasValue(locationChoiceBox,"Select location"))
            tableView.setItems(timetableService.findByStopName(locationChoiceBox.getValue()));
        if (findByBusNumberRadioButton.isSelected() &&
                Validator.isChoiceBoxHasValue(busNumberChoiceBox,"Select bus number"))
            tableView.setItems(timetableService.findByBusNumber(busNumberChoiceBox.getValue()));
    }

    @Override
    public void detailsButtonPushed(ActionEvent event) throws IOException {
        //access the controller and call a method
        TimesDetails controller = changeScreen(event, "/timetableView.fxml").getController();
        controller.initData(getObjectFromSelectedTableRow());

    }
    private boolean checkAllUserInput() {
        return  Validator.isChoiceBoxHasValue(weekdayChoiceBox,"Select weekday") &&
                Validator.isTextFieldEmpty(arrivalTimeTextField, "Type arrival time") &&
                Validator.stringMatcherValidation(arrivalTimeTextField.getText(), "([01]?[0-9]|2[0-3]):[0-5][0-9]",
                        "Make sure that time format is xx:xx")  &&
                Validator.isChoiceBoxHasValue(locationChoiceBox,"Select location") &&
                Validator.isChoiceBoxHasValue(busNumberChoiceBox,"Select bus number");

    }
}
