
package View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class MainScreenController implements Initializable {

    @FXML
    private BorderPane MainScreen;
    @FXML
    private Button PartSearch;
    @FXML
    private Button PartAdd;
    @FXML
    private Button PartModify;
    @FXML
    private Button PartDelete;
    @FXML
    private TableView<?> PartTable;
    @FXML
    private Button Exit;
    @FXML
    private Button ProductSearch;
    @FXML
    private TableView<?> ProductTable;
    @FXML
    private Button ProductAdd;
    @FXML
    private Button ProductModify;
    @FXML
    private Button ProductDelete;
    @FXML
    private TextField PartSeachField;
    @FXML
    private TextField ProductSearchField;
    @FXML
    private Button ClearPartSearch;
    @FXML
    private Button ClearProductSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Exit(ActionEvent event) {
        System.exit(0);
    }
    
}
