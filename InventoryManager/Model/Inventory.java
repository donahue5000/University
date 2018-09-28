package Model;

import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Brian Donahue
 */
public class Inventory {

    private static ObservableList<Product> products
            = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts
            = FXCollections.observableArrayList();
    private static int partIDtracker = 1;
    private static int ProductIDtracker = 1;

    /**
     *
     * @param product
     */
    public static void addProduct(Product product) {
        products.add(product);
        ProductIDtracker++;
    }

    /**
     *
     * @param productID
     * @return result of confirmation alert
     */
    public static boolean removeProduct(int productID) {

        if (alertDelete()) {
            return products.remove(lookupProduct(productID));
        } else {
            return false;
        }
    }

    /**
     *
     * @param productID
     * @return Product object with matching productID property
     */
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

    /**
     * use replaceProduct()
     * @param productID
     * @deprecated
     */
    @Deprecated
    public static void updateProduct(int productID) {
    }

    /**
     *
     * @param part
     */
    public static void addPart(Part part) {
        allParts.add(part);
        partIDtracker++;
    }

    /**
     *
     * @param part
     * @return result of alert confirmation
     */
    public static boolean deletePart(Part part) {
        if (alertDelete()) {
            Iterator<Product> iterator = products.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                ObservableList<Part> tempParts = product.getAssociatedParts();
                if (tempParts.contains(part) && tempParts.size() > 1) {
                    tempParts.remove(part);
                } else if (tempParts.contains(part)) {
                    if (alertDependency(part, product)) {
                        iterator.remove();
                    }
                }
            }

            return allParts.remove(part);
        } else {
            return false;
        }
    }

    /**
     *
     * @param partID
     * @return Part object with matching partID property
     */
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

    /**
     * use replacePart()
     * @param partID
     * @deprecated
     */
    @Deprecated
    public static void updatePart(int partID) {
    }

    /**
     *
     * @return global Parts list
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *
     * @return global Products list
     */
    public static ObservableList<Product> getProducts() {
        return products;
    }

    /**
     *
     * @return next part ID number
     */
    public static int getPartID() {
        return partIDtracker;
    }

    /**
     *
     * @return next product ID number
     */
    public static int getProductID() {
        return ProductIDtracker;
    }

    /**
     *
     * @param oldPart
     * @param newPart
     */
    public static void replacePart(Part oldPart, Part newPart) {
        allParts.set(allParts.indexOf(oldPart), newPart);
    }

    /**
     *
     * @param oldProduct
     * @param newProduct
     */
    public static void replaceProduct(Product oldProduct, Product newProduct) {
        products.set(products.indexOf(oldProduct), newProduct);
    }

    /**
     *
     * @param part
     * @return Part validation result
     */
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

    /**
     *
     * @param product
     * @return Product validation result
     */
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
                    + "\n(This exception control approved by the\n"
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

    /**
     *
     * @param alertText
     */
    public static void failedCheck(String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(alertText);
        alert.showAndWait();
    }

    /**
     *
     */
    public static void alertDeleteProductWithParts() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Remove all associated parts to delete Product\n"
                + "\n(The Department of Redundancy Department approves"
                + " of this button)");
        alert.showAndWait();
    }

    /**
     *
     */
    public static void alertFormat() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("All those little boxes need stuff in them.\n"
                + "Tip: put numbers where numbers go and words where words go");
        alert.showAndWait();
    }

    /**
     *
     * @return confirmation alert result
     */
    public static ButtonType alertCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Discard changes?");
        alert.showAndWait();
        return alert.getResult();
    }

    /**
     *
     * @return confirmation alert result
     */
    public static boolean alertDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Confirm Delete");
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }

    /**
     *
     */
    public static void alertSearchEmpty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Search Field Empty");
        alert.showAndWait();
    }

    /**
     *
     * @param id
     */
    public static void alertID(String id) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(id + " is not a valid ID");
        alert.showAndWait();
    }

    /**
     *
     */
    public static void alertSelection() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Nothing selected");
        alert.showAndWait();
    }

    /**
     *
     * @return confirmation alert result
     */
    public static boolean alertLastPart() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("All parts removed.\n"
                + "You can't sell a box of air. Maybe delete "
                + "this product?");
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }

    /**
     *
     * @param part
     * @param product
     * @return confirmation alert result
     */
    public static boolean alertDependency(Part part, Product product) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(part.getName() + " is the only part used to make "
                + "the " + product.getName() + " product. Delete it as well?");
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }
}
