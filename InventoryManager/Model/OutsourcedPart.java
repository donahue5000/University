package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class OutsourcedPart extends Part{
    
    private final StringProperty companyName;
    
    public OutsourcedPart(int newPartID, String newName, double newPrice,
            int newInStock, int newMin, int newMax, String newCompanyName){
        super(newPartID, newName, newPrice, newInStock, newMin, newMax);
        companyName = new SimpleStringProperty(newCompanyName);
    }
    
    
    
    
    public void setCompanyName(String newCompanyName){
        companyName.set(newCompanyName);
    }
    
    public String getCompanyName(){
        return companyName.get();
    }
    
}
