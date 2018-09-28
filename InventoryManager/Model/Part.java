package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Brian Donahue
 */
public abstract class Part {
    
    private final IntegerProperty partID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;
    
    Part(int newPartID, String newName, double newPrice, int newInStock,
            int newMin, int newMax){
        partID = new SimpleIntegerProperty(newPartID);
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
     * @param newPartID
     */
    public void setPartID(int newPartID){
        partID.set(newPartID);
    }
    
    /**
     *
     * @return partID value
     */
    public int getPartID(){
        return partID.get();
    }
    
    /**
     *
     * @return partID property
     */
    public IntegerProperty partIDProperty(){
        return partID;
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





















