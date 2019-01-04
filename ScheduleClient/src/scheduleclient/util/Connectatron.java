package scheduleclient.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectatron {

    private static final String URL = "jdbc:mysql://52.206.157.109/U04VEO";
    private static final String USER = "U04VEO";
    private static final String PW = "53688353958";
    
    public static Connection getCon() throws SQLException {
        return DriverManager.getConnection(URL, USER, PW);
    }

}
