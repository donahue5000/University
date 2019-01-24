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

public class DBConnect {

    private static final String DBURL = "jdbc:mysql://52.206.157.109/U04VEO";
    private static final String DBUSER = "U04VEO";
    private static final String DBPW = "53688353958";
    private static String USER;
    private static int USERID;

    public static String getUser() {
        return USER;
    }
    public static int getUserID() {
        return USERID;
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
                        log.append("ID:" + USERID + " Username: " + USER
                                + " logged in at " + LocalDateTime.now().toString()
                                + System.lineSeparator());
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

}
