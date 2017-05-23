package com.image.ui.forgotpassword;

import com.image.dao.DAOException;
import com.image.dao.DAOFactory;
import com.image.dao.UserDAO;
import com.image.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * TODO
 */
public class ForgotPasswordController {

    @FXML
    private TextField textField;

    public void pressedButton(ActionEvent actionEvent) {
        UserDAO userDAO = DAOFactory.getUserDAO();
        User user = null;
        try {
            user = userDAO.getByKeyWord(textField.getText());
        } catch (DAOException e) {
            e.printStackTrace();
        }
        if (user == null) {
            // TODO change to throw new exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Ooops, there was an error!");
            alert.showAndWait();
        } else {
            Parent root;
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("User login: " + user.getLogin() + "\n" +
                        "User password: " + user.getPassword());
                alert.showAndWait();

                root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Login page");
                stage.setScene(new Scene(root, 500, 600));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void pressedButtonHome(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login page");
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}