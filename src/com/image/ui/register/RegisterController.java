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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * TODO
 */
public class RegisterController {

    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField keyWord;

    public void pressedButton(ActionEvent actionEvent) {
        if (login == null || password == null || confirm == null || keyWord == null) {
            // TODO Exception
        } else if (!password.getText().equals(confirm.getText())) {
            // TODO Exception
        } else {
            // TODO add user with some empty fields
            UserDAO userDAO = DAOFactory.getUserDAO();
            try {
                userDAO.createUser(login.getText(), password.getText(), firstName.getText(), lastName.getText(), email.getText(), keyWord.getText());
            } catch (DAOException e) {
                e.printStackTrace();
            }

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                stage.setScene(new Scene(root, 500, 600));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
