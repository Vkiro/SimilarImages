package com.image.ui.user.secondpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * TODO
 */
public class ControllerSecond implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buttonPressedAdd(ActionEvent actionEvent) {
        Parent root;
        try {
            // TODO add path
            root = FXMLLoader.load(getClass().getResource("../../addimages/add.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buttonPressedShow(ActionEvent actionEvent) {
        Parent root;
        try {
            // TODO add path
            root = FXMLLoader.load(getClass().getResource("../showmy/showmy.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buttonPressedHome(ActionEvent actionEvent) {
        Parent root;
        try {
            // TODO add path
            root = FXMLLoader.load(getClass().getResource("../../login/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

