package schedulekeeper.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import static schedulekeeper.ScheduleKeeper.stage;
import schedulekeeper.util.DBConnect;
import schedulekeeper.util.Errors;

public class MenuController implements Initializable {

    @FXML
    private Label welcomeLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label zoneLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Welcome, " + DBConnect.getUser());
        zoneLabel.setText(ZoneId.systemDefault().toString() + " UTC" 
                + ZoneId.systemDefault().getRules().getOffset(Instant.now()));
        dateLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm")));
    }

    @FXML
    private void appointmentsClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Appointments");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void customersClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Customers.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Customers");
        stage.show();
    }

    @FXML
    private void reportsClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Reports");
        stage.show();
    }

    @FXML
    private void userlogClick(ActionEvent event) {
        try {
            Desktop.getDesktop().open(new File("log.txt"));
        }catch (Exception e){
            Errors.badLog();
        }
    }

    @FXML
    private void logoutClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Login");
        stage.centerOnScreen();
        stage.show();
    }

}
