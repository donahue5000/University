
package schedulekeeper.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import static schedulekeeper.ScheduleKeeper.stage;
import schedulekeeper.model.Customer;
import schedulekeeper.util.DBConnect;
import schedulekeeper.util.Errors;


public class CustomerUpdateController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField address2Field;
    @FXML
    private TextField postalcodeField;
    @FXML
    private TextField phoneField;
    @FXML
    private ComboBox<String> cityBox;
    @FXML
    private TextField countryField;

    private int cityId;
    private int countryId;
    private Customer existingCustomer;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        address2Field.setText("");
        cityBox.setItems(FXCollections.observableArrayList("Phoenix", "New York", "London"));
        existingCustomer = DBConnect.getPassedCustomer();
        DBConnect.setPassedCustomer(null);
        loadFields();
    }    

    @FXML
    private void citySelect(ActionEvent event) {
        autoPopCountry();
    }

    @FXML
    private void saveClick(ActionEvent event) throws IOException {
        if (nameField.getText().isEmpty()
                || addressField.getText().isEmpty()
                || phoneField.getText().isEmpty()
                || postalcodeField.getText().isEmpty()
                || cityBox.getValue().isEmpty()) {
            Errors.emptyField();
            return;
        } else {
            existingCustomer.setCustomerName(nameField.getText());
            existingCustomer.setAddress(addressField.getText());
            existingCustomer.setAddress2(address2Field.getText());
            existingCustomer.setPostalCode(postalcodeField.getText());
            existingCustomer.setPhone(phoneField.getText());
            existingCustomer.setCity(cityBox.getValue());
            existingCustomer.setCityId(cityId);
            existingCustomer.setCountryId(countryId);
            existingCustomer.setCountry(countryField.getText());
            
            DBConnect.updateCustomer(existingCustomer);
            
            Parent root = FXMLLoader.load(getClass().getResource("Customers.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Schedule Keeper - Customers");
            stage.show();
        }
    }

    @FXML
    private void cancelClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Customers.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Keeper - Customers");
        stage.show();
    }
    
    private void loadFields(){
        if (existingCustomer != null){
            nameField.setText(existingCustomer.getCustomerName());
            addressField.setText(existingCustomer.getAddress());
            address2Field.setText(existingCustomer.getAddress2());
            postalcodeField.setText(existingCustomer.getPostalCode());
            phoneField.setText(existingCustomer.getPhone());
            cityBox.setValue(existingCustomer.getCity());
            autoPopCountry();
        }
    }
    
    private void autoPopCountry(){
        String city = cityBox.getSelectionModel().getSelectedItem();
        String country = "";
        switch (city) {
            case "Phoenix":
                country = "United States";
                cityId = 1;
                countryId = 1;
                break;
            case "New York":
                country = "United States";
                cityId = 2;
                countryId = 1;
                break;
            case "London":
                country = "England";
                cityId = 3;
                countryId = 2;
                break;
        }
        countryField.setText(country);
    }
    
}
