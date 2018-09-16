package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class InhousePart extends Part{
    
    private final IntegerProperty machineID;
    
    public InhousePart(int newPartID, String newName, double newPrice,
            int newInStock, int newMin, int newMax, int newMachineID){
        super(newPartID, newName, newPrice, newInStock, newMin, newMax);
        machineID = new SimpleIntegerProperty(newMachineID);
    }
    
    
    
    
    public void setMachineID(int newMachineID){
        machineID.set(newMachineID);
    }
    
    public int getMachineID(){
        return machineID.get();
    }
    
}
