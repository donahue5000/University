package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Inventory {

    private static ObservableList<Product> products
            = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts
            = FXCollections.observableArrayList();
    private static int partIDtracker = 1;
    private static int ProductIDtracker = 1;

    public static void addProduct(Product product) {
        products.add(product);
        ProductIDtracker++;
    }

    public static boolean removeProduct(int productID) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setContentText("Confirm Delete");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            return products.remove(lookupProduct(productID));
        } else {
            return false;
        }
    }

    public static Product lookupProduct(int productID) {
        Product result = null;
        for (Product i : products) {
            if (i.getProductID() == productID) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Deprecated
    public static void updateProduct(int productID) {
    }

    public static void addPart(Part part) {
        allParts.add(part);
        partIDtracker++;
    }

    public static boolean deletePart(Part part) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setContentText("Confirm Delete");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            return allParts.remove(part);
        } else {
            return false;
        }
    }

    public static Part lookupPart(int partID) {
        Part result = null;
        for (Part i : allParts) {
            if (i.getPartID() == partID) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Deprecated
    public static void updatePart(int partID) {
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getProducts() {
        return products;
    }

    public static int getPartID() {
        return partIDtracker;
    }

    public static int getProductID() {
        return ProductIDtracker;
    }

    public static void replacePart(Part oldPart, Part newPart) {
        allParts.set(allParts.indexOf(oldPart), newPart);
    }

    public static boolean partCheck(Part part) {
        boolean result = true;
        if (part.getInStock() < part.getMin()
                || part.getInStock() > part.getMax()) {
            alertInv();
            result = false;
        }
        if (part.getMax() < part.getMin()) {
            alertMax();
            result = false;
        }
        if (part.getMin() > part.getMax()) {
            alertMin();
            result = false;
        }
        return result;

    }

    public static boolean productCheck(Product product) {
        boolean result = true;
        
        return result;
    }

    //alerts
    public static void alertInv() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Inventory out of Specified Limits");
        alert.showAndWait();
    }

    public static void alertMax() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Max lower than Min");
        alert.showAndWait();
    }

    public static void alertMin() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Min higher than Max");
        alert.showAndWait();
    }
    

}
