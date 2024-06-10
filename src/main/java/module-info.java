module sspd.bookshop.bookshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;
    requires jasperreports;


    opens sspd.bookshop.models to javafx.fxml;
    exports sspd.bookshop.models;
    exports sspd.bookshop.controllers;
    opens sspd.bookshop.controllers to javafx.fxml;
    exports sspd.bookshop.DAO;
    opens sspd.bookshop.DAO to javafx.fxml;
    exports sspd.bookshop.launch;
    opens sspd.bookshop.launch to javafx.fxml;
    exports sspd.bookshop.databases;
    opens sspd.bookshop.databases to javafx.fxml;
}