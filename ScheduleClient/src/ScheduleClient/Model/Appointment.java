package ScheduleClient.Model;

import ScheduleClient.Util.Connectatron;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {

    //appointment table
    private IntegerProperty appointmentId;
    private IntegerProperty customerId;
    private StringProperty title;
    private StringProperty description;
    private StringProperty location;
    private StringProperty contact;
    private StringProperty url;
    private StringProperty start;
    private StringProperty end;
    private StringProperty createDate;
    private StringProperty createdBy;
    private StringProperty lastUpdate;
    private StringProperty lastUpdatedBy;
    private IntegerProperty userId;
    private StringProperty type;
    
    //customer table
    private StringProperty customerName;

    
    public Appointment(){
        this.appointmentId = new SimpleIntegerProperty();
        this.customerId = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.location = new SimpleStringProperty();
        this.contact = new SimpleStringProperty();
        this.url = new SimpleStringProperty();
        this.start = new SimpleStringProperty();
        this.end = new SimpleStringProperty();
        this.createDate = new SimpleStringProperty();
        this.createdBy = new SimpleStringProperty();
        this.lastUpdate = new SimpleStringProperty();
        this.lastUpdatedBy = new SimpleStringProperty();
        this.userId = new SimpleIntegerProperty();
        this.type = new SimpleStringProperty();
        this.customerName = new SimpleStringProperty();
    }
    
    
    public Appointment(int appointmentId, int customerId, String title,
            String description, String location, String contact, String url,
            String start, String end, String createDate, String createdBy,
            String lastUpdate, String lastUpdatedBy, int userId, String type, 
            String customerName) {
        this.appointmentId = new SimpleIntegerProperty(appointmentId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.contact = new SimpleStringProperty(contact);
        this.url = new SimpleStringProperty(url);
        this.start = new SimpleStringProperty(start);
        this.end = new SimpleStringProperty(end);
        this.createDate = new SimpleStringProperty(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
        this.userId = new SimpleIntegerProperty(userId);
        this.type = new SimpleStringProperty(type);
        this.customerName = new SimpleStringProperty(customerName);
    }


    public final int getAppointmentId() {
        return appointmentId.get();
    }

    public final void setAppointmentId(int value) {
        appointmentId.set(value);
    }

    public IntegerProperty appointmentIdProperty() {
        return appointmentId;
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

    public final String getTitle() {
        return title.get();
    }

    public final void setTitle(String value) {
        title.set(value);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public final String getDescription() {
        return description.get();
    }

    public final void setDescription(String value) {
        description.set(value);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public final String getLocation() {
        return location.get();
    }

    public final void setLocation(String value) {
        location.set(value);
    }

    public StringProperty locationProperty() {
        return location;
    }

    public final String getContact() {
        return contact.get();
    }

    public final void setContact(String value) {
        contact.set(value);
    }

    public StringProperty contactProperty() {
        return contact;
    }

    public final String getUrl() {
        return url.get();
    }

    public final void setUrl(String value) {
        url.set(value);
    }

    public StringProperty urlProperty() {
        return url;
    }

    public final String getStart() {
        return start.get();
    }

    public final void setStart(String value) {
        start.set(value);
    }

    public StringProperty startProperty() {
        return start;
    }

    public final String getEnd() {
        return end.get();
    }

    public final void setEnd(String value) {
        end.set(value);
    }

    public StringProperty endProperty() {
        return end;
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

    public final String getLastUpdatedBy() {
        return lastUpdatedBy.get();
    }

    public final void setLastUpdatedBy(String value) {
        lastUpdatedBy.set(value);
    }

    public StringProperty lastUpdatedByProperty() {
        return lastUpdatedBy;
    }

    public final int getUserId() {
        return userId.get();
    }

    public final void setUserId(int value) {
        userId.set(value);
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public final String getType() {
        return type.get();
    }

    public final void setType(String value) {
        type.set(value);
    }

    public StringProperty typeProperty() {
        return type;
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
    
    

}
