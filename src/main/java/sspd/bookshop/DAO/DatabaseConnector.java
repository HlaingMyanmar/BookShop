package sspd.bookshop.DAO;


import java.sql.*;

public class DatabaseConnector {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bookdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static DatabaseConnector instance = null;

    private static Connection con =null;

    // using Singleton Design Pattern
    // place Constructor private modifier


    private DatabaseConnector() {


        try {

            Class.forName(DB_DRIVER);

            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);

            if(con==null) {
                System.out.println("Can't Connect");
            }
            else {
                System.out.println("Connection Successfully");
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static DatabaseConnector getInstance() {

        try {

            if(instance==null) {

                instance = new DatabaseConnector();

            }
        } catch (Exception e) {


            e.printStackTrace();
        }

        return instance;
    }

    public static Connection getConn() {

        return con;
    }
    public static void colseConn(Connection con,PreparedStatement stmt ){

        if(con!=null) {

            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        if(stmt!=null) {

            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void colseConn(Connection con,PreparedStatement stmt , ResultSet set){

        if(con!=null) {

            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        if(stmt!=null) {

            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if(set!=null) {
            try {
                set.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }




    }


}
