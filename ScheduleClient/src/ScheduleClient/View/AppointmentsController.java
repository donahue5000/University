package ScheduleClient.View;

import ScheduleClient.Model.Appointment;
import ScheduleClient.Model.Customer;
import static ScheduleClient.ScheduleClient.stage;
import ScheduleClient.Util.Connectatron;
import ScheduleClient.Util.Oops;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AppointmentsController implements Initializable {

    @FXML
    private Label userLabel;
    @FXML
    private RadioButton radioWeek;
    @FXML
    private ToggleGroup weekMonth;
    @FXML
    private RadioButton radioMonth;
    @FXML
    private Label calendarRangeLabel;
    @FXML
    private TableView<Appointment> appointmentsTableView;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endColumn;
    @FXML
    private TableColumn<Appointment, Integer> customerColumn;
    @FXML
    private TableColumn<Appointment, String> titleColumn;
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    @FXML
    private TextField appointmentIDField;
    @FXML
    private TextField customerIDField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField contactField;
    @FXML
    private TextField URLField;
    @FXML
    private TextField startField;
    @FXML
    private TextField endField;
    @FXML
    private TextField createDateField;
    @FXML
    private TextField createdByField;
    @FXML
    private TextField lastUpdateField;
    @FXML
    private TextField lastUpdatedByField;
    @FXML
    private TextField userIDField;
    @FXML
    private TextField typeField;
    @FXML
    private RadioButton radioAll;

    private ObservableList<Appointment> appointmentList;
    private Appointment selectedAppointment;
    private Customer selectedCustomer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userLabel.setText(Connectatron.USER);
        appointmentList = Connectatron.getAppointmentList();
        loadTable();
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
        Parent root = FXMLLoader.load(getClass().getResource(
                "Appointments.fxml"));
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
        stage.setTitle("Schedule Client - Customers");
        stage.show();
    }

    @FXML
    private void reportsButtonClick(ActionEvent event) {
    }

    @FXML
    private void logButtonClick(ActionEvent event) {
    }

    @FXML
    private void radioChange(ActionEvent event) {
    }

    @FXML
    private void previousButtonClick(ActionEvent event) {
    }

    @FXML
    private void nextButtonClick(ActionEvent event) {
    }

    @FXML
    private void newButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentUpdate.fxml"));
        AppointmentUpdateController newAppointment = new AppointmentUpdateController();
        loader.setController(newAppointment);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Client - Adding New Appointment");
        stage.show();
    }

    @FXML
    private void updateButtonClick(ActionEvent event) throws IOException {
        if (selectedAppointment == null) {
            Oops.noSelection();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentUpdate.fxml"));
        AppointmentUpdateController newAppointment = new AppointmentUpdateController(selectedAppointment);
        loader.setController(newAppointment);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Client - Updating Appointment");
        stage.show();
    }

    @FXML
    private void deleteButtonClick(ActionEvent event) {
    }

    @FXML
    private void SelectedCustomerButtonClick(ActionEvent event) throws IOException {
        if (appointmentsTableView.getSelectionModel().getSelectedItem() != null) {
            int customerID = appointmentsTableView.getSelectionModel().getSelectedItem().getCustomerId();
            selectedCustomer = Connectatron.getCustomerByID(customerID);
        }
        if (selectedCustomer == null){
            Oops.noSelection();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerUpdate.fxml"));
        CustomerUpdateController newCustomer = new CustomerUpdateController(selectedCustomer);
        loader.setController(newCustomer);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Client - Updating Customer Record");
        stage.show();
    }

    private void loadTable() {
        if (appointmentList.size() < 1) {
            return;
        }
        appointmentsTableView.setItems(appointmentList);
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        startColumn.setSortType(TableColumn.SortType.ASCENDING);
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        appointmentsTableView.getSortOrder().add(startColumn);
        appointmentsTableView.getColumns().setAll(startColumn, endColumn, customerColumn,
                titleColumn, descriptionColumn, locationColumn);
    }

    @FXML
    private void appointmentsTableViewClick(MouseEvent event) {
        if (appointmentsTableView.getSelectionModel().getSelectedItem() != null) {
            selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();
        }
        if (selectedAppointment != null) {
            appointmentIDField.setText(selectedAppointment.appointmentIdProperty().getValue().toString());
            customerIDField.setText(selectedAppointment.customerIdProperty().getValue().toString());
            titleField.setText(selectedAppointment.titleProperty().getValue());
            descriptionField.setText(selectedAppointment.descriptionProperty().getValue());
            locationField.setText(selectedAppointment.locationProperty().getValue());
            contactField.setText(selectedAppointment.contactProperty().getValue());
            URLField.setText(selectedAppointment.urlProperty().getValue());
            startField.setText(selectedAppointment.startProperty().getValue());
            endField.setText(selectedAppointment.endProperty().getValue());
            createDateField.setText(selectedAppointment.createDateProperty().getValue());
            createdByField.setText(selectedAppointment.createdByProperty().getValue());
            lastUpdateField.setText(selectedAppointment.lastUpdateProperty().getValue());
            lastUpdatedByField.setText(selectedAppointment.lastUpdatedByProperty().getValue());
            userIDField.setText(selectedAppointment.userIdProperty().getValue().toString());
            typeField.setText(selectedAppointment.typeProperty().getValue());
        }
    }

}