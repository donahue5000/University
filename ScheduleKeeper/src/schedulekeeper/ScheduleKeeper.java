package schedulekeeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScheduleKeeper extends Application {

    public static Stage stage;

    @Override
    public void start(Stage stageStart) throws Exception {
        stage = stageStart;
        Parent root = FXMLLoader.load(getClass().getResource(
                "/schedulekeeper/view/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}