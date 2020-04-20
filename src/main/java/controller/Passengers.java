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
import models.Passenger;
import util.Validator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Passengers extends Models<models.Passenger> implements Initializable {

    @FXML
    private TableColumn<Passenger, String> fullNameColumn;
    @FXML
    private TableColumn<Passenger, String> emailColumn;
    @FXML
    private TableColumn<Passenger, String> phoneNumberColumn;
    @FXML
    private TableColumn<Passenger, String> ageColumn;
    @FXML
    private TableColumn<Passenger, String> startLocationColumn;
    @FXML
    private TableColumn<Passenger, String> stopLocationColumn;
    @FXML
    private TableColumn<Passenger, String> busNumberColumn;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private ChoiceBox<String> startLocationChoiceBox;
    @FXML
    private ChoiceBox<String> stopLocationChoiceBox;
    @FXML
    private ChoiceBox<String> busChoiceBox;
    @FXML
    private RadioButton findByBusNumberRadioButton;
    @FXML
    private RadioButton findByStartLocationRadioButton;
    @FXML
    private RadioButton findByStopLocationOnRadioButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUpTableColumns();
        //load data
        tableView.setItems(passengerService.getAll());

        startLocationChoiceBox.getItems().addAll(locationService.getAllNames());
        stopLocationChoiceBox.getItems().addAll(locationService.getAllNames());
        busChoiceBox.getItems().addAll(busService.getAllNames());
    }

    @Override
    public void setUpTableColumns() {

        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        startLocationColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getStartLocation().getStopName()));
        stopLocationColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getStopLocation().getStopName()));
        busNumberColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getBus().getBusNumber()));
    }

    @Override
    public Passenger getObjectFromFields() {
        Passenger passenger = new Passenger();
        passenger.setFullName(fullNameTextField.getText());
        passenger.setEmail(emailTextField.getText());
        passenger.setPhoneNumber(phoneNumberTextField.getText());
        passenger.setAge(Integer.parseInt(ageTextField.getText()));
        passenger.setStartLocation(locationService.findLocationByStopName(startLocationChoiceBox.getValue()));
        passenger.setStopLocation(locationService.findLocationByStopName(stopLocationChoiceBox.getValue()));
        passenger.setBus(busService.findBusByNumber(busChoiceBox.getValue()));

        return passenger;
    }

    @Override
    public void showAllButtonPushed(ActionEvent event) {
        tableView.setItems(passengerService.getAll());
    }


    @Override
    public void addButtonPushed() {

        if (checkAllUserInput()) {
            boolean result = passengerService.save(getObjectFromFields());
            if (result) {
                tableView.setItems(passengerService.getAll());
            } else {
                System.out.println("was not not saved to a database");
            }
        }
    }

    @Override
    public void updateButtonPushed() {

        Passenger changeablePassenger = getObjectFromSelectedTableRow();

        if (!fullNameTextField.getText().isEmpty())
            changeablePassenger.setFullName(fullNameTextField.getText());
        if (!emailTextField.getText().isEmpty())
            changeablePassenger.setEmail(emailTextField.getText());
        if (!phoneNumberTextField.getText().isEmpty())
            changeablePassenger.setPhoneNumber(phoneNumberTextField.getText());
        if (!ageTextField.getText().isEmpty())
            changeablePassenger.setAge(Integer.parseInt(ageTextField.getText()));
        if (locationService.findLocationByStopName(startLocationChoiceBox.getValue()) != null)
            changeablePassenger.setStartLocation(locationService.findLocationByStopName(startLocationChoiceBox.getValue()));
        if (locationService.findLocationByStopName(stopLocationChoiceBox.getValue()) != null)
            changeablePassenger.setStopLocation(locationService.findLocationByStopName(stopLocationChoiceBox.getValue()));
        if (busService.findBusByNumber(busChoiceBox.getValue()) != null)
            changeablePassenger.setBus(busService.findBusByNumber(busChoiceBox.getValue()));

        System.out.println(passengerService.update(changeablePassenger));

        tableView.setItems(passengerService.getAll());
        tableView.refresh();
    }

    @Override
    public void deleteButtonPushed() {

        ObservableList<Passenger> selectedRows, allPassengers;
        allPassengers = tableView.getItems();

        //this gives us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Bus objects from the table
        for (Passenger passenger : selectedRows) {
            allPassengers.remove(passenger);
            System.out.println(passengerService.delete(passenger));
        }
    }

    @Override
    public void findButtonPushed() {

        if (findByBusNumberRadioButton.isSelected() &&
                Validator.isChoiceBoxHasValue(busChoiceBox, "Select bus number"))
            tableView.setItems(passengerService.findPassengersByBusNumber(busChoiceBox.getValue()));
        if (findByStartLocationRadioButton.isSelected() &&
                Validator.isChoiceBoxHasValue(startLocationChoiceBox, "Select start location"))
            tableView.setItems(passengerService.findPassengersByStartLocation(startLocationChoiceBox.getValue()));
        if (findByStopLocationOnRadioButton.isSelected() &&
                Validator.isChoiceBoxHasValue(stopLocationChoiceBox, "Select stop location"))
            tableView.setItems(passengerService.findPassengersByStopLocation(stopLocationChoiceBox.getValue()));
    }

    @Override
    public void detailsButtonPushed(ActionEvent event) throws IOException {
        //access the controller and call a method
        PassengerDetails controller = changeScreen(event, "/passengerView.fxml").getController();
        controller.initData(getObjectFromSelectedTableRow());
    }

    private boolean checkAllUserInput() {
        return Validator.isChoiceBoxHasValue(startLocationChoiceBox, "Select start location") &&
                Validator.isChoiceBoxHasValue(stopLocationChoiceBox, "Select stop location") &&
                Validator.isChoiceBoxHasValue(busChoiceBox, "Select bus number") &&
                Validator.isTextFieldEmpty(fullNameTextField, "Type full name") &&
                Validator.isTextFieldEmpty(emailTextField, "Type email") &&
                Validator.isTextFieldEmpty(phoneNumberTextField, "Type phone number") &&
                Validator.isTextFieldEmpty(ageTextField, "Type age") &&
                Validator.stringMatcherValidation(ageTextField.getText(), "\\d+",
                        "Make sure that age is a number");
    }
}
