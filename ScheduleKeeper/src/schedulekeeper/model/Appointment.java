package schedulekeeper.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {

    private IntegerProperty appointmentId;
    private IntegerProperty customerId;
    private StringProperty customer;
    private StringProperty title;
    private StringProperty description;
    private StringProperty location;
    private StringProperty contact;
    private StringProperty url;
    private IntegerProperty userId;
    private StringProperty user;
    private StringProperty type;

    private StringProperty startString;
    private ZonedDateTime start;
    private StringProperty endString;
    private ZonedDateTime end;

    public Appointment(
            int appointmentId, 
            int customerId, 
            String customer, 
            String title, 
            String description, 
            String location, 
            String contact, 
            String url, 
            int userId, 
            String user, 
            String type, 
            ZonedDateTime start, 
            ZonedDateTime end) {
        this.appointmentId = new SimpleIntegerProperty(appointmentId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.customer = new SimpleStringProperty(customer);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.contact = new SimpleStringProperty(contact);
        this.url = new SimpleStringProperty(url);
        this.userId = new SimpleIntegerProperty(userId);
        this.user = new SimpleStringProperty(user);
        this.type = new SimpleStringProperty(type);
        this.start = start;
        this.end = end;
        
        setFancyDateTime();
    }
    
    private void setFancyDateTime(){
        startString = new SimpleStringProperty(start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        endString = new SimpleStringProperty(end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
    
    public void setStart(ZonedDateTime zonedStart){
        start = zonedStart;
        setFancyDateTime();
    }
    
    public ZonedDateTime getStart(){
        return start;
    }
    
    public void setEnd(ZonedDateTime zonedEnd){
        end = zonedEnd;
        setFancyDateTime();
    }
    
    public ZonedDateTime getEnd(){
        return end;
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

    public final String getCustomer() {
        return customer.get();
    }

    public final void setCustomer(String value) {
        customer.set(value);
    }

    public StringProperty customerProperty() {
        return customer;
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

    public final int getUserId() {
        return userId.get();
    }

    public final void setUserId(int value) {
        userId.set(value);
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public final String getUser() {
        return user.get();
    }

    public final void setUser(String value) {
        user.set(value);
    }

    public StringProperty userProperty() {
        return user;
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

    public final String getStartString() {
        return startString.get();
    }

    public final void setStartString(String value) {
        startString.set(value);
    }

    public StringProperty startStringProperty() {
        return startString;
    }

    public final String getEndString() {
        return endString.get();
    }

    public final void setEndString(String value) {
        endString.set(value);
    }

    public StringProperty endStringProperty() {
        return endString;
    }

}
