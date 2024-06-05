package sspd.bookshop.DAO;


import java.sql.*;

public class DatabaseConnector {

    private static final String DB_DRIVER = "con.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bookdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static DatabaseConnector instance = null;

    private static Connection con =null;

    private DatabaseConnector(){

        try {

            Class.forName(DB_DRIVER);

            con = DriverManager.getConnection(DB_URL, DB_USER, DB_DRIVER);

            if(con!=null) {

                System.out.println("Connetion successful");
            }

            else
            {

                System.out.println("Connetion Not successful");
            }



        } catch (ClassNotFoundException e) {

            System.out.println("ClassNotFound Error");


        } catch (SQLException e) {

            System.out.println("SQLException Error");
        }



    }

    public static DatabaseConnector getDatabaseConnector(){

        if(instance==null){
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public static Connection getConnect() {

        return con;
    }

    public static void getCloseConnection(Connection con,PreparedStatement stmt ){

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
