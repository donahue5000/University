package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Brian Donahue
 */
public class OutsourcedPart extends Part{
    
    private final StringProperty companyName;
    
    /**
     *
     * @param newPartID
     * @param newName
     * @param newPrice
     * @param newInStock
     * @param newMin
     * @param newMax
     * @param newCompanyName
     */
    public OutsourcedPart(int newPartID, String newName, double newPrice,
            int newInStock, int newMin, int newMax, String newCompanyName){
        super(newPartID, newName, newPrice, newInStock, newMin, newMax);
        companyName = new SimpleStringProperty(newCompanyName);
    }
    
    /**
     *
     * @param newCompanyName
     */
    public void setCompanyName(String newCompanyName){
        companyName.set(newCompanyName);
    }
    
    /**
     *
     * @return companyName value
     */
    public String getCompanyName(){
        return companyName.get();
    }
    
}
