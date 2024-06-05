module sspd.bookshop.bookshop {
    requires javafx.controls;
    requires javafx.fxml;


    opens sspd.bookshop.models to javafx.fxml;
    exports sspd.bookshop.models;
    exports sspd.bookshop.controllers;
    opens sspd.bookshop.controllers to javafx.fxml;
}