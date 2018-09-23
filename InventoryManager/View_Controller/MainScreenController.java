package View_Controller;

import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {

    @FXML
    private BorderPane mainScreenScene;
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
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> productIDcolumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInStockColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;
    
    //references for passing Parts to modify screens
    private static Part modifiedPart;
    private static Product modifiedProduct;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTables();
        //reset selected table references for modify screens
        modifiedPart = null;
        modifiedProduct = null;
    }

    @FXML
    private void exitButtonClick(ActionEvent event) throws IOException {
        System.exit(0);
    }

    @FXML
    private void addPartButtonClick(ActionEvent event) throws IOException {
        //load add Part screen
        Parent loader = FXMLLoader.load(getClass()
                .getResource("AddPart.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) mainScreenScene.getScene().getWindow();
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifyPartButtonClick(ActionEvent event) throws IOException {
        //ensure Part table item is selected
        if (partsTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("oh snap");
            alert.setContentText("Select a part to modify");
            alert.showAndWait();
            return;
        }
        
        //store selected Part reference
        modifiedPart = partsTable.getSelectionModel().getSelectedItem();
        
        //load modify Part screen
        Parent loader = FXMLLoader.load(getClass()
                .getResource("ModifyPart.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) mainScreenScene.getScene().getWindow();
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clearPartSearchButtonClick(ActionEvent event) {
    }

    @FXML
    private void partSearchButtonClick(ActionEvent event) {
    }

    @FXML
    private void deletePartButtonClick(ActionEvent event) {
    }

    @FXML
    private void clearProductButtonClick(ActionEvent event) {
    }

    @FXML
    private void productSearchButtonClick(ActionEvent event) {
    }

    @FXML
    private void addProductButtonClick(ActionEvent event) throws IOException {
        //load add Product screen
        Parent loader = FXMLLoader.load(getClass()
                .getResource("AddProduct.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) mainScreenScene.getScene().getWindow();
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifyProductButtonClick(ActionEvent event) throws IOException {
        //ensure Product table item is selected
        if (productsTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("oh snap");
            alert.setContentText("Select a product to modify");
            alert.showAndWait();
            return;
        }
        //store selected Product reference
        modifiedProduct = productsTable.getSelectionModel().getSelectedItem();
        
        //load modify Product screen
        Parent loader = FXMLLoader.load(getClass()
                .getResource("ModifyProduct.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) mainScreenScene.getScene().getWindow();
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteProductButtonClick(ActionEvent event) {
    }
    
    private void loadTables(){
        //populate Part table
        partsTable.setItems(Inventory.getAllParts());
        partIDcolumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.getColumns().setAll(partIDcolumn, partNameColumn, 
                partInStockColumn, partPriceColumn);

        //populate Product table
        productsTable.setItems(Inventory.getProducts());
        productIDcolumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.getColumns().setAll(productIDcolumn, productNameColumn, 
                productInStockColumn, productPriceColumn);
    }
    
    //Part reference getters for modify screens
    public static Part getModifiedPart(){
        return modifiedPart;
    }
    public static Product getModifiedProduct(){
        return modifiedProduct;
    }

}
