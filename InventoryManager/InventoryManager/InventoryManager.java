package InventoryManager;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.input.DataFormat.URL;
import javafx.stage.Stage;

/**
 *
 * @author Brian Donahue
 */
public class InventoryManager extends Application {

    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass()
                .getResource("/View_Controller/MainScreen.fxml"));
        scene = new Scene(root);
        stage = primaryStage;
        toMain();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void toMain() {
        stage.setTitle("Inventory Manager");
        stage.setScene(scene);
    }

}
