package ScheduleClient.View;

import ScheduleClient.Model.Customer;
import static ScheduleClient.ScheduleClient.stage;
import ScheduleClient.Util.Connectatron;
import ScheduleClient.Util.Oops;
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
import javafx.scene.control.TextField;

public class CustomerUpdateController implements Initializable {

    @FXML
    private Label userLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField address2Field;
    @FXML
    private TextField cityField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField phoneField;

    private boolean isExisting;
    private Customer existingCustomer;

    public CustomerUpdateController() {
        isExisting = false;
    }

    public CustomerUpdateController(Customer c) {
        isExisting = true;
        existingCustomer = c;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userLabel.setText(Connectatron.USER);
        if (isExisting) {
            loadFields();
        }
    }

    public void setExisting(boolean b) {
        isExisting = b;
    }

    public void setExistingCustomer(Customer c) {
        existingCustomer = c;
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
    private void saveButtonClick(ActionEvent event) throws IOException {
        if (isExisting) {
            existingCustomer.setCustomerName(nameField.getText());
            existingCustomer.setAddress(addressField.getText());
            existingCustomer.setAddress2(address2Field.getText());
            existingCustomer.setCity(cityField.getText());
            existingCustomer.setCountry(countryField.getText());
            existingCustomer.setPostalCode(postalCodeField.getText());
            existingCustomer.setPhone(phoneField.getText());
            existingCustomer.setLastUpdateBy(Connectatron.USER);
        } else {
            Customer newCustomer = new Customer(
                    -1,
                    nameField.getText(),
                    -1,
                    1,
                    "",
                    Connectatron.USER,
                    "",
                    Connectatron.USER,
                    addressField.getText(),
                    address2Field.getText(),
                    -1,
                    postalCodeField.getText(),
                    phoneField.getText(),
                    cityField.getText(),
                    -1,
                    countryField.getText()
            );
            existingCustomer = newCustomer;
        }

        Connectatron.scrubAllID(existingCustomer);

        try {
            Connectatron.insertCustomer(existingCustomer);
        } catch (NullPointerException n) {
            Oops.blankField();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Customers.fxml"));
        CustomersController showCustomerInList = new CustomersController(
                Connectatron.getCustomerByID(existingCustomer.getCustomerId()));
        loader.setController(showCustomerInList);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Client - New Customer Added, Huzzah!");
        stage.show();

    }

    @FXML
    private void cancelButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Customers.fxml"));
        CustomersController showCustomerInList = new CustomersController();
        loader.setController(showCustomerInList);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Client - Customers");
        stage.show();
    }

    private void loadFields() {
        nameField.setText(existingCustomer.getCustomerName());
        addressField.setText(existingCustomer.getAddress());
        address2Field.setText(existingCustomer.getAddress2());
        cityField.setText(existingCustomer.getCity());
        countryField.setText(existingCustomer.getCountry());
        postalCodeField.setText(existingCustomer.getPostalCode());
        phoneField.setText(existingCustomer.getPhone());
    }

}
