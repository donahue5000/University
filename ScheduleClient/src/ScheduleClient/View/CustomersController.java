package ScheduleClient.View;

import static ScheduleClient.ScheduleClient.stage;
import ScheduleClient.Util.Connectatron;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CustomersController implements Initializable {

    @FXML
    private Label userLabel;
    @FXML
    private TextField customerIDField;
    @FXML
    private TextField createDateField;
    @FXML
    private TextField createdByField;
    @FXML
    private TextField lastUpdateField;
    @FXML
    private TableView<?> customersTableView;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressIDField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField address2Field;
    @FXML
    private TextField cityField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField phoneField;
    @FXML
    private TableColumn<?, ?> customerIdColumn;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> phoneColumn;
    @FXML
    private TableColumn<?, ?> cityColumn;
    @FXML
    private TableColumn<?, ?> cityColumn1;
    @FXML
    private TextField countryField;
    @FXML
    private TextField lastUpdateByField;
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userLabel.setText(Connectatron.USER);
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
        Parent root = FXMLLoader.load(getClass().getResource(
                "Customers.fxml"));
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
    private void newButtonClick(ActionEvent event) {
    }

    @FXML
    private void updateButtonClick(ActionEvent event) {
    }

    @FXML
    private void deleteButtonClick(ActionEvent event) {
    }

    @FXML
    private void customersTableViewClick(MouseEvent event) {
    }

}
