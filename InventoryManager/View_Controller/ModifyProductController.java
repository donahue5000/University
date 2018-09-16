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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ModifyProductController implements Initializable {

    @FXML
    private AnchorPane modifyProductScene;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
        Parent loader = FXMLLoader.load(getClass()
                .getResource("MainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)modifyProductScene.getScene().getWindow();
        stage.setTitle("Inventory Manager");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void saveButtonClick(ActionEvent event) {
    }

    @FXML
    private void addPartButtonClick(ActionEvent event) {
    }
    
}
