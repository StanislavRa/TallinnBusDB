package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Driver;
import util.Validator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Drivers extends Models<Driver> implements Initializable {
    @FXML
    private TableColumn<Driver, String> fullNameColumn;
    @FXML
    private TableColumn<Driver, String> addressColumn;
    @FXML
    private TableColumn<Driver, String> phoneColumn;
    @FXML
    private TableColumn<Driver, String> ageColumn;
    @FXML
    private TableColumn<Driver, String> heightColumn;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private RadioButton findByAddressRadioButton;
    @FXML
    private RadioButton findByAgeRadioButton;
    @FXML
    private RadioButton findByHeightRadioButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpTableColumns();
        //load data
        tableView.setItems(driverService.getAll());
    }

    @Override
    public void setUpTableColumns() {

        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
    }

    @Override
    public Driver getObjectFromFields() {

        Driver driver = new Driver();
        driver.setFullName(fullNameTextField.getText());
        driver.setAddress(addressTextField.getText());
        driver.setPhone(phoneTextField.getText());
        driver.setAge(Integer.parseInt(ageTextField.getText()));
        driver.setHeight(Float.parseFloat(heightTextField.getText()));

        return driver;
    }

    @Override
    public void showAllButtonPushed(ActionEvent event) {
        tableView.setItems(driverService.getAll());
    }

    @Override
    public void addButtonPushed() {

        if (checkAllUserInput()) {
            boolean result = driverService.save(getObjectFromFields());
            if (result) {
                tableView.setItems(driverService.getAll());
            } else {
                System.out.println("was not not saved to a database");
            }
        }
    }

    @Override
    public void updateButtonPushed() {

        Driver changeableDriver = getObjectFromSelectedTableRow();

        if (!addressTextField.getText().isEmpty())
            changeableDriver.setAddress(addressTextField.getText());
        if (!fullNameTextField.getText().isEmpty())
            changeableDriver.setFullName(fullNameTextField.getText());

        if (!heightTextField.getText().isEmpty())
            changeableDriver.setHeight(Float.parseFloat(heightTextField.getText()));
        if (!ageTextField.getText().isEmpty())
            changeableDriver.setAge(Integer.parseInt(ageTextField.getText()));
        if (!phoneTextField.getText().isEmpty())
            changeableDriver.setPhone(phoneTextField.getText());

        System.out.println(driverService.update(changeableDriver));

        tableView.setItems(driverService.getAll());
        tableView.refresh();
    }

    @Override
    public void deleteButtonPushed() {

        ObservableList<Driver> selectedRows, allDrivers;
        allDrivers = tableView.getItems();

        //this gives us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Bus objects from the table
        for (Driver driver : selectedRows) {
            allDrivers.remove(driver);
            System.out.println(driverService.delete(driver));
        }
    }

    @Override
    public void findButtonPushed() {

        if (findByAddressRadioButton.isSelected() &&
                Validator.isTextFieldEmpty(addressTextField, "Type address"))
            tableView.setItems(driverService.findDriversByAddress(addressTextField.getText()));
        if (findByAgeRadioButton.isSelected() &&
                Validator.isTextFieldEmpty(ageTextField, "Type age"))
            tableView.setItems(driverService.findDriversByAge(Integer.parseInt(ageTextField.getText())));
        if (findByHeightRadioButton.isSelected() &&
                Validator.isTextFieldEmpty(heightTextField, "Type height") &&
                Validator.stringMatcherValidation(heightTextField.getText(), "[0-9]*['.']?[0-9]*",
                        "Make sure that height format is xx.xx"))
            tableView.setItems(driverService.findDriversByHeight(Float.parseFloat(heightTextField.getText())));
    }

    @Override
    public void detailsButtonPushed(ActionEvent event) throws IOException {

        //access the controller and call a method
        DriverDetails controller = changeScreen(event, "/driverView.fxml").getController();
        controller.initData(getObjectFromSelectedTableRow());
    }

    private boolean checkAllUserInput() {
        return Validator.isTextFieldEmpty(fullNameTextField, "Type full name") &&
                Validator.isTextFieldEmpty(addressTextField, "Type address") &&
                Validator.isTextFieldEmpty(phoneTextField, "Type phone") &&
                Validator.isTextFieldEmpty(ageTextField, "Type age") &&
                Validator.stringMatcherValidation(ageTextField.getText(), "\\d+",
                        "Make sure that age is a number") &&
                Validator.isTextFieldEmpty(heightTextField, "Type height") &&
                Validator.stringMatcherValidation(heightTextField.getText(), "[0-9]*['.']?[0-9]*",
                        "Make sure that height format is xx.xx");
    }
}
