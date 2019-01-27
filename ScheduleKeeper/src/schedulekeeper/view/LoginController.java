package schedulekeeper.view;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static schedulekeeper.ScheduleKeeper.stage;
import schedulekeeper.util.DBConnect;
import schedulekeeper.util.Errors;

public class LoginController implements Initializable {

    @FXML
    private Label topLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Locale.setDefault(new Locale("ru"));
        rb = ResourceBundle.getBundle("schedulekeeper.util.Language", Locale.getDefault());
        loginButton.setText(rb.getString("login"));
        topLabel.setText(rb.getString("welcome"));
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
    }

    @FXML
    private void loginButtonClick(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        passwordField.clear();
        if (username.length() < 1 || password.length() < 1) {
            Errors.emptyLoginField();
            return;
        }
        if (DBConnect.login(username, password)) {
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Schedule Keeper - Menu");
            stage.centerOnScreen();
            stage.show();
        }
    }

}
