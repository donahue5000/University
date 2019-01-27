package schedulekeeper.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import schedulekeeper.model.Appointment;
import schedulekeeper.model.Customer;
import schedulekeeper.util.DBConnect;

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

    private ObservableList<Appointment> appointmentList;
    private Customer selectedCustomer;
    private Appointment selectedAppointment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedCustomer = null;
        selectedAppointment = null;
        loadTable();
    }

    @FXML
    private void newAppointmentClick(ActionEvent event) {
    }

    @FXML
    private void updateAppointmentClick(ActionEvent event) {
    }

    @FXML
    private void deleteClick(ActionEvent event) {
    }

    @FXML
    private void customerClick(ActionEvent event) {
    }
    
    @FXML
    private void mainMenuClick(ActionEvent event) {
    }
    
    private void loadTable(){
        appointmentList = DBConnect.getAppointmentList();
        appointmentsTable.setItems(appointmentList);
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        startColumn.setSortType(TableColumn.SortType.ASCENDING);
        stopColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));

        appointmentsTable.getSortOrder().add(startColumn);
        appointmentsTable.getColumns().setAll(startColumn, stopColumn, titleColumn, 
                customerColumn, locationColumn, descriptionColumn, userColumn);
    }


}
