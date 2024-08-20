package sspd.bookshop.Chaing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {


    private static final String DB_DRIVER = "org.mariadb.jdbc.Driver"; //
    private static final String DB_IP= "192.168.100.162";
    private static final String DB_URL = "jdbc:mariadb://";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private static final String DB_Name ="bookshop_db";
    private static final String DB_PORT = "3306";



    public static Connection getConnection() {

        try {

            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(DB_URL+DB_IP+":"+DB_PORT+"/"+DB_Name+"?user="+DB_USER+"&password="+DB_PASS);

            // conn = DriverManager.getConnection(
            //         "jdbc:mariadb://192.168.100.163:3306/" + DB_Name + "?user=" + DB_USER + "&password=" + DB_PASS
            // );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection con = getConnection();

        if (con == null) {
            System.out.println("Not Connected");
        } else {
            System.out.println("Connected");
        }
    }

}
