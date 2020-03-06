package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import service.Bus;
import service.Driver;

import java.io.IOException;

public abstract class Models<T> extends General {

    @FXML
    protected TableView<T> TableView;
    @FXML
    protected Button addButton;
    @FXML
    protected Button updateButton;
    @FXML
    protected Button deleteButton;
    @FXML
    protected Button findButton;

    protected Bus bus = new Bus();
    protected Driver driver = new Driver();

    public void userClickedOnTable() {

        this.findButton.setDisable(false);
        this.deleteButton.setDisable(false);
        this.updateButton.setDisable(false);
    }


    public abstract void setUpTableColumns();

    public abstract T getObjectFromSelectedTableRow();

    public abstract T getObjectFromFields();

    public abstract void addButtonPushed();

    public abstract void updateButtonPushed();

    public abstract void deleteButtonPushed();

    public abstract void findButtonPushed(ActionEvent event) throws IOException;
}
