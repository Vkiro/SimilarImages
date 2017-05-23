package com.image.ui.userinformationpage;

import com.image.domain.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * TODO
 */
public class UserController implements Initializable{

    public static User user;

    @FXML
    private TextField loginField;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginField.setText(user.getLogin());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
    }
}
