package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Brian Donahue
 */
public class Product {
    
    private ObservableList<Part> associatedParts;
    private final IntegerProperty productID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;

    /**
     *
     * @param newAssociatedParts
     * @param newProductID
     * @param newName
     * @param newPrice
     * @param newInStock
     * @param newMin
     * @param newMax
     */
    public Product(ObservableList<Part> newAssociatedParts, int newProductID, 
            String newName, double newPrice, int newInStock, int newMin, 
            int newMax) {
        associatedParts = newAssociatedParts;
        productID = new SimpleIntegerProperty(newProductID);
        name = new SimpleStringProperty(newName);
        price = new SimpleDoubleProperty(newPrice);
        inStock = new SimpleIntegerProperty(newInStock);
        min = new SimpleIntegerProperty(newMin);
        max = new SimpleIntegerProperty(newMax);
    }
    
    /**
     *
     * @param newName
     */
    public void setName(String newName){
        name.set(newName);
    }
    
    /**
     *
     * @return name value
     */
    public String getName(){
        return name.get();
    }
    
    /**
     *
     * @param newPrice
     */
    public void setPrice(double newPrice){
        price.set(newPrice);
    }
    
    /**
     *
     * @return price value
     */
    public double getPrice(){
        return price.get();
    }
    
    /**
     *
     * @param newInStock
     */
    public void setInStock(int newInStock){
        inStock.set(newInStock);
    }
    
    /**
     *
     * @return inStock value
     */
    public int getInStock(){
        return inStock.get();
    }
    
    /**
     *
     * @param newMin
     */
    public void setMin(int newMin){
        min.set(newMin);
    }
    
    /**
     *
     * @return min value
     */
    public int getMin(){
        return min.get();
    }
    
    /**
     *
     * @param newMax
     */
    public void setMax(int newMax){
        max.set(newMax);
    }
    
    /**
     *
     * @return max value
     */
    public int getMax(){
        return max.get();
    }
    
    /**
     *
     * @param newPart
     */
    public void addAssociatedPart(Part newPart){
        associatedParts.add(newPart);
    }
    
    /**
     * handled in Inventory class
     * @param removedPart
     * @return 
     * @deprecated 
     */
    @Deprecated
    public boolean removeAssociatedPart(Part removedPart){
        boolean partFound = false;
        return partFound;
    }
    
    /**
     * handled in Inventory class
     * @param partID
     * @return
     * @deprecated
     */
    @Deprecated
    public Part lookupAssociatedPart(int partID){
        Part foundPart = null;
        return foundPart;
    }
    
    /**
     *
     * @return list of object's associated parts
     */
    public ObservableList<Part> getAssociatedParts(){
        return associatedParts;
    }
    
    /**
     *
     * @param newAssociatedParts
     */
    public void setAssociatedParts(ObservableList newAssociatedParts){
        associatedParts = newAssociatedParts;
    }
    
    /**
     *
     * @param newProductID
     */
    public void setProductID(int newProductID){
        productID.set(newProductID);
    }
    
    /**
     *
     * @return productID value
     */
    public int getProductID(){
        return productID.get();
    }
    
    /**
     *
     * @return productID property
     */
    public IntegerProperty productIDProperty(){
        return productID;
    }
    
    /**
     *
     * @return name property
     */
    public StringProperty nameProperty(){
        return name;
    }
    
    /**
     *
     * @return price property
     */
    public DoubleProperty priceProperty(){
        return price;
    }
    
    /**
     *
     * @return inStock property
     */
    public IntegerProperty inStockProperty(){
        return inStock;
    }
    
    /**
     *
     * @return min property
     */
    public IntegerProperty minProperty(){
        return min;
    }
    
    /**
     *
     * @return max property
     */
    public IntegerProperty maxProperty(){
        return max;
    }
    
}























