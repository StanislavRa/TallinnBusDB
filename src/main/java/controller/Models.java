package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import service.Bus;
import service.Driver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class Models<T> extends General {

    Bus bus = new Bus();
    Driver driver = new Driver();

    public abstract void setUpTableColumns();

    public abstract T getObjectFromSelectedTableRow();

    public abstract T getObjectFromFields();

    public abstract void addButtonPushed();

    public abstract void updateButtonPushed();

    public abstract void deleteButtonPushed();

    public abstract void findButtonPushed(ActionEvent event) throws IOException;

    public abstract void userClickedOnTable();
}
