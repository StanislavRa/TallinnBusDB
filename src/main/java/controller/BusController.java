package controller;

import db.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Bus;
import service.BusService;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class BusController extends Controller implements Initializable {

    @FXML
    private TableColumn<Bus, String> driverIdColumn;
    //configure the table
    @FXML
    private TableView<Bus> busesTableView;
    @FXML
    private TableColumn<Bus, String> busNumberColumn;
    @FXML
    private TableColumn<Bus, String> fuelColumn;
    @FXML
    private TableColumn<Bus, Date> purchasedOnColumn;
    @FXML
    private Button homeButton;
    @FXML
    private TextField busNumberTextField;
    @FXML
    private TextField driverIdTextField;
    @FXML
    private TextField fuelTextField;
    @FXML
    private DatePicker purchasedOnDatePicker;
    @FXML
    private Button addBusButton;
    @FXML
    private Button deleteBusButton;
    @FXML
    private Button findBusButton;

    private BusService busService = new BusService();

    public void addBusButtonPushed() {

        Bus bus = new Bus();
        bus.setBusNumber(busNumberTextField.getText());
        bus.setDriverId(Integer.parseInt(driverIdTextField.getText()));
        bus.setFuel(Float.parseFloat(fuelTextField.getText()));
        bus.setPurchasedOn(convertToDateViaSqlDate(purchasedOnDatePicker.getValue()));

        //Get all the items from the table as a list, then add the new person to
        //the list
        System.out.println(busService.save(bus));

        busesTableView.getItems().add(bus);
    }

    public void userClickedOnTable()
    {
        this.findBusButton.setDisable(false);
    }


    public void findBusButtonPushed(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/busView.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        BusViewController controller = loader.getController();
        controller.initData(busesTableView.getSelectionModel().getSelectedItem());

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    public void deleteButtonPushed() {
        ObservableList<Bus> selectedRows, allBuses;
        allBuses = busesTableView.getItems();

        //this gives us the rows that were selected
        selectedRows = busesTableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Bus bus : selectedRows) {
            allBuses.remove(bus);
            System.out.println(busService.delete(bus));
        }
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up the columns in the table
        busNumberColumn.setCellValueFactory(new PropertyValueFactory<Bus, String>("busNumber"));
        driverIdColumn.setCellValueFactory(new PropertyValueFactory<Bus, String>("driverId"));
        fuelColumn.setCellValueFactory(new PropertyValueFactory<Bus, String>("fuel"));
        purchasedOnColumn.setCellValueFactory(new PropertyValueFactory<Bus, Date>("purchasedOn"));

        //load data
        busesTableView.setItems(busService.listAllBuses());
    }
}
