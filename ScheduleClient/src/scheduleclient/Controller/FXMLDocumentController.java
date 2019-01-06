package scheduleclient.Controller;



import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.SQLException;
import static scheduleclient.util.Connectatron.*;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void testClicked(ActionEvent event) {
        System.out.println("Test Button Clicked");
        label.setText("^ clicked");

        Connection con = null;
        try {
            con = getCon();
            System.out.println("connected yo");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally{
            try{
                if (con != null) con.close();
            }catch(SQLException e){}
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
