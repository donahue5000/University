package ScheduleClient.View;

import ScheduleClient.Model.Customer;
import static ScheduleClient.ScheduleClient.stage;
import ScheduleClient.Util.Connectatron;
import ScheduleClient.Util.Oops;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Customer> customersTableView;
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
    private TableColumn<Customer, Integer> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;
    @FXML
    private TableColumn<Customer, String> cityColumn;
    @FXML
    private TableColumn<Customer, String> countryColumn;
    @FXML
    private TextField countryField;
    @FXML
    private TextField lastUpdateByField;

    private ObservableList<Customer> customerList;
    private Customer selectedCustomer;

    public CustomersController() {
        selectedCustomer = null;
    }

    public CustomersController(Customer newCustomer) {
        selectedCustomer = newCustomer;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userLabel.setText(Connectatron.USER);
        customerList = Connectatron.getCustomerList();
        loadTable();

        if (selectedCustomer != null) {
            //Lambda (runnable to select the new customer after scene finishes loading)
            Platform.runLater(() -> {
                customersTableView.getSelectionModel().select(selectedCustomer);
                showSelection();
            });
        }
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
    private void newButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerUpdate.fxml"));
        CustomerUpdateController newCustomer = new CustomerUpdateController();
        loader.setController(newCustomer);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Client - Adding Customer Record");
        stage.show();
    }

    @FXML
    private void updateButtonClick(ActionEvent event) throws IOException {
        if (selectedCustomer == null) {
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

    @FXML
    private void deleteButtonClick(ActionEvent event) throws IOException {
        selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            Connectatron.deleteCustomer(selectedCustomer.getCustomerId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Customers.fxml"));
            CustomersController showCustomerInList = new CustomersController();
            loader.setController(showCustomerInList);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Schedule Client - Customers");
            stage.show();
        }else{
            Oops.noSelection();
            return;
        }
    }

    private void loadTable() {
        if (customerList.size() < 1) {
            return;
        }
        customersTableView.setItems(customerList);
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

        customersTableView.getSortOrder().add(customerIdColumn);
        customersTableView.getColumns().setAll(customerIdColumn, nameColumn,
                phoneColumn, cityColumn, countryColumn);
    }

    private void showSelection() {
        selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            customerIDField.setText(selectedCustomer.customerIdProperty().getValue().toString());
            customerIDField.setText(selectedCustomer.customerIdProperty().getValue().toString());
            createDateField.setText(selectedCustomer.createDateProperty().getValue());
            createdByField.setText(selectedCustomer.createdByProperty().getValue());
            lastUpdateField.setText(selectedCustomer.lastUpdateProperty().getValue());
            nameField.setText(selectedCustomer.customerNameProperty().getValue());
            addressIDField.setText(selectedCustomer.addressIdProperty().getValue().toString());
            addressField.setText(selectedCustomer.addressProperty().getValue());
            address2Field.setText(selectedCustomer.address2Property().getValue());
            cityField.setText(selectedCustomer.cityProperty().getValue());
            postalCodeField.setText(selectedCustomer.postalCodeProperty().getValue());
            phoneField.setText(selectedCustomer.phoneProperty().getValue());
            countryField.setText(selectedCustomer.countryProperty().getValue());
            lastUpdateByField.setText(selectedCustomer.lastUpdateByProperty().getValue());
        }
    }

    @FXML
    private void customersTableViewClick(MouseEvent event) {
        showSelection();
    }

    
}