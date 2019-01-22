package ScheduleClient.View;

import static ScheduleClient.ScheduleClient.stage;
import ScheduleClient.Util.Connectatron;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import static ScheduleClient.Util.Connectatron.*;
import ScheduleClient.Util.Oops;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;

    ResourceBundle rb = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Locale.setDefault(new Locale("ru"));
        rb = ResourceBundle.getBundle("ScheduleClient.Util.Language", Locale.getDefault());
        loginButton.setText(rb.getString("login"));
        welcomeLabel.setText(rb.getString("welcome"));
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
    }

    @FXML
    private void loginButtonClick(ActionEvent event) throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();
        int userID;
        passwordField.setText("");
        String checkedPassword = "";

        if (username.equals("") || password.equals("")) {
            Oops.blankField();
            return;
        }

        Connection con = getCon();

        if (con != null) {
            try {
                PreparedStatement st = con.prepareStatement("SELECT password, userId FROM user "
                        + "WHERE userName = ?");
                st.setString(1, username);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    checkedPassword = rs.getString(1);
                    userID = rs.getInt(2);
                } else {
                    Oops.badUsername(username);
                    closeCon(con);
                    return;
                }
            } catch (SQLException e) {
                Oops.badGetCon();
                closeCon(con);
                return;
            }

            if (password.equals(checkedPassword)) {
                Connectatron.USER = username;
                Connectatron.USERID = userID;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Appointments.fxml"));
                AppointmentsController showAppointments = new AppointmentsController();
                loader.setController(showAppointments);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Schedule Client - Appointments");
                stage.centerOnScreen();
                stage.show();
            } else {
                Oops.badPassword();
                closeCon(con);
                return;
            }

            closeCon(con);
        }
    }

}
