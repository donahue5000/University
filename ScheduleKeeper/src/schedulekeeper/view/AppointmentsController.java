package schedulekeeper.view;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static schedulekeeper.ScheduleKeeper.stage;
import schedulekeeper.model.Appointment;
import schedulekeeper.model.Customer;
import schedulekeeper.util.DBConnect;
import schedulekeeper.util.Errors;

public class AppointmentsController implements Initializable {

    @FXML
    private TableView<Appointment> appointmentsTable;
    @FXML
    private TableColumn<Appointment, String> startColumn;
    @FXML
    private TableColumn<Appointment, String> stopColumn;
    @FXML
    private TableColumn<Appointment, String> titleColumn;
    @FXML
    private TableColumn<Appointment, String> customerColumn;
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
    @FXML
    private TableColumn<Appointment, String> userColumn;
    @FXML
    private Label timeZoneLabel;
    @FXML
    private Label rangeLabel;

    private ObservableList<Appointment> appointmentListWeek;
    private ObservableList<Appointment> appointmentListMonth;
    private Customer selectedCustomer;
    private Appointment selectedAppointment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedCustomer = null;
        selectedAppointment = null;
        loadTable(DBConnect.getRecentAppointmentList());
        timeZoneLabel.setText(ZoneId.systemDefault().getDisplayName(TextStyle.FULL, Locale.US));
        rangeLabel.setText("All Appointments");
    }

    @FXML
    private void newAppointmentClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AppointmentNew.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - New Appointment");
        stage.show();
    }

    @FXML
    private void updateAppointmentClick(ActionEvent event) throws IOException {
        selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Errors.badSelection();
            return;
        }
        DBConnect.setPassedAppointment(selectedAppointment);
        Parent root = FXMLLoader.load(getClass().getResource("AppointmentUpdate.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Update Appointment");
        stage.show();
    }

    @FXML
    private void deleteClick(ActionEvent event) {
        selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Errors.badSelection();
            return;
        }
        DBConnect.deleteAppointment(selectedAppointment);
        loadTable(DBConnect.getRecentAppointmentList());
    }

    @FXML
    private void customerClick(ActionEvent event) throws IOException {
        selectedCustomer = DBConnect.getCustomerByID(appointmentsTable.getSelectionModel().getSelectedItem().getCustomerId());
        if (selectedCustomer == null) {
            Errors.badSelection();
            return;
        }
        DBConnect.setPassedCustomer(selectedCustomer);
        Parent root = FXMLLoader.load(getClass().getResource("CustomerUpdate.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Update Customer");
        stage.show();
    }

    @FXML
    private void mainMenuClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Menu");
        stage.show();
    }

    private void loadTable(ObservableList<Appointment> appointmentList) {
        if (appointmentList == null) {
            appointmentList = DBConnect.getAppointmentList();
        }
        appointmentsTable.setItems(appointmentList);
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startString"));
        startColumn.setSortType(TableColumn.SortType.ASCENDING);
        stopColumn.setCellValueFactory(new PropertyValueFactory<>("endString"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));

        appointmentsTable.getSortOrder().add(startColumn);
        appointmentsTable.getColumns().setAll(startColumn, stopColumn, titleColumn,
                customerColumn, locationColumn, descriptionColumn, userColumn);
    }

    @FXML
    private void allClick(ActionEvent event) {
        loadTable(DBConnect.getRecentAppointmentList());
        rangeLabel.setText("All Appointments");
    }

    
    
    
    //Behold, my tenuous grasp of Lambda Expressions! Used here in a stream 
    //to filter appointments by start time for displaying ranges.
    
    @FXML
    private void weekClick(ActionEvent event) {
        appointmentListWeek = FXCollections.observableArrayList(DBConnect.getRecentAppointmentList()
                .stream()
                .filter(x -> x.getStart()
                    .toInstant()
                    .isBefore(Instant.now().plus(7, ChronoUnit.DAYS)))
                .filter(x -> x.getStart()
                    .toInstant()
                    .isAfter(Instant.now()))
                .collect(Collectors.toList()));
        loadTable(appointmentListWeek);
        rangeLabel.setText("Upcoming 7 Days");
    }

    @FXML
    private void monthClick(ActionEvent event) {
        appointmentListMonth = FXCollections.observableArrayList(DBConnect.getRecentAppointmentList()
                .stream()
                .filter(x -> x.getStart()
                    .toInstant()
                    .isBefore(Instant.now().plus(30, ChronoUnit.DAYS)))
                .filter(x -> x.getStart()
                    .toInstant()
                    .isAfter(Instant.now()))
                .collect(Collectors.toList()));
        loadTable(appointmentListMonth);
        rangeLabel.setText("Upcoming Month");
    }

}
