package org.example.salon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.salon.Database.DatabaseConnector;
import org.example.salon.Database.Model.*;

import java.io.IOException;
import java.sql.Connection;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Connection conn = DatabaseConnector.getConnection();

        if (conn != null) {
            System.out.println("Connection established successfully.");
        } else {
            System.out.println("Failed to establish connection.");
        }

//        User c = DatabaseConnector.getUserObjectbyUsername("jon0iko");
//        User e = DatabaseConnector.getUserObjectbyUsername("Sarah");
//        Service s = DatabaseConnector.getServicebyName("haircut");
//
//        Appointment a = new Appointment(c,e,s,generator.generateCurrentDate(), generator.generateCurrentTime(),"scheduled");
//        DatabaseConnector.addAppointment(a);


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("TASH Salon");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}