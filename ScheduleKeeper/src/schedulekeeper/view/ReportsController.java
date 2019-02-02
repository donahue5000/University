package schedulekeeper.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import static schedulekeeper.ScheduleKeeper.stage;
import schedulekeeper.model.Appointment;
import schedulekeeper.model.Customer;
import schedulekeeper.util.DBConnect;

public class ReportsController implements Initializable {

    @FXML
    private ComboBox<Object> optionComboBox;
    @FXML
    private TextArea reportOutput;
    @FXML
    private Label optionLabel;

    ObservableList<Object> customerList;
    ObservableList<Appointment> appointmentList;
    Object selectedOption;
    boolean typeMode;
    boolean consultantMode;
    boolean customerMode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerList = FXCollections.observableArrayList(DBConnect.getCustomerList());
        appointmentList = DBConnect.getRecentAppointmentList();
    }

    @FXML
    private void optionSelect(ActionEvent event) {
        optionClick();
    }

    private void optionClick() {
        selectedOption = optionComboBox.getSelectionModel().getSelectedItem();
        if (selectedOption != null) {
            buildReport();
        }
    }

    @FXML
    private void appointmentTypeClick(ActionEvent event) {
        typeMode = true;
        consultantMode = false;
        customerMode = false;
        optionLabel.setText("Appointment Type:");
        optionComboBox.setItems(FXCollections.observableArrayList("Training", "Installation", "Repair"));
        optionComboBox.getSelectionModel().selectFirst();
        optionClick();
    }

    @FXML
    private void consultantClick(ActionEvent event) {
        typeMode = false;
        consultantMode = true;
        customerMode = false;
        optionLabel.setText("Consultant: ");
        optionComboBox.setItems(FXCollections.observableArrayList("user1", "user2", "test"));
        optionComboBox.getSelectionModel().selectFirst();
        optionClick();
    }

    @FXML
    private void customerAppointmentsClick(ActionEvent event) {
        typeMode = false;
        consultantMode = false;
        customerMode = true;
        optionLabel.setText("Customer: ");
        if (customerList.size() < 1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No customers in database");
            alert.showAndWait();
            return;
        }
        optionComboBox.setItems(customerList);
        optionComboBox.getSelectionModel().selectFirst();
        optionClick();
    }

    @FXML
    private void menuClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Menu");
        stage.show();
    }

    private void buildReport() {
        String report = "";

        if (typeMode) {
            String type = optionComboBox.getValue().toString();
            for (int i = LocalDate.now().getMonthValue();;) {
                int typeCount = 0;
                for (Appointment ap : appointmentList) {
                    if (ap.getStart().getMonthValue() == i && ap.getType().equals(type)) {
                        typeCount++;
                    }
                }
                report += (Month.of(i).toString() + ":" + System.lineSeparator()
                        + typeCount + System.lineSeparator() + System.lineSeparator());

                i++;
                if (i > 12) {
                    i = 1;
                }
                if (i == LocalDate.now().getMonthValue()) {
                    break;
                }
            }

        } else if (consultantMode) {
            String user = optionComboBox.getValue().toString();
            //Lambdas for the filter predicate and sort comparator
            //Logical Justification: do a bunch of stuff while keepin' it pretty
            List<Appointment> userApps = new ArrayList<>(appointmentList
                    .stream()
                    .filter(x -> x.getUser().equals(user))
                    .sorted((Appointment x1, Appointment x2) -> {
                        return x1.getStart().compareTo(x2.getStart());
                    })
                    .collect(Collectors.toList()));
            for (Appointment ap : userApps) {
                ZoneId officeZoneID = null;
                switch (ap.getLocation()) {
                    case "Phoenix":
                        officeZoneID = ZoneId.of("America/Phoenix");
                        break;
                    case "New York":
                        officeZoneID = ZoneId.of("America/New_York");
                        break;
                    case "London":
                        officeZoneID = ZoneId.of("Europe/London");
                        break;
                }
                report += (ap.getTitle()
                        + " with " + ap.getCustomer()
                        + " on " + ap.getStart().toInstant().atZone(officeZoneID).format(DateTimeFormatter.ofPattern("EEEE, MMMM dd"))
                        + " at " + ap.getStart().toInstant().atZone(officeZoneID).format(DateTimeFormatter.ofPattern("hh:mm a"))
                        + " local time in " + ap.getLocation() + "." + System.lineSeparator());
            }

        } else if (customerMode) {
            int selectedCustomerID = ((Customer) optionComboBox.getValue()).getCustomerId();
            //Lambdas for the filter predicate and sort comparator
            //Logical Justification: do a bunch of stuff while keepin' it pretty
            List<Appointment> customerAppointments = new ArrayList<>(appointmentList
                    .stream()
                    .filter(x -> x.getCustomerId() == selectedCustomerID)
                    .sorted((Appointment x1, Appointment x2) -> {
                        return x1.getStart().compareTo(x2.getStart());
                    })
                    .collect(Collectors.toList()));
            for (Appointment ap : customerAppointments) {
                ZoneId officeZoneID = null;
                switch (ap.getLocation()) {
                    case "Phoenix":
                        officeZoneID = ZoneId.of("America/Phoenix");
                        break;
                    case "New York":
                        officeZoneID = ZoneId.of("America/New_York");
                        break;
                    case "London":
                        officeZoneID = ZoneId.of("Europe/London");
                        break;
                }
                report += (ap.getCustomer()
                        + " is expecting " + ap.getUser()
                        + " at " + ap.getStart().toInstant().atZone(officeZoneID).format(DateTimeFormatter.ofPattern("hh:mm a"))
                        + " local time on " + ap.getStart().toInstant().atZone(officeZoneID).format(DateTimeFormatter.ofPattern("EEEE, MMMM dd"))
                        + " in " + ap.getLocation()
                        + " for " + ap.getTitle() + "." + System.lineSeparator());
            }
        }
        reportOutput.setText(report);
    }

}
