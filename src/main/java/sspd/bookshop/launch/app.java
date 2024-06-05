package sspd.bookshop.launch;

import sspd.bookshop.DAO.DatabaseConnector;

import java.sql.Connection;

public class app {

    public static void main(String[] args) {

        Connection con = DatabaseConnector.getConnect();





    }
}
