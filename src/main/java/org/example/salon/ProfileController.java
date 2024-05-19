package org.example.salon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.example.salon.Database.DatabaseConnector;
import org.example.salon.Database.Model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    Label usernameLabel, nameLabel, numberLabel, emailLabel, pointLabel;

    @FXML
    PasswordField currentPass,newPass, confirmPass;

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void servicesButtonfunction(ActionEvent e) {
        Auth.openPage("services-view", e);
    }

    public void appointmentbuttonfunction(ActionEvent e) {
        Auth.openPage("appointment-view", e);
    }

    public void submitButtonfunction() {

    }
}
