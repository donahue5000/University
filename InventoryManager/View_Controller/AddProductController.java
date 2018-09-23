package View_Controller;

import InventoryManager.*;
import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class AddProductController implements Initializable {

    @FXML
    private AnchorPane addProductScene;
    @FXML
    private TextField productID;
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
    
    private ObservableList<Part> associatedParts;
    private int productIDnew;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        associatedParts = FXCollections.observableArrayList();
        loadPartTable();
        loadAddPartTable();
        
        //get next Product ID
        productIDnew = Inventory.getProductID();
        
        productID.setText("Auto Gen: " + Integer.toString(productIDnew));
        
    }    
    

    @FXML
    private void searchPartButtonClick(ActionEvent event) {
    }

    @FXML
    private void deletePartButtonClick(ActionEvent event) {
    }

    @FXML
    private void cancelButtonClick(ActionEvent event) throws IOException {
        //todo confirmation
        InventoryManager.toMain();
    }

    @FXML
    private void saveButtonClick(ActionEvent event) {
        try {
            String nameNew = name.getText();
            int inStockNew = Integer.parseInt(inStock.getText());
            double priceNew = Double.parseDouble(price.getText());
            int maxNew = Integer.parseInt(max.getText());
            int minNew = Integer.parseInt(min.getText());
            Product product = new Product(associatedParts, productIDnew, 
                    nameNew, priceNew, inStockNew, minNew, maxNew);
                Inventory.addProduct(product);

             
        } catch (NumberFormatException e) {
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
        associatedParts.add(partsTable.getSelectionModel().getSelectedItem());
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
        addPartsTable.setItems(associatedParts);
        addPartIDcolumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        addPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        addPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        addPartsTable.getColumns().setAll(addPartIDcolumn, addPartNameColumn, 
                addPartInStockColumn, addPartPriceColumn);
    }
    
}
