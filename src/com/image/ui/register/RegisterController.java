package com.image.ui.register;

import com.image.dao.DAOException;
import com.image.dao.DAOFactory;
import com.image.dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * TODO
 */
public class RegisterController {

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField keyWord;

    public void pressedButtonRegister(ActionEvent actionEvent) {
        if (loginField.getText().equals("") || passwordField.getText().equals("")
                || confirmPassword.getText().equals("") || keyWord.getText().equals("")) {
            // TODO Exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Empty field");
            alert.setContentText("Fill in all fields!");
            alert.showAndWait();
        } else if (!passwordField.getText().equals(confirmPassword.getText())) {
            // TODO Exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Password");
            alert.setContentText("Confirm password and password should be the same!");
            alert.showAndWait();
        } else {
            // TODO add user with some empty fields
            UserDAO userDAO = DAOFactory.getUserDAO();
            try {
                userDAO.createUser(loginField.getText(), passwordField.getText(), firstName.getText(), lastName.getText(), email.getText(), keyWord.getText());
            } catch (DAOException e) {
                e.printStackTrace();
            }

            openLoginPage(actionEvent);
        }

    }

    public void pressedButtonHome(ActionEvent actionEvent) {
        openLoginPage(actionEvent);
    }

    private void openLoginPage(ActionEvent actionEvent) {
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
