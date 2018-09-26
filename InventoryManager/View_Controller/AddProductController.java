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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
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
    @FXML
    private TextField partSearch;

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
    }

    @FXML
    private void deletePartButtonClick(ActionEvent event) {
        if (addPartsTable.getSelectionModel().getSelectedItem() != null) {
            associatedParts.remove(addPartsTable.getSelectionModel().getSelectedItem());
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
            String nameNew = name.getText();
            double priceNew = Double.parseDouble(price.getText());
            int inStockNew = Integer.parseInt(inStock.getText());
            int minNew = Integer.parseInt(min.getText());
            int maxNew = Integer.parseInt(max.getText());
            Product product = new Product(associatedParts, productIDnew,
                    nameNew, priceNew, inStockNew, minNew, maxNew);
            if (Inventory.productCheck(product)) {
                Inventory.addProduct(product);
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
            associatedParts.add(partsTable.getSelectionModel().getSelectedItem());
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
        addPartsTable.setItems(associatedParts);
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
