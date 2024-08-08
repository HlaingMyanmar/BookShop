package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sspd.bookshop.launch.Bookshop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainDashboardController implements Initializable {

    @FXML
    private MenuItem showStockbtn;

    @FXML
    private AnchorPane switchPane;


    @FXML
    private MenuItem showpurchasebtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ini();


    }


    private void  ini(){

        showStockbtn.setOnAction(event -> {

            FXMLLoader fxmlLoader2 = new FXMLLoader(Bookshop.class.getResource("/layout/stock.fxml"));
            Node node = null;

            try {

                node = fxmlLoader2.load();
                switchPane.getChildren().clear();
                switchPane.getChildren().add(node);



            } catch (IOException e) {

                throw new RuntimeException(e);
            }

        });

        showpurchasebtn.setOnAction(event -> {

            FXMLLoader fxmlLoader2 = new FXMLLoader(Bookshop.class.getResource("/layout/buy.fxml"));
            Node node = null;

            try {

                node = fxmlLoader2.load();
                switchPane.getChildren().clear();
                switchPane.getChildren().add(node);



            } catch (IOException e) {

                throw new RuntimeException(e);
            }


        });

    }
}
