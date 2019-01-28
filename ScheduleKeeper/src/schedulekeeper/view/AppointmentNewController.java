
package schedulekeeper.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
import javafx.scene.control.TextField;
import static schedulekeeper.ScheduleKeeper.stage;
import schedulekeeper.model.Appointment;
import schedulekeeper.model.Customer;
import schedulekeeper.util.DBConnect;
import schedulekeeper.util.Errors;


public class AppointmentNewController implements Initializable {

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

    
    ObservableList<Customer> customerList;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerList = DBConnect.getCustomerList();
        loadComboBoxes();
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
                || typeComboBox.getValue().isEmpty()){
            Errors.emptyField();
            return;
        }else{
            Appointment appointment = new Appointment(
                    -1,
                    customerComboBox.getSelectionModel().getSelectedItem().getCustomerId(),
                    customerComboBox.getSelectionModel().getSelectedItem().getCustomerName(),
                    titleField.getText(),
                    descriptionField.getText(),
                    locationComboBox.getValue(),
                    contactField.getText(),
                    "N/A",
                    DBConnect.getUserID(),
                    DBConnect.getUser(),
                    typeComboBox.getValue(),
                    ZonedDateTime.of(startDatePicker.getValue(), LocalTime.parse(startTimeComboBox.getValue()), ZoneId.systemDefault()),
                    ZonedDateTime.of(endDatePicker.getValue(), LocalTime.parse(endTimeComboBox.getValue()), ZoneId.systemDefault())
            );
            DBConnect.insertAppointment(appointment);
            Parent root = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Schedule Keeper - Appointments");
            stage.show();
        }
    }

    @FXML
    private void cancelClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Appointments");
        stage.show();
    }
    
    private void loadComboBoxes(){
        customerComboBox.setItems(customerList);
        startTimeComboBox.setItems(DBConnect.getTimes());
        endTimeComboBox.setItems(DBConnect.getTimes());
        typeComboBox.setItems(FXCollections.observableArrayList("Training", "Installation", "Repair"));
        locationComboBox.setItems(FXCollections.observableArrayList("Phoenix", "New York", "London"));
    }
    
}
