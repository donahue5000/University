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

    public static void replaceProduct(Product oldProduct, Product newProduct) {
        products.set(products.indexOf(oldProduct), newProduct);
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
        if (product.getInStock() < product.getMin()
                || product.getInStock() > product.getMax()) {
            alertInv();
            result = false;
        }
        if (product.getMax() < product.getMin()) {
            alertMax();
            result = false;
        }
        if (product.getMin() > product.getMax()) {
            alertMin();
            result = false;
        }
        if (product.getAssociatedParts().size() < 1) {
            alertPartMissing(product);
            result = false;
        }
        return result;
    }

    //alerts
    public static void alertInv() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Inventory out of range");
        alert.showAndWait();
    }

    public static void alertMax() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Max lower than Min");
        alert.showAndWait();
    }

    public static void alertMin() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Min higher than Max\n\n\n"
                + "Yes, checking for both max under min\n"
                + "and min over max is redundant.\n\n"
                + "Required by the course checklist though, so here ya go :)");
        alert.showAndWait();
    }

    public static void alertPartMissing(Product product) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Product must have one part.\n"
                + "Trying to sell a box of nothing, are we?");
        alert.showAndWait();
    }

    public static void alertDeleteProductWithParts() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Remove all parts via Modify to delete Product");
        alert.showAndWait();
    }

    public static void alertFormat() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("oh snap");
        alert.setContentText("Uhhh, those boxes need numbers and words...\n"
                + "Tip: put numbers where numbers go and words where words go");
        alert.showAndWait();
    }

    public static ButtonType alertCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setContentText("Discard changes?");
        alert.showAndWait();
        return alert.getResult();
    }

    public static void alertSearchEmpty() {
        Alert empty = new Alert(Alert.AlertType.ERROR);
        empty.setTitle("Error");
        empty.setContentText("Search Field Empty");
        empty.showAndWait();
    }

}
