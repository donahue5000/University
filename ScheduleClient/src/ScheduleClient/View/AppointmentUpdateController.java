package ScheduleClient.View;

import ScheduleClient.Model.Appointment;
import ScheduleClient.Model.Customer;
import static ScheduleClient.ScheduleClient.stage;
import ScheduleClient.Util.Connectatron;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
        if (existingAppointment != null) {
            loadFields();
        }
        loadComboBoxes();
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
        AppointmentsController appointments = new AppointmentsController();
        loader.setController(appointments);
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
    private void saveButtonClick(ActionEvent event) {
        
        
    }

    @FXML
    private void cancelButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Appointments.fxml"));
        CustomersController appointments = new CustomersController();
        loader.setController(appointments);
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
    
    private void loadComboBoxes(){
        typeMenu.setItems(types);
    }

}
