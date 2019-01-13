

package ScheduleClient.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;


public class Customer {
    
    //customer table
    private IntegerProperty customerId;
    private StringProperty customerName;
    private IntegerProperty addressId;
    private IntegerProperty active;
    private StringProperty createDate;
    private StringProperty createdBy;
    private StringProperty lastUpdate;
    private StringProperty lastUpdateBy;
    
    //address table
    private StringProperty address;
    private StringProperty address2;
    private IntegerProperty cityId;
    private StringProperty postalCode;
    private StringProperty phone;
    
    //city table
    private StringProperty city;
    private StringProperty countryId;
    
    //country table
    private StringProperty country;
    
    
    

}
