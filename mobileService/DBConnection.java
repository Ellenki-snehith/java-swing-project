package mobileService;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static final String URL = "jdbc:mysql://localhost:3307/mobile_service_center";
    static final String USER = "root";
    static final String PASSWORD = "1440";   // change this

    public static Connection getConnection() {

        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}