package sspd.bookshop.Chaing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {


    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver"; //
    private static final String urlGetDBParameters = "autoReconnect=true&useSSL=false";// Updated driver class name
    private static final String DB_URL = "jdbc:mysql://192.168.100.143:3306/bookshop_db" + urlGetDBParameters;
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    public static Connection getConnection() {



        Connection conn = null;



        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {


        Connection con = getConnection();

        if(con==null){
            System.out.println("Not Connect");
        }
        else {
            System.out.println("Connect");
        }

    }
}
