package org.example.salon;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ServicesController {

    public void appointmentButtonfunction(ActionEvent event) {
        Auth.openPage("appointment-view", event);
    }

    public void profileButtonfunction(ActionEvent event) {
        Auth.openPage("profile-view", event);
    }
}
