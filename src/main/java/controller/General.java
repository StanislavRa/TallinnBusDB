package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parser.Parser;

import java.io.IOException;

public class General {

    Parser parser = new Parser();

    public void homeButtonPushed(ActionEvent event) throws IOException {

        changeScreen(event, "/home.fxml");
    }

    public FXMLLoader changeScreen(ActionEvent event, String viewName) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

        return loader;
    }
}
