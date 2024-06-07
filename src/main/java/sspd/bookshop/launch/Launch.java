package sspd.bookshop.launch;




import javafx.application.Application;
import sspd.bookshop.DAO.DatabaseConnector;

import java.sql.Connection;



public class Launch {

    public static void main(String[] args) {


        Application.launch(Bookshop.class, args);

    }
}
