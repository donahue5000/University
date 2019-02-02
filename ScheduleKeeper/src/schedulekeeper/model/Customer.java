
package schedulekeeper.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;




public class Customer {
    private final IntegerProperty customerId;
    private final StringProperty customerName;
    private final IntegerProperty addressId;
    private final StringProperty address;
    private final StringProperty address2;
    private final StringProperty postalCode;
    private final StringProperty phone;
    private final IntegerProperty cityId;
    private final StringProperty city;
    private final IntegerProperty countryId;
    private final StringProperty country;

    public Customer(
            int customerId, 
            String customerName, 
            int addressId, 
            String address, 
            String address2, 
            String postalCode, 
            String phone, 
            int cityId, 
            String city, 
            int countryId, 
            String country) {
        this.customerId = new SimpleIntegerProperty(customerId);
        this.customerName = new SimpleStringProperty(customerName);
        this.addressId = new SimpleIntegerProperty(addressId);
        this.address = new SimpleStringProperty(address);
        this.address2 = new SimpleStringProperty(address2);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.phone = new SimpleStringProperty(phone);
        this.cityId = new SimpleIntegerProperty(cityId);
        this.city = new SimpleStringProperty(city);
        this.countryId = new SimpleIntegerProperty(countryId);
        this.country = new SimpleStringProperty(country);
    }
    
    @Override
    public String toString(){
        return "ID:" + customerId.get() + " " + getCustomerName();
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

    public final int getCityId() {
        return cityId.get();
    }

    public final void setCityId(int value) {
        cityId.set(value);
    }

    public IntegerProperty cityIdProperty() {
        return cityId;
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