package sspd.bookshop.DAO;


import java.sql.*;

public class DatabaseConnector {

    private static final String DB_DRIVER = "con.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bookdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static DatabaseConnector instance = null;

    private static Connection con =null;

    // using Singleton Design Pattern
    // place Constructor private modifier

    private DatabaseConnector(){

        try {


            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            if(con!=null) {

                System.out.println("Connetion successful");
            }

            else
            {

                System.out.println("Connetion Not successful");
            }


        } catch (SQLException e) {

            System.out.println("SQLException Error");
        }



    }

    // using to get DatabaseConnector instance Object
    public static DatabaseConnector getDatabaseConnector(){

        if(instance==null){

            instance = new DatabaseConnector();

        }

        return instance;
    }

    // using to get Database Connection
    public static Connection getConnect() {

        return con;
    }

    // Method Overloading
    // using to Close Database Connection and PreparedStatement
    public static void getCloseConnection(Connection con,PreparedStatement stmt ){

        if(con!=null) {

            try {
                con.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }

        }

        if(stmt!=null) {

            try {
                stmt.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    // using to Close Database Connection , PreparedStatement and ResultSet
    public static void getCloseConnection(Connection con, PreparedStatement stmt , ResultSet set){

        if(con!=null) {

            try {
                con.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }

        }

        if(stmt!=null) {

            try {

                stmt.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        if(set!=null) {

            try {

                set.close();

            } catch (SQLException e) {



                e.printStackTrace();
            }
        }




    }
}
