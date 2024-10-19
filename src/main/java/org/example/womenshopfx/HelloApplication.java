package org.example.womenshopfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml")); // Replace with your initial view
        primaryStage.setTitle("Women's Shop");
        primaryStage.setScene(new Scene(root, 800, 600)); // Adjust size as needed
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
