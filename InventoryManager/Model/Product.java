package Model;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


public class Product {
    
    private ObservableList<Part> associatedParts;
    private final IntegerProperty productID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;

    public Product(int newProductID, String newName, double newPrice, 
            int newInStock, int newMin, int newMax) {
        productID = new SimpleIntegerProperty(newProductID);
        name = new SimpleStringProperty(newName);
        price = new SimpleDoubleProperty(newPrice);
        inStock = new SimpleIntegerProperty(newInStock);
        min = new SimpleIntegerProperty(newMin);
        max = new SimpleIntegerProperty(newMax);
    }
    
    
    
    
    
    public void setName(String newName){
        name.setValue(newName);
    }
    
    public String getName(){
        return name.get();
    }
    
    public void setPrice(double newPrice){
        price.set(newPrice);
    }
    
    public double getPrice(){
        return price.get();
    }
    
    public void setInStock(int newInStock){
        inStock.set(newInStock);
    }
    
    public int getInStock(){
        return inStock.get();
    }
    
    public void setMin(int newMin){
        min.set(newMin);
    }
    
    public int getMin(){
        return min.get();
    }
    
    public void setMax(int newMax){
        max.set(newMax);
    }
    
    public int getMax(){
        return max.get();
    }
    
    public void addAssociatedPart(Part newPart){
        
    }
    
    public boolean removeAssociatedPart(Part removedPart){
        boolean partFound = false;
        
        return partFound;
    }
    
    public Part lookupAssociatedPart(int partID){
        Part foundPart = null;
        
        return foundPart;
    }
    
    public void setProductID(int newProductID){
        productID.set(newProductID);
    }
    
    public int getProductID(){
        return productID.get();
    }
    
    
    
}























