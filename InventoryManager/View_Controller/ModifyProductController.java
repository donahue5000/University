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
import javafx.scene.control.TableCell;
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
    @FXML
    private TextField partSearch;

    private Product product;
    private Product newProduct;
    private ObservableList<Part> tempPartsList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        product = MainScreenController.getModifiedProduct();
        productID.setText(Integer.toString(product.getProductID()));
        name.setText(product.getName());
        inStock.setText(Integer.toString(product.getInStock()));
        price.setText(Double.toString(product.getPrice()));
        max.setText(Integer.toString(product.getMax()));
        min.setText(Integer.toString(product.getMin()));

        tempPartsList = FXCollections.observableArrayList();
        product.getAssociatedParts().forEach(part -> tempPartsList.add(part));

        loadPartTable();
        loadAddPartTable();
    }

    @FXML
    private void searchPartButtonClick(ActionEvent event) {
        try {
            if (partSearch.getText().trim().equals("")) {
                Inventory.alertSearchEmpty();
                return;
            }
            Part searchedPart = Inventory.lookupPart(Integer.parseInt(partSearch
                    .getText()));
            if (searchedPart != null) {
                partsTable.getSelectionModel().select(searchedPart);
                partsTable.scrollTo(searchedPart);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("ID " + partSearch.getText() + " Not Found");
                alert.showAndWait();
            }
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid ID");
            alert.showAndWait();
        }
    }

    @FXML
    private void deletePartButtonClick(ActionEvent event) {
        if (addPartsTable.getSelectionModel().getSelectedItem() != null) {
            tempPartsList.remove(addPartsTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void cancelButtonClick(ActionEvent event) throws IOException {
        if (Inventory.alertCancel() == ButtonType.OK) {
            InventoryManager.toMain();
        }
    }

    @FXML
    private void saveButtonClick(ActionEvent event) {
        try {
            if (tempPartsList.size() < 1){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("All parts removed.\n"
                        + "You can't sell a box of air. Maybe delete "
                        + "this product?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK){
                    Inventory.removeProduct(product.getProductID());
                    InventoryManager.toMain();
                    return;
                }else return;
            }
            int productIDnew = Integer.parseInt(productID.getText());
            String nameNew = name.getText();
            double priceNew = Double.parseDouble(price.getText());
            int inStockNew = Integer.parseInt(inStock.getText());
            int minNew = Integer.parseInt(min.getText());
            int maxNew = Integer.parseInt(max.getText());
            newProduct = new Product(tempPartsList, productIDnew,
                    nameNew, priceNew, inStockNew, minNew, maxNew);
            if (Inventory.productCheck(newProduct)) {
                Inventory.replaceProduct(product, newProduct);
            } else {
                return;
            }
        } catch (NumberFormatException e) {
            Inventory.alertFormat();
            return;
        }
        InventoryManager.toMain();
    }

    @FXML
    private void addPartButtonClick(ActionEvent event) {
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            tempPartsList.add(partsTable.getSelectionModel()
                    .getSelectedItem());
        }
    }

    private void loadPartTable() {
        partsTable.setItems(Inventory.getAllParts());
        partIDcolumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partPriceColumn.setCellFactory(cell -> new TableCell<Part, Double>() {
            @Override
            public void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("$%,.2f", price));
                }
            }
        });
        partsTable.getColumns().setAll(partIDcolumn, partNameColumn,
                partInStockColumn, partPriceColumn);
    }

    private void loadAddPartTable() {
        addPartsTable.setItems(tempPartsList);
        addPartIDcolumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        addPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        addPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        addPartPriceColumn.setCellFactory(cell -> new TableCell<Part, Double>() {
            @Override
            public void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("$%,.2f", price));
                }
            }
        });
        addPartsTable.getColumns().setAll(addPartIDcolumn, addPartNameColumn,
                addPartInStockColumn, addPartPriceColumn);
    }

}
