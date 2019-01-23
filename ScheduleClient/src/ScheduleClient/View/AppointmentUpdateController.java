package ScheduleClient.View;

import ScheduleClient.Model.Appointment;
import ScheduleClient.Model.Customer;
import static ScheduleClient.ScheduleClient.stage;
import ScheduleClient.Util.Connectatron;
import ScheduleClient.Util.Oops;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AppointmentUpdateController implements Initializable {

    @FXML
    private Label userLabel;
    @FXML
    private TextField startField;
    @FXML
    private TextField endField;
    @FXML
    private ComboBox<Customer> customerMenu;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField contactField;
    @FXML
    private TextField urlField;
    @FXML
    private ComboBox<String> typeMenu;

    private Appointment existingAppointment;
    private Appointment newAppointment;
    private ObservableList<Customer> customerList;
    private Customer selectedCustomer;
    private ObservableList<String> types;

    public AppointmentUpdateController() {
        existingAppointment = null;
    }

    public AppointmentUpdateController(Appointment existingAppointment) {
        this.existingAppointment = existingAppointment;
        selectedCustomer = Connectatron.getCustomerByID(
                existingAppointment.getCustomerId());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userLabel.setText(Connectatron.USER);
        types = FXCollections.observableArrayList("Training", "Repair", "Installation");
        customerList = Connectatron.getCustomerList();
        loadComboBoxes();
        if (existingAppointment != null) {
            loadFields();
            customerMenu.setValue(selectedCustomer);
            typeMenu.setValue(existingAppointment.getType());
        } else {
            loadNewFields();
        }

    }

    @FXML
    private void logoutButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void appointmentsButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Appointments.fxml"));
        AppointmentsController showAppointments = new AppointmentsController();
        loader.setController(showAppointments);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Client - Appointments");
        stage.show();
    }

    @FXML
    private void customersButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Customers.fxml"));
        CustomersController showCustomerInList = new CustomersController();
        loader.setController(showCustomerInList);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Client - New Customer Added, Huzzah!");
        stage.show();
    }

    @FXML
    private void reportsButtonClick(ActionEvent event) {
    }

    @FXML
    private void logButtonClick(ActionEvent event) {
    }

    @FXML
    private void saveButtonClick(ActionEvent event) throws IOException {
        if (existingAppointment != null) {
            newAppointment = existingAppointment;
            Connectatron.deleteAppointment(existingAppointment.getAppointmentId());
        } else {
            newAppointment = new Appointment();
            newAppointment.setAppointmentId(-1);
            newAppointment.setUserId(Connectatron.USERID);
        }

        try {
            newAppointment.setCustomerId(customerMenu.getValue().getCustomerId());
            newAppointment.setCustomerName(customerMenu.getValue().getCustomerName());
            newAppointment.setTitle(titleField.getText());
            newAppointment.setDescription(descriptionField.getText());
            newAppointment.setLocation(locationField.getText());
            newAppointment.setContact(contactField.getText());
            newAppointment.setUrl(urlField.getText());
            newAppointment.setStart(startField.getText());
            newAppointment.setEnd(endField.getText());
            newAppointment.setType(typeMenu.getValue());
        } catch (NullPointerException n) {
            Oops.blankField();
            return;
        }

        try {
            Connectatron.insertAppointment(newAppointment);
        } catch (NullPointerException n) {
            Oops.blankField();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Appointments.fxml"));
        AppointmentsController showAppointments = new AppointmentsController(
                Connectatron.getAppointmentByID(newAppointment.getAppointmentId()));
        loader.setController(showAppointments);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Client - Appointments");
        stage.show();
    }

    @FXML
    private void cancelButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Appointments.fxml"));
        AppointmentsController showAppointments = new AppointmentsController();
        loader.setController(showAppointments);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Client - Appointments");
        stage.show();
    }

    private void loadFields() {
        startField.setText(existingAppointment.getStart());
        endField.setText(existingAppointment.getEnd());
        titleField.setText(existingAppointment.getTitle());
        descriptionField.setText(existingAppointment.getDescription());
        locationField.setText(existingAppointment.getLocation());
        contactField.setText(existingAppointment.getContact());
        urlField.setText(existingAppointment.getUrl());
    }

    private void loadNewFields() {
        startField.setText(Connectatron.getDTFormatter().format(LocalDateTime.now(ZoneId.systemDefault())));
        endField.setText(Connectatron.getDTFormatter().format(LocalDateTime.now(ZoneId.systemDefault())));
        urlField.setText("N/A");
    }

    private void loadComboBoxes() {
        typeMenu.setItems(types);
        customerMenu.setItems(customerList);
    }

}
