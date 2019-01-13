package ScheduleClient.Model;

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

    public IntegerProperty appointmentIdProperty() {
        return appointmentId;
    }

    public IntegerProperty customerIdProperty() {
        return customerId;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty contactProperty() {
        return contact;
    }

    public StringProperty urlProperty() {
        return url;
    }

    public StringProperty startProperty() {
        return start;
    }

    public StringProperty endProperty() {
        return end;
    }

    public StringProperty createDateProperty() {
        return createDate;
    }

    public StringProperty createdByProperty() {
        return createdBy;
    }

    public StringProperty lastUpdateProperty() {
        return lastUpdate;
    }

    public StringProperty lastUpdatedByProperty() {
        return lastUpdatedBy;
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public StringProperty typeProperty() {
        return type;
    }
    
    public StringProperty customerNameProperty() {
        return customerName;
    }

}
