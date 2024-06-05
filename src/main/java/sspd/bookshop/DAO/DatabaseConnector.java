package sspd.bookshop.DAO;


import java.sql.Connection;

public class DatabaseConnector {

    private DatabaseConnector instance;

    private Connection con;

    private DatabaseConnector(){


    }



    public DatabaseConnector getDatabaseConnector(){

        if(instance==null){
            instance = new DatabaseConnector();
        }
        return instance;
    }
}
