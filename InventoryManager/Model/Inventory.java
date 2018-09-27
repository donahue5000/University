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
        String alertText = "";
        boolean result = true;
        if (part.getName().trim().length() < 1) {
            alertText += "The Purchasing Department kindly suggests giving"
                    + " names to parts.\n";
            result = false;
        }
        if (part.getInStock() < part.getMin()
                || part.getInStock() > part.getMax()) {
            alertText += "\nInventory out of range.\n";
            result = false;
        }
        if (part.getMax() < part.getMin()) {
            alertText += "\nInventory maximum less than minimum.\n";
            result = false;
        }
        if (part.getMin() > part.getMax()) {
            alertText += "\nInventory minimum more than maximum.\n"
                    + "(This exception control approved by the\n"
                    + "Department of Redundancy Department)\n";
            result = false;
        }
        if (!result) {
            failedCheck(alertText);
        }
        return result;

    }

    public static boolean productCheck(Product product) {
        boolean result = true;
        String alertText = "";
        if (product.getName().trim().length() < 1) {
            alertText += "The Marketing Department kindly suggests naming "
                    + "products, citing potential loss of sales from blank "
                    + "billboards.\n";
            result = false;
        }
        if (product.getInStock() < product.getMin()
                || product.getInStock() > product.getMax()) {
            alertText += "\nInventory out of range.\n";
            result = false;
        }
        if (product.getMax() < product.getMin()) {
            alertText += "\nInventory maximum less than minimum.\n";
            result = false;
        }
        if (product.getMin() > product.getMax()) {
            alertText += "\nInventory minimum more than maximum.\n"
                    + "(This exception control approved by the\n"
                    + "Department of Redundancy Department)\n";
            result = false;
        }
        if (product.getAssociatedParts().size() < 1) {
            alertText += "\nThe Operations Department suggests "
                    + "assigning at least one part to each product, kindly "
                    + "reminding the user that 'stuff is made of other stuff.'\n";
            result = false;
        }
        double partSum = 0;
        for (Part part : product.getAssociatedParts()) {
            partSum += part.getPrice();
        }
        if (partSum > product.getPrice()) {
            alertText += "\nCost of parts: "
                    + String.format("$%,.2f", partSum)
                    + "\nProduct selling price: "
                    + String.format("$%,.2f", product.getPrice())
                    + "\nThe Accounting Department kindly suggests "
                    + "Googling the phrase 'profit margin'.\n";
            result = false;
        }
        if (!result) {
            failedCheck(alertText);
        }
        return result;
    }

    public static void failedCheck(String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(alertText);
        alert.showAndWait();
    }

    public static void alertDeleteProductWithParts() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Remove all parts via Modify to delete Product");
        alert.showAndWait();
    }

    public static void alertFormat() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("All those little boxes need stuff in them.\n"
                + "Tip: put numbers where numbers go and words where words go");
        alert.showAndWait();
    }

    public static ButtonType alertCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Discard changes?");
        alert.showAndWait();
        return alert.getResult();
    }

    public static void alertSearchEmpty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Search Field Empty");
        alert.showAndWait();
    }
}
