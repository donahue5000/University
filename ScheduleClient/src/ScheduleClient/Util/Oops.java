
package ScheduleClient.Util;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;


public class Oops {
    
    
    
    public static void badGetCon(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle rb = ResourceBundle.getBundle("ScheduleClient.Util.Language", Locale.getDefault());
        alert.setContentText(rb.getString("badGetCon"));
        alert.showAndWait();
    }
    
    public static void badCloseCon(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle rb = ResourceBundle.getBundle("ScheduleClient.Util.Language", Locale.getDefault());
        alert.setContentText(rb.getString("badCloseCon"));
        alert.showAndWait();
    }
    
    public static void badUsername(String username){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle rb = ResourceBundle.getBundle("ScheduleClient.Util.Language", Locale.getDefault());
        alert.setContentText(username + " " + rb.getString("usernameError"));
        alert.showAndWait();
    }
    
    public static void badPassword(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle rb = ResourceBundle.getBundle("ScheduleClient.Util.Language", Locale.getDefault());
        alert.setContentText(rb.getString("passwordError"));
        alert.showAndWait();
    }
    
    public static void blankField(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle rb = ResourceBundle.getBundle("ScheduleClient.Util.Language", Locale.getDefault());
        alert.setContentText(rb.getString("blankField"));
        alert.showAndWait();
    }
    
    
    
    
    
}
