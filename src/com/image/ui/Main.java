package com.image.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent panel = FXMLLoader.load(getClass().getResource("login/login.fxml"));
        primaryStage.setTitle("Login page");
        Scene scene = new Scene(panel, 500, 600);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("login/login.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
