

package ScheduleClient.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
    private IntegerProperty countryId;
    
    //country table
    private StringProperty country;

    public Customer(int customerId, String customerName, int addressId, int active, 
            String createDate, String createdBy, String lastUpdate, String lastUpdateBy, 
            String address, String address2, int cityId, String postalCode, 
            String phone, String city, int countryId, String country) {
        this.customerId = new SimpleIntegerProperty(customerId);
        this.customerName = new SimpleStringProperty(customerName);
        this.addressId = new SimpleIntegerProperty(addressId);
        this.active = new SimpleIntegerProperty(active);
        this.createDate = new SimpleStringProperty(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
        this.lastUpdateBy = new SimpleStringProperty(lastUpdateBy);
        this.address = new SimpleStringProperty(address);
        this.address2 = new SimpleStringProperty(address2);
        this.cityId = new SimpleIntegerProperty(cityId);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.phone = new SimpleStringProperty(phone);
        this.city = new SimpleStringProperty(city);
        this.countryId = new SimpleIntegerProperty(countryId);
        this.country = new SimpleStringProperty(country);
    }
    
    @Override
    public String toString(){
        return ("ID: " + getCustomerId() + " " + getCustomerName());
    }
    

    public final int getCustomerId() {
        return customerId.get();
    }

    public final void setCustomerId(int value) {
        customerId.set(value);
    }

    public IntegerProperty customerIdProperty() {
        return customerId;
    }

    public final String getCustomerName() {
        return customerName.get();
    }

    public final void setCustomerName(String value) {
        customerName.set(value);
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public final int getAddressId() {
        return addressId.get();
    }

    public final void setAddressId(int value) {
        addressId.set(value);
    }

    public IntegerProperty addressIdProperty() {
        return addressId;
    }

    public final int getActive() {
        return active.get();
    }

    public final void setActive(int value) {
        active.set(value);
    }

    public IntegerProperty activeProperty() {
        return active;
    }

    public final String getCreateDate() {
        return createDate.get();
    }

    public final void setCreateDate(String value) {
        createDate.set(value);
    }

    public StringProperty createDateProperty() {
        return createDate;
    }

    public final String getCreatedBy() {
        return createdBy.get();
    }

    public final void setCreatedBy(String value) {
        createdBy.set(value);
    }

    public StringProperty createdByProperty() {
        return createdBy;
    }

    public final String getLastUpdate() {
        return lastUpdate.get();
    }

    public final void setLastUpdate(String value) {
        lastUpdate.set(value);
    }

    public StringProperty lastUpdateProperty() {
        return lastUpdate;
    }

    public final String getLastUpdateBy() {
        return lastUpdateBy.get();
    }

    public final void setLastUpdateBy(String value) {
        lastUpdateBy.set(value);
    }

    public StringProperty lastUpdateByProperty() {
        return lastUpdateBy;
    }

    public final String getAddress() {
        return address.get();
    }

    public final void setAddress(String value) {
        address.set(value);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public final String getAddress2() {
        return address2.get();
    }

    public final void setAddress2(String value) {
        address2.set(value);
    }

    public StringProperty address2Property() {
        return address2;
    }

    public final int getCityId() {
        return cityId.get();
    }

    public final void setCityId(int value) {
        cityId.set(value);
    }

    public IntegerProperty cityIdProperty() {
        return cityId;
    }

    public final String getPostalCode() {
        return postalCode.get();
    }

    public final void setPostalCode(String value) {
        postalCode.set(value);
    }

    public StringProperty postalCodeProperty() {
        return postalCode;
    }

    public final String getPhone() {
        return phone.get();
    }

    public final void setPhone(String value) {
        phone.set(value);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public final String getCity() {
        return city.get();
    }

    public final void setCity(String value) {
        city.set(value);
    }

    public StringProperty cityProperty() {
        return city;
    }

    public final int getCountryId() {
        return countryId.get();
    }

    public final void setCountryId(int value) {
        countryId.set(value);
    }

    public IntegerProperty countryIdProperty() {
        return countryId;
    }

    public final String getCountry() {
        return country.get();
    }

    public final void setCountry(String value) {
        country.set(value);
    }

    public StringProperty countryProperty() {
        return country;
    }
    
    
    
    
    

}
