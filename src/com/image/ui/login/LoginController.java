package com.image.ui.login;

import com.image.dao.DAOException;
import com.image.dao.DAOFactory;
import com.image.dao.UserDAO;
import com.image.ui.user.secondpage.ControllerSecond;
import com.image.ui.user.showmy.ControllerShowMy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class LoginController implements Initializable{
    public static int userId;
    @FXML
    private TextField textField;

    @FXML
    private PasswordField passwordField;

    public void pressedButton(ActionEvent actionEvent) {
        userId = -1;
        UserDAO userDAO = DAOFactory.getUserDAO();
        try {
            userId = userDAO.getId(textField.getText(), passwordField.getText());
        } catch (DAOException e) {
            e.printStackTrace();
        }
        if (userId <= 0) {
            // TODO change to throw new exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Ooops, there was an error!");
            alert.showAndWait();
        } else if (userId == 4){ // Administrator
            Parent root;
            try {
                // TODO add path
                root = FXMLLoader.load(getClass().getResource("../secondpage/second.fxml"));
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
        } else { // User
            Parent root;
            try {
                // TODO add path
                ControllerShowMy.userId = userId;
                root = FXMLLoader.load(getClass().getResource("../user/secondpage/second.fxml"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void forgotPasswordClicked(MouseEvent mouseEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../forgotpassword/forgotpassword.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerClicked(MouseEvent mouseEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../register/register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
