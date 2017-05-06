package com.image.ui.secondpage;

import com.image.dao.DAOException;
import com.image.dao.DAOFactory;
import com.image.dao.ImageDAO;
import com.image.logic.Finder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
            root = FXMLLoader.load(getClass().getResource("../addimages/add.fxml"));
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

    public void buttonPressedFind(ActionEvent actionEvent) {
        Parent root;
        try {
            // TODO add path
            root = FXMLLoader.load(getClass().getResource("../thirdpage/third.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../showall/showall.fxml"));
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

