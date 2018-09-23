package View_Controller;

import InventoryManager.*;
import Model.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class AddPartController implements Initializable {

    @FXML
    private Pane addPartScene;
    @FXML
    private ToggleGroup addPartToggle;
    @FXML
    private Label machineIDcompanyNameLabel;
    @FXML
    private TextField partID;
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
    private TextField machineIDcompanyName;
    @FXML
    private RadioButton inHouseSelected;
    @FXML
    private RadioButton outsourcedSelected;
    private int partIDNew;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partIDNew = Inventory.getPartID();
        partID.setText("Auto Gen: " + Integer.toString(partIDNew));
    }

    @FXML
    private void cancelButtonClick(ActionEvent event) {
        //confirm Part discard
        Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
        cancelAlert.setTitle("Cancel");
        cancelAlert.setContentText("Part Not Saved");
        cancelAlert.showAndWait();
        if (cancelAlert.getResult() == ButtonType.OK) InventoryManager.toMain();
    }

    @FXML
    private void savePartButtonClick(ActionEvent event) {
        try {
            String nameNew = name.getText();
            int inStockNew = Integer.parseInt(inStock.getText());
            double priceNew = Double.parseDouble(price.getText());
            int maxNew = Integer.parseInt(max.getText());
            int minNew = Integer.parseInt(min.getText());
            String machineIDcompanyNameString = machineIDcompanyName.getText();

            if (inHouseSelected.isSelected()) {
                int machineID = Integer.parseInt(machineIDcompanyNameString);
                Part part = new InhousePart(partIDNew, nameNew, priceNew,
                        inStockNew, minNew, maxNew, machineID);
                Inventory.addPart(part);
            }
            if (outsourcedSelected.isSelected()) {
                Part part = new OutsourcedPart(partIDNew, nameNew, priceNew,
                        inStockNew, minNew, maxNew, machineIDcompanyNameString);
                Inventory.addPart(part);
            }
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
    private void inHouseToggleSelect(ActionEvent event) {
        machineIDcompanyNameLabel.setText("Machine ID");
    }

    @FXML
    private void outsourcedToggleSelect(ActionEvent event) {
        machineIDcompanyNameLabel.setText("Company Name");
    }



}