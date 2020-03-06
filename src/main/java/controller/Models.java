package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import service.Bus;
import service.Driver;

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
    protected Button showAll;
    @FXML
    protected Button findButton;

    protected Bus bus = new Bus();
    protected Driver driver = new Driver();

    public void userClickedOnTable() {

        this.detailsButton.setDisable(false);
        this.deleteButton.setDisable(false);
        this.updateButton.setDisable(false);
    }

    public abstract void setUpTableColumns();

    public abstract T getObjectFromSelectedTableRow();

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
