
package schedulekeeper.util;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;


public class Errors {
    
    public static void badCon(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle rb = ResourceBundle.getBundle("schedulekeeper.util.Language", Locale.getDefault());
        alert.setContentText(rb.getString("badGetCon"));
        alert.showAndWait();
    }
    
    public static void badClose(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle rb = ResourceBundle.getBundle("schedulekeeper.util.Language", Locale.getDefault());
        alert.setContentText(rb.getString("badCloseCon"));
        alert.showAndWait();
    }
    
    public static void badSQL(String statement){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Newb coder be like: " + statement);
        alert.showAndWait();
    }
    
    public static void emptyLoginField(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle rb = ResourceBundle.getBundle("schedulekeeper.util.Language", Locale.getDefault());
        alert.setContentText(rb.getString("blankField"));
        alert.showAndWait();
    }
    
    public static void emptyField(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Fill out all those boxes dummy.");
        alert.showAndWait();
    }

    public static void badUsername(String username) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle rb = ResourceBundle.getBundle("schedulekeeper.util.Language", Locale.getDefault());
        alert.setContentText(rb.getString("badUsername1") + username + rb.getString("badUsername2"));
        alert.showAndWait();
    }

    public static void badPassword(String username, String password) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle rb = ResourceBundle.getBundle("schedulekeeper.util.Language", Locale.getDefault());
        alert.setContentText(rb.getString("badPassword1") + username + rb.getString("badPassword2") + password);
        alert.showAndWait();
    }
    
    public static void badLog(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Trouble writing to log. You must have broken it.");
        alert.showAndWait();
    }
    
    
    
}
