package org.example.salon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.example.salon.Database.DatabaseConnector;
import org.example.salon.Database.Model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    private ArrayList<User> stylists;
    private ArrayList<Service> a;

    @FXML
    ComboBox<String> CategoryBox, ServiceBox, stylistBox;
    @FXML
    DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategoryBox.setItems(FXCollections.observableArrayList("Hair", "Spa", "Beauty"));
        stylists = new ArrayList<>();
        stylists = DatabaseConnector.getEmployees();
        ObservableList<String> list = FXCollections.observableArrayList();
        for (User stylist : stylists) {
            list.add(stylist.getFullName());
        }
        stylistBox.setItems(list);
    }

    public void CategoryBoxfunction() {
        setServiceBox(CategoryBox.getValue());
    }

    public void setServiceBox(String Category) {
        a = new ArrayList<>();
        a = DatabaseConnector.getServicesByCategory(Category);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Service service : a) {
            String s = service.getName() +" (" + Double.toString(service.getPrice())+ ")";
            observableList.add(s);
        }
        ServiceBox.setItems(observableList);
    }

    public static Date convertLocalDateToDate(LocalDate localDate) {
        long epochMilli = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return new Date(epochMilli);
    }

    public static String extractServiceName(String s) {
        int index = s.indexOf(" (");
        if (index != -1) {
            return s.substring(0, index);
        }
        return s;
    }

    public void confirmButtonfunction() {

        User stylist = null;
        Service s = null;
        String serviceName = extractServiceName(ServiceBox.getValue());


        for (User user : stylists) {
            if (user.getFullName().equals(stylistBox.getValue())) {
                stylist = user;
                break;
            }
        }

        for (Service service : a) {
            if (service.getName().equals(serviceName)) {
                s = service;
                break;
            }
        }

        Date d = convertLocalDateToDate(datePicker.getValue());
        Appointment apnmnt = new Appointment(Auth.getUser(), stylist, s, d, generator.generateCurrentTime());
        DatabaseConnector.addAppointment(apnmnt);
    }

    public void servicesButtonfunction(ActionEvent event) {
        Auth.openPage("services-view", event);
    }

    public void profileButtonfunction(ActionEvent event) {
        Auth.openPage("profile-view", event);
    }



}
