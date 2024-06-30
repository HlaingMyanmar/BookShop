package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.launch.Bookshop;

import java.io.IOException;
import java.sql.Connection;

import static sspd.bookshop.controllers.NewSaleController.oList;

public class ApplicationViewController {



    @FXML
    private ImageView bookimg;


    static String cho = null;

    Connection con = DatabaseConnector.getInstance().getConn();




    @FXML
    void saleAction(MouseEvent event) throws IOException {

        Stage stage = new Stage();


        oList.clear();

        FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/saledashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage mainStage = (Stage) bookimg.getScene().getWindow();
        stage.setTitle("New Sale");
        stage.initOwner(mainStage);
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    void purchaseAction(MouseEvent event) throws IOException {


        Stage stage = new Stage();

        cho = "P";


        oList.clear();

        FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/maindashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage mainStage = (Stage) bookimg.getScene().getWindow();
        stage.setTitle("Purchase Option");
        stage.initOwner(mainStage);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void stockAction(MouseEvent event) throws IOException {

        Stage stage = new Stage();

        cho = "S";


        oList.clear();

        FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/maindashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage mainStage = (Stage) bookimg.getScene().getWindow();
        stage.setTitle("Purchase Option");
        stage.initOwner(mainStage);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void supplierAction(MouseEvent event) throws IOException {

        Stage stage = new Stage();

        cho = "Si";


        oList.clear();

        FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/maindashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage mainStage = (Stage) bookimg.getScene().getWindow();
        stage.setTitle("Supplier Option");
        stage.initOwner(mainStage);
        stage.setScene(scene);
        stage.show();



    }


}
