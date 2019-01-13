package ScheduleClient.Util;

import ScheduleClient.Model.Appointment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Connectatron {

    private static final String DBURL = "jdbc:mysql://52.206.157.109/U04VEO";
    private static final String DBUSER = "U04VEO";
    private static final String DBPW = "xxxxxxx";
    public static String USER;
    public static int USERID;
    
    
    
    
    public static Connection getCon(){
        try{
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPW);
            return con;
        }catch (SQLException e){
            Oops.badGetCon();
        }
        return null;
    }
        
    public static void closeCon(Connection con){
        try{
            con.close();
        }catch (SQLException e){
            Oops.badCloseCon();
        }
    }
    
    public static ObservableList<Appointment> getAppointmentList(int userID){
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        Connection con = getCon();
        try{
//            PreparedStatement statement = con.prepareStatement("SELECT * FROM appointment"
//                    + " WHERE userId = ?");
            PreparedStatement statement = con.prepareStatement("SELECT appointment.*, "
                    + "customer.customerName FROM appointment, customer WHERE "
                    + "appointment.userId = ? AND appointment.customerId = customer.customerId");
            statement.setString(1, Integer.toString(Connectatron.USERID));
            ResultSet result = statement.executeQuery();
            while(result.next()){
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
        }catch(SQLException e){
            Oops.badGetCon();
            closeCon(con);
        }
        
        closeCon(con);
        return appointmentList;
    }
    
}
