package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {
    
    private static ObservableList<Product> products = 
            FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = 
            FXCollections.observableArrayList();
    private static int partIDtracker = 1;
    private static int ProductIDtracker = 1;

    
    
    
    
    public static void addProduct(Product product){
        products.add(product);
        ProductIDtracker++;
    }
    
    public static boolean removeProduct(int productID){
        boolean productFound = false;
        
        return productFound;
    }
    
    public static Product lookupProduct(int productID){
        Product product = null;
        
        return product;
    }
    
    public static void updateProduct(int productID){
        
    }
    
    public static void addPart(Part part){
        allParts.add(part);
        partIDtracker++;
    }
    
    public static boolean deletePart(Part part){
        boolean partFound = false;
        
        return partFound;
    }
    
    public static Part lookupPart(int partID){
        Part part = null;
        
        return part;
    }
    
    public static void updatePart(int partID){
        
    }
    
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    public static ObservableList<Product> getProducts(){
        return products;
    }
    
    public static int getPartID(){
        return partIDtracker;
    }
    
    public static int getProductID(){
        return ProductIDtracker;
    }
    
}
