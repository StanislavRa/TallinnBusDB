package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Bus;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class Buses extends Models<Bus> implements Initializable {

    @FXML
    private TableView<Bus> busesTableView;
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
    private Button addBusButton;
    @FXML
    private Button updateBusButton;

    @FXML
    private Button deleteBusButton;
    @FXML
    private Button findBusButton;
    @FXML
    private Button homeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUpTableColumns();
        //load data
        busesTableView.setItems(bus.getAll());
        driverChoiceBox.getItems().addAll(driver.getAllNames());
    }

    public void setUpTableColumns() {
        //set up the columns in the table
        busNumberColumn.setCellValueFactory(new PropertyValueFactory<>("busNumber"));
        driverFullNameColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getDriver().getFullName()));
        fuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
        purchasedOnColumn.setCellValueFactory(new PropertyValueFactory<>("purchasedOn"));
    }


    public void updateButtonPushed() {
        Bus changeableBus = getObjectFromSelectedTableRow();

        if (!busNumberTextField.getText().isEmpty())
            changeableBus.setBusNumber(busNumberTextField.getText());
        if (driver.findDriverByFullName(driverChoiceBox.getValue()) != null )
            changeableBus.setDriver(driver.findDriverByFullName(driverChoiceBox.getValue()));
        if (!fuelTextField.getText().isEmpty())
            changeableBus.setFuel(Float.parseFloat(fuelTextField.getText()));
        if (purchasedOnDatePicker.getValue() != null)
            changeableBus.setPurchasedOn(parser.convertToDateViaSqlDate(purchasedOnDatePicker.getValue()));

        System.out.println(this.bus.update(changeableBus));

        busesTableView.setItems(this.bus.getAll());
    }

    public Bus getObjectFromSelectedTableRow() {
        return busesTableView.getSelectionModel().getSelectedItem();
    }

    public Bus getObjectFromFields() {

        Bus bus = new Bus();
        bus.setBusNumber(busNumberTextField.getText());
        bus.setDriver(driver.findDriverByFullName(driverChoiceBox.getValue()));
        bus.setFuel(Float.parseFloat(fuelTextField.getText()));
        bus.setPurchasedOn(parser.convertToDateViaSqlDate(purchasedOnDatePicker.getValue()));
        return bus;
    }

    public void addButtonPushed() {

        //Get all the items from the table as a list, then add the new bus to the list
        System.out.println(this.bus.save(getObjectFromFields()));

        busesTableView.getItems().add(getObjectFromFields());
    }

    public void findButtonPushed(ActionEvent event) throws IOException {

        //access the controller and call a method
        BusDetails controller = changeScreen(event, "/busView.fxml").getController();
        controller.initData(busesTableView.getSelectionModel().getSelectedItem());
    }

    public void deleteButtonPushed() {

        ObservableList<Bus> selectedRows, allBuses;
        allBuses = busesTableView.getItems();

        //this gives us the rows that were selected
        selectedRows = busesTableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Bus objects from the table
        for (Bus bus : selectedRows) {
            allBuses.remove(bus);
            System.out.println(this.bus.delete(bus));
        }
    }

    public void userClickedOnTable() {

        this.findBusButton.setDisable(false);
        this.deleteBusButton.setDisable(false);
        this.updateBusButton.setDisable(false);
    }

}
