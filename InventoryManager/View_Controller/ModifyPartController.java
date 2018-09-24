package View_Controller;

import InventoryManager.*;
import Model.InhousePart;
import Model.OutsourcedPart;
import Model.*;
import java.io.IOException;
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


public class ModifyPartController implements Initializable {

    @FXML
    private Pane modifyPartScene;
    @FXML
    private ToggleGroup modifyPartToggle;
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
    
    //references to modified objects
    private Part part;
    private InhousePart inhousePart;
    private OutsourcedPart outsourcedPart;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize textfields
        part = MainScreenController.getModifiedPart();
        partID.setText(Integer.toString(part.getPartID()));
        name.setText(part.getName());
        inStock.setText(Integer.toString(part.getInStock()));
        price.setText(Double.toString(part.getPrice()));
        max.setText(Integer.toString(part.getMax()));
        min.setText(Integer.toString(part.getMin()));
        
        //configure screen for InhousePart and downcast for MachineID access
        if (part instanceof InhousePart){
            inHouseSelected.setSelected(true);
            machineIDcompanyNameLabel.setText("Machine ID");
            inhousePart = (InhousePart) part;
            machineIDcompanyName.setText(
                    Integer.toString(inhousePart.getMachineID()));
        }
        
        //configure screen for OutsourcedPart and downcast for CompanyName access
        if (part instanceof OutsourcedPart){
            outsourcedSelected.setSelected(true);
            machineIDcompanyNameLabel.setText("Company Name");
            outsourcedPart = (OutsourcedPart) part;
            machineIDcompanyName.setText(outsourcedPart.getCompanyName());
        }
    }    

    @FXML
    private void cancelButtonClick(ActionEvent event) throws IOException {
        //confirm changes discard
        Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
        cancelAlert.setTitle("Cancel");
        cancelAlert.setContentText("Part Changes Not Saved");
        cancelAlert.showAndWait();
        if (cancelAlert.getResult() == ButtonType.OK) InventoryManager.toMain();
    }

    @FXML
    private void savePartButtonClick(ActionEvent event) {
        //check textfield format and update Part
        try {
            part.setName(name.getText());
            part.setPrice(Double.parseDouble(price.getText()));
            part.setInStock(Integer.parseInt(inStock.getText()));
            part.setMin(Integer.parseInt(min.getText()));
            part.setMax(Integer.parseInt(max.getText()));
            //use downcasted reference to update MachineID
            if (inHouseSelected.isSelected()) {
                inhousePart.setMachineID(Integer.parseInt(machineIDcompanyName
                        .getText()));
            }
            //use downcasted reference to update CompanyName
            if (outsourcedSelected.isSelected()) {
                outsourcedPart.setCompanyName(machineIDcompanyName.getText());
            }
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

}
