package schedulekeeper.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static schedulekeeper.ScheduleKeeper.stage;
import schedulekeeper.model.Appointment;
import schedulekeeper.model.Customer;
import schedulekeeper.util.DBConnect;
import schedulekeeper.util.Errors;

public class AppointmentUpdateController implements Initializable {

    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private ComboBox<Customer> customerComboBox;
    @FXML
    private TextField contactField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<String> startTimeComboBox;
    @FXML
    private ComboBox<String> endTimeComboBox;
    @FXML
    private ComboBox<String> locationComboBox;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private Label localHours;
    @FXML
    private Label userHours;
    
    ObservableList<Customer> customerList;
    Appointment existingAppointment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerList = DBConnect.getCustomerList();
        loadComboBoxes();
        existingAppointment = DBConnect.getPassedAppointment();
        DBConnect.setPassedAppointment(null);
        loadData();
        loadOfficeHours();
    }

    @FXML
    private void saveClick(ActionEvent event) throws IOException {
        if (titleField.getText().isEmpty()
                || descriptionField.getText().isEmpty()
                || customerComboBox.getValue() == null
                || contactField.getText().isEmpty()
                || startDatePicker.getValue() == null
                || endDatePicker.getValue() == null
                || startTimeComboBox.getValue().isEmpty()
                || endTimeComboBox.getValue().isEmpty()
                || locationComboBox.getValue().isEmpty()
                || typeComboBox.getValue().isEmpty()) {
            Errors.emptyField();
            return;
        } else {
            existingAppointment.setTitle(titleField.getText());
            existingAppointment.setDescription(descriptionField.getText());
            existingAppointment.setCustomer(customerComboBox.getValue().getCustomerName());
            existingAppointment.setCustomerId(customerComboBox.getValue().getCustomerId());
            existingAppointment.setContact(contactField.getText());
            existingAppointment.setStart(ZonedDateTime.of(startDatePicker
                    .getValue(), LocalTime.parse(startTimeComboBox.getValue()), ZoneId.systemDefault()));
            existingAppointment.setEnd(ZonedDateTime.of(endDatePicker
                    .getValue(), LocalTime.parse(endTimeComboBox.getValue()), ZoneId.systemDefault()));
            existingAppointment.setLocation(locationComboBox.getValue());
            existingAppointment.setType(typeComboBox.getValue());
        }
        
        if (!DBConnect.appointmentCheck(existingAppointment)) {
            return;
        }

        DBConnect.updateAppointment(existingAppointment);

        Parent root = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Appointments");
        stage.show();
    }

    @FXML
    private void cancelClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Appointments");
        stage.show();
    }

    private void loadComboBoxes() {
        customerComboBox.setItems(customerList);
        startTimeComboBox.setItems(DBConnect.getTimes());
        endTimeComboBox.setItems(DBConnect.getTimes());
        typeComboBox.setItems(FXCollections.observableArrayList("Training", "Installation", "Repair"));
        locationComboBox.setItems(FXCollections.observableArrayList("Phoenix", "New York", "London"));
    }

    //Lambda expression to find and return customer object from customerId in Appointment
    //Logical Justification - avoid loops to search customerList
    private void loadData() {
        titleField.setText(existingAppointment.getTitle());
        descriptionField.setText(existingAppointment.getDescription());
        contactField.setText(existingAppointment.getContact());
        customerComboBox.setValue(customerList.stream()
                .filter(x -> x.getCustomerId() == existingAppointment.getCustomerId())
                .findFirst().get());
        typeComboBox.setValue(existingAppointment.getType());
        locationComboBox.setValue(existingAppointment.getLocation());
        startTimeComboBox.setValue(existingAppointment.getStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        endTimeComboBox.setValue(existingAppointment.getEnd().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        startDatePicker.setValue(existingAppointment.getStart().toLocalDate());
        endDatePicker.setValue(existingAppointment.getEnd().toLocalDate());
    }

    private void loadOfficeHours() {
        localHours.setText(locationComboBox.getValue() + " is open from 09:00 to 17:00");
        userHours.setText("In your timezone: " 
                + DBConnect.getUserOpenHours(locationComboBox.getValue(), startDatePicker.getValue()) + " to " 
                + DBConnect.getUserCloseHours(locationComboBox.getValue(), startDatePicker.getValue())
        );
    }
    
    @FXML
    private void setHours(ActionEvent event) {
        loadOfficeHours();
    }

    @FXML
    private void dateClick(ActionEvent event) {
        loadOfficeHours();
    }

}
