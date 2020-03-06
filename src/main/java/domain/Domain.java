package domain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.Bus;

public class Domain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/home.fxml"));
        primaryStage.setTitle("My First App");
        primaryStage.setScene(new Scene(root, 700, 435));
        primaryStage.show();
    }



}
