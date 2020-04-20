package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import service.*;

import java.io.IOException;

public abstract class Models<T> extends General {

    @FXML
    protected TableView<T> tableView;
    @FXML
    protected Button addButton;
    @FXML
    protected Button updateButton;
    @FXML
    protected Button deleteButton;
    @FXML
    protected Button detailsButton;
    @FXML
    protected Button findButton;

    protected Bus busService = new Bus();
    protected Driver driverService = new Driver();
    protected Passenger passengerService = new Passenger();
    protected Location locationService = new Location();
    protected Timetable timetableService = new Timetable();
    protected Service<T> service;

    public void userClickedOnTable() {

        this.detailsButton.setDisable(false);
        this.deleteButton.setDisable(false);
        this.updateButton.setDisable(false);
    }

    public T getObjectFromSelectedTableRow() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public abstract void setUpTableColumns();

    public abstract T getObjectFromFields();

    @FXML
    public abstract void showAllButtonPushed(ActionEvent event);

    @FXML
    public abstract void addButtonPushed();

    @FXML
    public abstract void updateButtonPushed();

    @FXML
    public abstract void deleteButtonPushed();

    @FXML
    public abstract void findButtonPushed();

    @FXML
    public abstract void detailsButtonPushed(ActionEvent event) throws IOException;
}
