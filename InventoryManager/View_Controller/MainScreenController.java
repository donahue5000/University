
package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainScreenController implements Initializable {

    @FXML
    private BorderPane mainScreenScene;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void exitButtonClick(ActionEvent event) throws IOException {
        System.exit(0);
    }

    @FXML
    private void addPartButtonClick(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass()
                .getResource("AddPart.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)mainScreenScene.getScene().getWindow();
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void modifyPartButtonClick(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass()
                .getResource("ModifyPart.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)mainScreenScene.getScene().getWindow();
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
        Parent loader = FXMLLoader.load(getClass()
                .getResource("AddProduct.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)mainScreenScene.getScene().getWindow();
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifyProductButtonClick(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass()
                .getResource("ModifyProduct.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)mainScreenScene.getScene().getWindow();
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteProductButtonClick(ActionEvent event) {
    }
    
}
