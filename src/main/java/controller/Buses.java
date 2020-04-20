package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Bus;
import util.Validator;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class Buses extends Models<Bus> implements Initializable {

    @FXML
    private TableColumn<Bus, String> busNumberColumn;
    @FXML
    private TableColumn<Bus, String> driverFullNameColumn;
    @FXML
    private TableColumn<Bus, String> fuelColumn;
    @FXML
    private TableColumn<Bus, Date> purchasedOnColumn;
    @FXML
    private ChoiceBox<String> driverChoiceBox;
    @FXML
    private TextField busNumberTextField;
    @FXML
    private TextField fuelTextField;
    @FXML
    private DatePicker purchasedOnDatePicker;
    @FXML
    private RadioButton findByDriverRadioButton;
    @FXML
    private RadioButton findByFuelRadioButton;
    @FXML
    private RadioButton findByPurchaseOnRadioButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUpTableColumns();
        //load data
        tableView.setItems(busService.getAll());
        driverChoiceBox.getItems().addAll(driverService.getAllNames());
    }

    public void setUpTableColumns() {
        //set up the columns in the table
        busNumberColumn.setCellValueFactory(new PropertyValueFactory<>("busNumber"));
        driverFullNameColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getDriver().getFullName()));
        fuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
        purchasedOnColumn.setCellValueFactory(new PropertyValueFactory<>("purchasedOn"));
    }

    public Bus getObjectFromFields() {

        Bus bus = new Bus();
        bus.setBusNumber(busNumberTextField.getText());
        bus.setDriver(driverService.findDriverByFullName(driverChoiceBox.getValue()));
        bus.setFuel(Float.parseFloat(fuelTextField.getText()));
        bus.setPurchasedOn(parser.convertToDateViaSqlDate(purchasedOnDatePicker.getValue()));
        return bus;
    }

    @Override
    public void showAllButtonPushed(ActionEvent event) {
        tableView.setItems(busService.getAll());
    }

    public void addButtonPushed() {

        if (checkAllUserInput()) {
            boolean result = busService.save(getObjectFromFields());
            if (result) {
                tableView.setItems(busService.getAll());
            } else {
                System.out.println("was not not saved to a database");
            }
        }
    }

    public void updateButtonPushed() {

        Bus changeableBus = getObjectFromSelectedTableRow();

        if (!busNumberTextField.getText().isEmpty())
            changeableBus.setBusNumber(busNumberTextField.getText());
        if (driverService.findDriverByFullName(driverChoiceBox.getValue()) != null)
            changeableBus.setDriver(driverService.findDriverByFullName(driverChoiceBox.getValue()));
        if (!fuelTextField.getText().isEmpty())
            changeableBus.setFuel(Float.parseFloat(fuelTextField.getText()));
        if (purchasedOnDatePicker.getValue() != null)
            changeableBus.setPurchasedOn(parser.convertToDateViaSqlDate(purchasedOnDatePicker.getValue()));

        System.out.println(busService.update(changeableBus));

        tableView.setItems(busService.getAll());
        tableView.refresh();
    }


    public void deleteButtonPushed() {

        ObservableList<Bus> selectedRows, allBuses;
        allBuses = tableView.getItems();

        //this gives us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Bus objects from the table
        for (Bus bus : selectedRows) {
            allBuses.remove(bus);
            System.out.println(busService.delete(bus));
        }
    }

    @Override
    public void findButtonPushed() {

        if (findByDriverRadioButton.isSelected() &&
                Validator.isChoiceBoxHasValue(driverChoiceBox, "Select driver"))
            tableView.setItems(busService.findBusByDriverName(driverChoiceBox.getValue()));
        if (findByFuelRadioButton.isSelected() &&
                Validator.isTextFieldEmpty(fuelTextField, "Type fuel value") &&
                Validator.stringMatcherValidation(fuelTextField.getText(), "[0-9]*['.']?[0-9]*",
                        "Make sure that number format is xx.xx"))
            tableView.setItems(busService.findBusByFuel(Float.parseFloat(fuelTextField.getText())));
        if (findByPurchaseOnRadioButton.isSelected() &&
                Validator.isDatePickerNotEmpty(purchasedOnDatePicker, "Select Date"))
            tableView.setItems(busService.findBusByPurchasedOn(
                    parser.convertToDateViaSqlDate(purchasedOnDatePicker.getValue())));
    }

    public void detailsButtonPushed(ActionEvent event) throws IOException {

        //access the controller and call a method
        BusDetails controller = changeScreen(event, "/busView.fxml").getController();
        controller.initData(getObjectFromSelectedTableRow());
    }

    private boolean checkAllUserInput() {
        return Validator.isChoiceBoxHasValue(driverChoiceBox, "Select driver") &&
                Validator.isTextFieldEmpty(fuelTextField, "Type fuel value") &&
                Validator.stringMatcherValidation(fuelTextField.getText(), "[0-9]*['.']?[0-9]*",
                        "Make sure that number format is xx.xx") &&
                Validator.isDatePickerNotEmpty(purchasedOnDatePicker, "Select Date") &&
                Validator.isTextFieldEmpty(busNumberTextField, "Type bus number");
    }
}
