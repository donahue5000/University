package InventoryManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.*;
import javafx.collections.FXCollections;

/**
 *
 * Launcher
 */
public class InventoryManager extends Application {

    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadSampleData();
        Parent root = FXMLLoader.load(getClass()
                .getResource("/View_Controller/MainScreen.fxml"));
        scene = new Scene(root);
        stage = primaryStage;
        toMain();
        stage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     */
    public static void toMain() {
        stage.setTitle("Inventory Manager");
        stage.setScene(scene);
    }
    
    private static void loadSampleData(){
        Part part1 = new InhousePart(Inventory.getPartID(), "Square Thing", 5, 100, 10, 1000, 2357);
        Inventory.addPart(part1);
        Part part2 = new OutsourcedPart(Inventory.getPartID(), "Bobble", 36, 22, 5, 50, "Bobbles fo Dayz");
        Inventory.addPart(part2);
        Part part3 = new InhousePart(Inventory.getPartID(), "Round Thing", 345, 4, 1, 10, 26);
        Inventory.addPart(part3);
        Part part4 = new OutsourcedPart(Inventory.getPartID(), "Doobery", 85000, 3, 1, 10, "Doobery Warehouse");
        Inventory.addPart(part4);
        
        Product product1 = new Product(FXCollections.observableArrayList(part1), Inventory.getProductID(), "Coaster", 5.99, 3, 0, 500);
        Inventory.addProduct(product1);
        Product product2 = new Product(FXCollections.observableArrayList(part1, part2), Inventory.getProductID(), "Bookend", 41.99, 2, 0, 100);
        Inventory.addProduct(product2);
        Product product3 = new Product(FXCollections.observableArrayList(part1, part2, part3), Inventory.getProductID(), "Doorstop", 386.99, 1, 0, 50);
        Inventory.addProduct(product3);
        Product product4 = new Product(FXCollections.observableArrayList(part1, part2, part3, part4), Inventory.getProductID(), "Paperweight", 899999.99, 1, 0, 5);
        Inventory.addProduct(product4);
    }

}
