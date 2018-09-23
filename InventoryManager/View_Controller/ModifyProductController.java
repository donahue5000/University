package View_Controller;

import InventoryManager.*;
import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class ModifyProductController implements Initializable {

    @FXML
    private TextField productID;
    @FXML
    private AnchorPane modifyProductScene;
    @FXML
    private TextField name;
    @FXML
    private TextField inStock;
    @FXML
    private TextField price;
    @FXML
    private TextField max;
    @FXML
    private TextField min;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partIDcolumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInStockColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    @FXML
    private TableView<Part> addPartsTable;
    @FXML
    private TableColumn<Part, Integer> addPartIDcolumn;
    @FXML
    private TableColumn<Part, String> addPartNameColumn;
    @FXML
    private TableColumn<Part, Integer> addPartInStockColumn;
    @FXML
    private TableColumn<Part, Double> addPartPriceColumn;
    
    private Product product;
    private ObservableList<Part> tempPartsList;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize textfields
        product = MainScreenController.getModifiedProduct();
        productID.setText(Integer.toString(product.getProductID()));
        name.setText(product.getName());
        inStock.setText(Integer.toString(product.getInStock()));
        price.setText(Double.toString(product.getPrice()));
        max.setText(Integer.toString(product.getMax()));
        min.setText(Integer.toString(product.getMin()));
        
        //copy associatedParts to temp Part list for modifying pre-save
        tempPartsList = FXCollections.observableArrayList();
        product.getAssociatedParts().forEach(part -> tempPartsList.add(part));
        
        //initialize tables
        loadPartTable();
        loadAddPartTable();
    }    

    @FXML
    private void searchPartButtonClick(ActionEvent event) {
    }

    @FXML
    private void deletePartButtonClick(ActionEvent event) {
    }

    @FXML
    private void cancelButtonClick(ActionEvent event) throws IOException {
        //confirm Product discard
        Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
        cancelAlert.setTitle("Cancel");
        cancelAlert.setContentText("Product Changes Not Saved");
        cancelAlert.showAndWait();
        if (cancelAlert.getResult() == ButtonType.OK) InventoryManager.toMain();
    }

    @FXML
    private void saveButtonClick(ActionEvent event) {
        //check textfield format and update Part
        try {
            product.setName(name.getText());
            product.setPrice(Double.parseDouble(price.getText()));
            product.setInStock(Integer.parseInt(inStock.getText()));
            product.setMin(Integer.parseInt(min.getText()));
            product.setMax(Integer.parseInt(max.getText()));
            //assign Product associatedParts reference to modified list
            product.setAssociatedParts(tempPartsList);
        } catch (NumberFormatException e) {
            //alert dialog for textfield format problems
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("oh snap");
            alert.setContentText("Data Format Problem");
            alert.showAndWait();
            return;
        }
        InventoryManager.toMain();
    }

    @FXML
    private void addPartButtonClick(ActionEvent event) {
        tempPartsList.add(partsTable.getSelectionModel()
            .getSelectedItem());
    }
    
    private void loadPartTable(){
        partsTable.setItems(Inventory.getAllParts());
        partIDcolumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.getColumns().setAll(partIDcolumn, partNameColumn, 
                partInStockColumn, partPriceColumn);
    }
    
    private void loadAddPartTable(){
        addPartsTable.setItems(tempPartsList);
        addPartIDcolumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        addPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        addPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        addPartsTable.getColumns().setAll(addPartIDcolumn, addPartNameColumn, 
                addPartInStockColumn, addPartPriceColumn);
    }
    
}
