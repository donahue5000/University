package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Brian Donahue
 */
public class InhousePart extends Part{
    
    private final IntegerProperty machineID;
    
    /**
     *
     * @param newPartID
     * @param newName
     * @param newPrice
     * @param newInStock
     * @param newMin
     * @param newMax
     * @param newMachineID
     */
    public InhousePart(int newPartID, String newName, double newPrice,
            int newInStock, int newMin, int newMax, int newMachineID){
        super(newPartID, newName, newPrice, newInStock, newMin, newMax);
        machineID = new SimpleIntegerProperty(newMachineID);
    }
    
    /**
     *
     * @param newMachineID
     */
    public void setMachineID(int newMachineID){
        machineID.set(newMachineID);
    }
    
    /**
     *
     * @return machineID value
     */
    public int getMachineID(){
        return machineID.get();
    }
    
}
