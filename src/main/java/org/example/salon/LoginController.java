package org.example.salon;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.salon.Database.DatabaseConnector;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Optional;


public class LoginController {
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;


    public void initialize() {
        comboBox.setItems(FXCollections.observableArrayList("Customer","Employee"));
    }

    public void nameFieldfunction() {

    }

    public void passwordFieldfunction() {

    }


    public void loginButtonfunction(ActionEvent e) {
        String username = nameField.getText();
        String password =  passwordField.getText();

        if (comboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOPS!");
            alert.setContentText("Select a Role!");
            alert.showAndWait();
        }
        else {
            if (DatabaseConnector.validateUser(username, password) && comboBox.getValue() == "Customer") {
                Auth.setUser(nameField.getText());
                Auth.openPage("appointment-view", e);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("OOPS!");
                alert.setContentText("Wrong Credentials");
                alert.showAndWait();
            }
        }
    }


    public void signUpTextfunction(ActionEvent e)  {
        Auth.openPage("signup-view", e);
    }

}
