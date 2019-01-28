
package schedulekeeper.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static schedulekeeper.ScheduleKeeper.stage;
import schedulekeeper.model.Customer;
import schedulekeeper.util.DBConnect;
import schedulekeeper.util.Errors;


public class CustomersController implements Initializable {

    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> IDColumn;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;
    @FXML
    private TableColumn<Customer, String> address2Column;
    @FXML
    private TableColumn<Customer, String> cityColumn;
    @FXML
    private TableColumn<Customer, String> zipColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;
    
    private ObservableList<Customer> customerList;
    private Customer selectedCustomer;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedCustomer = null;
        loadTable();
    }    

    @FXML
    private void newCustomerClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerNew.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - New Customer");
        stage.show();
    }

    @FXML
    private void updateCustomerClick(ActionEvent event) throws IOException {
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null){
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
    private void deleteClick(ActionEvent event) {
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null){
            Errors.badSelection();
            return;
        }
        DBConnect.deleteCustomer(selectedCustomer);
        loadTable();
    }

    @FXML
    private void mainMenuClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Menu");
        stage.show();
    }
    
    private void loadTable(){
        customerList = DBConnect.getCustomerList();
        customerTable.setItems(customerList);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        IDColumn.setSortType(TableColumn.SortType.ASCENDING);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        address2Column.setCellValueFactory(new PropertyValueFactory<>("address2"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        customerTable.getSortOrder().add(IDColumn);
        customerTable.getColumns().setAll(IDColumn, nameColumn,
                phoneColumn, cityColumn, addressColumn, address2Column, zipColumn);
    }

    
}