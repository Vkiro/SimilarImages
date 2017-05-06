package com.image.ui;

import com.image.dao.DAOException;
import com.image.dao.DAOFactory;
import com.image.dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class UserController implements Initializable{
    private static int userId;

    @FXML
    private TextField textField;

    @FXML
    private PasswordField passwordField;
    public void pressedButton(ActionEvent actionEvent) {
        UserDAO userDAO = DAOFactory.getUserDAO();
        try {
            userId = userDAO.getId(textField.getText(), passwordField.getText());
        } catch (DAOException e) {
            e.printStackTrace();
        }
        System.out.println(userId);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static int getUserId() {
        return userId;
    }
}
