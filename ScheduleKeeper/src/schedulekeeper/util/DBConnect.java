package schedulekeeper.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedulekeeper.model.Appointment;
import schedulekeeper.model.Customer;

public class DBConnect {

    private static final String DBURL = "jdbc:mysql://52.206.157.109/U04VEO";
    private static final String DBUSER = "U04VEO";
    private static final String DBPW = "53688353958";
    private static String USER;
    private static int USERID;
    private static Customer passedCustomer;

    public static String getUser() {
        return USER;
    }
    public static int getUserID() {
        return USERID;
    }
    
    public static void setPassedCustomer(Customer customer){
        passedCustomer = customer;
    }
    
    public static Customer getPassedCustomer(){
        return passedCustomer;
    }

    private static Connection getCon() {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPW);
            return con;
        } catch (SQLException e) {
            Errors.badCon();
        }
        return null;
    }

    private static void closeCon(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            Errors.badClose();
        }
    }

    public static boolean login(String username, String password) {
        USER = null;
        USERID = -1;
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT "
                    + "userId, userName, password "
                    + "FROM user "
                    + "WHERE userName = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString(3).equals(password)) {
                    USERID = rs.getInt(1);
                    USER = rs.getString(2);
                    closeCon(con);
                    try (PrintWriter log = new PrintWriter(new FileOutputStream(new File("log.txt"), true))) {
                        log.append("ID:" + USERID + System.lineSeparator() + "Username: " + USER
                                + System.lineSeparator()
                                + "Logged in " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm"))
                                + System.lineSeparator() + System.lineSeparator());
                    } catch (FileNotFoundException e) {
                        Errors.badLog();
                    }
                    reminder();
                    return true;
                } else {
                    Errors.badPassword(username, rs.getString(3));
                    closeCon(con);
                    return false;
                }
            } else {
                Errors.badUsername(username);
                closeCon(con);
                return false;
            }
        } catch (SQLException e) {
            Errors.badSQL("derped login query");
            closeCon(con);
            return false;
        }
    }
    
    public static void reminder(){
        //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    }

    public static ObservableList<Customer> getCustomerList() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        Connection con = getCon();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT "
                    + "customer.customerId, "
                    + "customer.customerName, "
                    + "customer.addressId, "
                    + "address.address, "
                    + "address.address2, "
                    + "address.postalCode, "
                    + "address.phone, "
                    + "address.cityId, "
                    + "city.city, "
                    + "city.countryId, "
                    + "country.country "
                    + "FROM customer, address, city, country "
                    + "WHERE customer.addressId = address.addressId AND "
                    + "address.cityId = city.cityId AND "
                    + "city.countryId = country.countryId");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                customerList.add(new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11)
                ));
            }
        }catch(SQLException e){
            Errors.badSQL("bad get customer list SQL");
        }
        closeCon(con);
        return customerList;
    }
    
    public static ObservableList<Appointment> getAppointmentList() {
        
    }

    public static void insertCustomer(Customer customer) {
        int addressId = -1;
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO address VALUES("
                    + "null, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "now(), "
                    + "?, "
                    + "now(), "
                    + "?)");
            ps.setString(1, customer.getAddress());
            ps.setString(2, customer.getAddress2());
            ps.setInt(3, customer.getCityId());
            ps.setString(4, customer.getPostalCode());
            ps.setString(5, customer.getPhone());
            ps.setString(6, USER);
            ps.setString(7, USER);
            ps.executeUpdate();
        }catch (SQLException e){
            Errors.badSQL("insert new address SQL");
            System.out.println(e.getMessage());
            closeCon(con);
            return;
        }
        
        try{
            PreparedStatement ps = con.prepareStatement("SELECT MAX(addressId) FROM address");
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                addressId = rs.getInt(1);
            }
        }catch(SQLException e){
            Errors.badSQL("get new addressId SQL");
            closeCon(con);
            return;
        }
        //insert customer with addressId
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES("
                    + "null, "
                    + "?, "
                    + "?, "
                    + "5, "
                    + "now(), "
                    + "?, "
                    + "now(), "
                    + "?)");
            ps.setString(1, customer.getCustomerName());
            ps.setInt(2, addressId);
            ps.setString(3, USER);
            ps.setString(4, USER);
            ps.executeUpdate();
        }catch(SQLException e){
            Errors.badSQL("insert new customer with new addressId SQL");
            closeCon(con);
            return;
        }
        
        closeCon(con);
    }

    public static void deleteCustomer(Customer customer) {
        Connection con = getCon();
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM customer "
                    + "WHERE customerId = ?");
            ps.setInt(1, customer.getCustomerId());
            ps.executeUpdate();
            ps = con.prepareStatement("DELETE FROM address "
                    + "WHERE addressId = ?");
            ps.setInt(1, customer.getAddressId());
            ps.executeUpdate();
        }catch(SQLException e){
            Errors.badSQL("delete address and customer SQL");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            closeCon(con);
        }
        
        closeCon(con);
    }

    public static void updateCustomer(Customer existingCustomer) {
        Connection con = getCon();
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE customer, address SET "
                    + "customer.customerName = ?, "
                    + "address.address = ?, "
                    + "address.address2 = ?, "
                    + "address.cityId = ?, "
                    + "address.postalCode = ?, "
                    + "address.phone = ?, "
                    + "customer.lastUpdate = now(), "
                    + "customer.lastUpdateBy = ?, "
                    + "address.lastUpdate = now(), "
                    + "address.lastUpdateBy = ? "
                    + "WHERE customer.customerId = ? AND "
                    + "address.addressId = ?");
            ps.setString(1, existingCustomer.getCustomerName());
            ps.setString(2, existingCustomer.getAddress());
            ps.setString(3, existingCustomer.getAddress2());
            ps.setInt(4, existingCustomer.getCityId());
            ps.setString(5, existingCustomer.getPostalCode());
            ps.setString(6, existingCustomer.getPhone());
            ps.setString(7, USER);
            ps.setString(8, USER);
            ps.setInt(9, existingCustomer.getCustomerId());
            ps.setInt(10, existingCustomer.getAddressId());
            ps.executeUpdate();
        }catch(SQLException e){
            Errors.badSQL("bad update customer and address");
            System.out.println(e.getMessage());
            closeCon(con);
        }
        closeCon(con);
    }

    

}
