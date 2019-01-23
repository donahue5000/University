package ScheduleClient.Util;

import ScheduleClient.Model.Appointment;
import ScheduleClient.Model.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Connectatron {

    private static final String DBURL = "jdbc:mysql://52.206.157.109/U04VEO";
    private static final String DBUSER = "U04VEO";
    private static final String DBPW = "53688353958";
    public static String USER;
    public static int USERID;

    public static Connection getCon() {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPW);
            return con;
        } catch (SQLException e) {
            Oops.badGetCon();
        }
        return null;
    }

    public static void closeCon(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            Oops.badCloseCon();
        }
    }

    public static ObservableList<Appointment> getAppointmentList() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        Connection con = getCon();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT appointment.*, "
                    + "customer.customerName FROM appointment, customer WHERE "
                    + "appointment.userId = ? AND appointment.customerId = customer.customerId");
            statement.setString(1, Integer.toString(Connectatron.USERID));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                appointmentList.add(new Appointment(
                        result.getInt("appointmentId"),
                        result.getInt("customerId"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getString("location"),
                        result.getString("contact"),
                        result.getString("url"),
                        result.getString("start"),
                        result.getString("end"),
                        result.getString("createDate"),
                        result.getString("createdBy"),
                        result.getString("lastUpdate"),
                        result.getString("lastUpdateBy"),
                        result.getInt("userId"),
                        result.getString("type"),
                        result.getString("customerName")
                ));
            }
        } catch (SQLException e) {
            Oops.badGetCon();
            closeCon(con);
        }

        closeCon(con);
        return appointmentList;
    }

    public static ObservableList<Customer> getCustomerList() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        Connection con = getCon();
        try {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT customer.*, address.address, address.address2, "
                    + "address.cityId, address.postalCode, "
                    + "address.phone, city.city, city.countryId, country.country "
                    + "FROM customer, address, city, country "
                    + "WHERE customer.addressId = address.addressId AND "
                    + "address.cityId = city.cityId AND "
                    + "city.countryId = country.countryId");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                customerList.add(new Customer(
                        result.getInt("customerId"),
                        result.getString("customerName"),
                        result.getInt("addressId"),
                        result.getInt("active"),
                        result.getString("createDate"),
                        result.getString("createdBy"),
                        result.getString("lastUpdate"),
                        result.getString("lastUpdateBy"),
                        result.getString("address"),
                        result.getString("address2"),
                        result.getInt("cityId"),
                        result.getString("postalCode"),
                        result.getString("phone"),
                        result.getString("city"),
                        result.getInt("countryId"),
                        result.getString("country")
                ));
            }
        } catch (SQLException e) {
            Oops.badGetCon();
            closeCon(con);
        }

        closeCon(con);
        return customerList;
    }

    public static Customer getCustomerByID(int ID) {
        Customer customer = null;
        Connection con = getCon();
        try {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT customer.*, address.address, address.address2, "
                    + "address.cityId, address.postalCode, "
                    + "address.phone, city.city, city.countryId, country.country "
                    + "FROM customer, address, city, country "
                    + "WHERE customer.customerId = ? AND "
                    + "customer.addressId = address.addressId AND "
                    + "address.cityId = city.cityId AND "
                    + "city.countryId = country.countryId");
            statement.setInt(1, ID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                customer = new Customer(
                        result.getInt("customerId"),
                        result.getString("customerName"),
                        result.getInt("addressId"),
                        result.getInt("active"),
                        result.getString("createDate"),
                        result.getString("createdBy"),
                        result.getString("lastUpdate"),
                        result.getString("lastUpdateBy"),
                        result.getString("address"),
                        result.getString("address2"),
                        result.getInt("cityId"),
                        result.getString("postalCode"),
                        result.getString("phone"),
                        result.getString("city"),
                        result.getInt("countryId"),
                        result.getString("country")
                );
            }
        } catch (SQLException e) {
            Oops.badGetCon();
            closeCon(con);
        }

        closeCon(con);
        return customer;
    }

    public static Appointment getAppointmentByID(int ID) {
        Appointment appointment = null;
        Connection con = getCon();
        try {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT appointment.* FROM appointment WHERE appointmentId = ?");
            statement.setInt(1, ID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                appointment = new Appointment(
                        result.getInt("appointmentId"),
                        result.getInt("customerId"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getString("location"),
                        result.getString("contact"),
                        result.getString("url"),
                        result.getString("start"),
                        result.getString("end"),
                        result.getString("createDate"),
                        result.getString("createdBy"),
                        result.getString("lastUpdate"),
                        result.getString("lastUpdateBy"),
                        result.getInt("userId"),
                        result.getString("type"),
                        Connectatron.getCustomerByID(result.getInt("customerId")).getCustomerName()
                );
            }
        } catch (SQLException e) {
            Oops.badGetCon();
            closeCon(con);
        }

        closeCon(con);
        return appointment;
    }

    public static void scrubAllID(Customer customer) {
        int countryID = customer.getCountryId();
        int cityID = customer.getCityId();
        int addressID = customer.getAddressId();

        Connection con = getCon();
        PreparedStatement ps;
        ResultSet rs;
        try {

            //find or insert country, set ID
            ps = con.prepareStatement("SELECT countryId FROM country WHERE country = ?");
            ps.setString(1, customer.getCountry());
            rs = ps.executeQuery();
            if (rs.next()) {
                countryID = rs.getInt(1);
            } else {
                ps = con.prepareStatement("INSERT INTO country VALUES ("
                        + "null, "
                        + "?, "
                        + "now(), "
                        + "?, "
                        + "now(), "
                        + "?)");
                ps.setString(1, customer.getCountry());
                ps.setString(2, USER);
                ps.setString(3, USER);
                ps.executeUpdate();
                ps = con.prepareStatement("SELECT MAX(countryId) FROM country");
                rs = ps.executeQuery();
                if (rs.next()) {
                    countryID = rs.getInt(1);
                }
            }

            //find or insert city, set ID
            ps = con.prepareStatement("SELECT cityId FROM city WHERE city = ? AND countryId = ?");
            ps.setString(1, customer.getCity());
            ps.setInt(2, countryID);
            rs = ps.executeQuery();
            if (rs.next()) {
                cityID = rs.getInt(1);
            } else {
                ps = con.prepareStatement("INSERT INTO city VALUES ("
                        + "null, "
                        + "?, "
                        + "?, "
                        + "now(), "
                        + "?, "
                        + "now(), "
                        + "?)");
                ps.setString(1, customer.getCity());
                ps.setInt(2, countryID);
                ps.setString(3, USER);
                ps.setString(4, USER);
                ps.executeUpdate();
                ps = con.prepareStatement("SELECT MAX(cityId) FROM city");
                rs = ps.executeQuery();
                if (rs.next()) {
                    cityID = rs.getInt(1);
                }
            }

            //find or insert address, set ID
            ps = con.prepareStatement("SELECT addressId from address WHERE "
                    + "address = ? AND "
                    + "address2 = ? AND "
                    + "cityId = ? AND "
                    + "postalCode = ? AND "
                    + "phone = ?");
            ps.setString(1, customer.getAddress());
            ps.setString(2, customer.getAddress2());
            ps.setInt(3, cityID);
            ps.setString(4, customer.getPostalCode());
            ps.setString(5, customer.getPhone());
            rs = ps.executeQuery();
            if (rs.next()) {
                addressID = rs.getInt(1);
            } else {
                ps = con.prepareStatement("INSERT INTO address VALUES("
                        + "null, ?, ?, ?, ?, ?, "
                        + "now(), ?, now(), ?)");
                ps.setString(1, customer.getAddress());
                ps.setString(2, customer.getAddress2());
                ps.setInt(3, cityID);
                ps.setString(4, customer.getPostalCode());
                ps.setString(5, customer.getPhone());
                ps.setString(6, USER);
                ps.setString(7, USER);
                ps.executeUpdate();
                ps = con.prepareStatement("SELECT MAX(addressId) FROM address");
                rs = ps.executeQuery();
                if (rs.next()) {
                    addressID = rs.getInt(1);
                }
            }

            customer.setCountryId(countryID);
            customer.setCityId(cityID);
            customer.setAddressId(addressID);
            closeCon(con);
        } catch (SQLException e) {
            Oops.badGetCon();
            closeCon(con);
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
        }

    }

    public static void insertCustomer(Customer existingCustomer) throws NullPointerException {
        Connection con = getCon();
        try {
            if (existingCustomer.getCustomerId() > 0) {
                PreparedStatement ps = con.prepareStatement("UPDATE customer SET "
                        + "customerName = ?, "
                        + "addressId = ?, "
                        + "lastUpdate = now(), "
                        + "lastUpdateBy = ? "
                        + "WHERE customerId = ?"
                );
                try {
                    ps.setString(1, existingCustomer.getCustomerName());
                    ps.setInt(2, existingCustomer.getAddressId());
                    ps.setString(3, USER);
                    ps.setInt(4, existingCustomer.getCustomerId());
                    ps.executeUpdate();
                } catch (NullPointerException n) {
                    closeCon(con);
                    throw new NullPointerException();
                }
            } else {
                PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES( "
                        + "null, "
                        + "?, "
                        + "?, "
                        + "1, "
                        + "now(), "
                        + "?, "
                        + "now(), "
                        + "?)"
                );
                try {
                    ps.setString(1, existingCustomer.getCustomerName());
                    ps.setInt(2, existingCustomer.getAddressId());
                    ps.setString(3, USER);
                    ps.setString(4, USER);
                    ps.executeUpdate();
                } catch (NullPointerException n) {
                    closeCon(con);
                    throw new NullPointerException();
                }
                ps = con.prepareStatement("SELECT MAX(customerId) FROM customer");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    existingCustomer.setCustomerId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            Oops.badGetCon();
            closeCon(con);
        }
        closeCon(con);
    }

    public static void deleteCustomer(int customerID) {
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM appointment WHERE customerId = ?");
            ps.setInt(1, customerID);
            ps.executeUpdate();
            ps = con.prepareStatement("DELETE FROM customer WHERE customerId = ?");
            ps.setInt(1, customerID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Oops.badGetCon();
            closeCon(con);
        }
        closeCon(con);
    }

    public static void deleteAppointment(int appointmentId) {
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM appointment WHERE appointmentId = ?");
            ps.setInt(1, appointmentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Oops.badGetCon();
            closeCon(con);
        }
        closeCon(con);
    }

    public static void insertAppointment(Appointment newAppointment) throws NullPointerException {
        Connection con = getCon();
        try {
            if (newAppointment.getAppointmentId() > 0) {
                //new appt with existing values
                PreparedStatement ps = con.prepareStatement("INSERT INTO appointment VALUES("
                        + "?, " //appointmentId
                        + "?, " //customerId
                        + "?, " //title
                        + "?, " //description
                        + "?, " //location
                        + "?, " //contact
                        + "?, " //url
                        + "?, " //start
                        + "?, " //end
                        + "?, " //createDate
                        + "?, " //createdBy
                        + "now(), " //lastUpdate
                        + "?, " //lastUpdateBy
                        + "?, " //userId
                        + "?)"); //type
                try {
                    ps.setInt(1, newAppointment.getAppointmentId());
                    ps.setInt(2, newAppointment.getCustomerId());
                    ps.setString(3, newAppointment.getTitle());
                    ps.setString(4, newAppointment.getDescription());
                    ps.setString(5, newAppointment.getLocation());
                    ps.setString(6, newAppointment.getContact());
                    ps.setString(7, newAppointment.getUrl());
                    ps.setString(8, newAppointment.getStart());
                    ps.setString(9, newAppointment.getEnd());
                    ps.setString(10, newAppointment.getCreateDate());
                    ps.setString(11, newAppointment.getCreatedBy());
                    ps.setString(12, USER);
                    ps.setInt(13, newAppointment.getUserId());
                    ps.setString(14, newAppointment.getType());
                    ps.executeUpdate();
                } catch (NullPointerException n) {
                    closeCon(con);
                    throw new NullPointerException();
                }
            } else {
                PreparedStatement ps = con.prepareStatement("INSERT INTO appointment VALUES("
                        + "null, " //appointmentId
                        + "?, " //customerId
                        + "?, " //title
                        + "?, " //description
                        + "?, " //location
                        + "?, " //contact
                        + "?, " //url
                        + "?, " //start
                        + "?, " //end
                        + "now(), " //createDate
                        + "?, " //createdBy
                        + "now(), " //lastUpdate
                        + "?, " //lastUpdateBy
                        + "?, " //userId
                        + "?)"); //type
                try {
                    ps.setInt(1, newAppointment.getCustomerId());
                    ps.setString(2, newAppointment.getTitle());
                    ps.setString(3, newAppointment.getDescription());
                    ps.setString(4, newAppointment.getLocation());
                    ps.setString(5, newAppointment.getContact());
                    ps.setString(6, newAppointment.getUrl());
                    ps.setString(7, newAppointment.getStart());
                    ps.setString(8, newAppointment.getEnd());
                    ps.setString(9, USER);
                    ps.setString(10, USER);
                    ps.setInt(11, newAppointment.getUserId());
                    ps.setString(12, newAppointment.getType());
                    ps.executeUpdate();
                } catch (NullPointerException n) {
                    closeCon(con);
                    throw new NullPointerException();
                }

                ps = con.prepareStatement("SELECT MAX(appointmentId) FROM appointment");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    newAppointment.setAppointmentId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Oops.badGetCon();
            closeCon(con);
        }

        closeCon(con);
    }

    public static DateTimeFormatter getDTFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        return formatter;
    }

    public static LocalDateTime getLDT(String date) {
        LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm"));
        return ldt;
    }

}
