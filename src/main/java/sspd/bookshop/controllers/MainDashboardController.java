package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sspd.bookshop.launch.Bookshop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sspd.bookshop.controllers.LoginController._level;
import static sspd.bookshop.controllers.NewSaleController.oList;

public class MainDashboardController implements Initializable {

    @FXML
    private MenuItem showStockbtn;

    @FXML
    private AnchorPane switchPane;


    @FXML
    private MenuItem showpurchasebtn;
    @FXML
    private Label loginlevel;

    @FXML
    private MenuItem exitbtn;
    @FXML
    private MenuItem saledashboard;

    @FXML
    private MenuItem newSalebtn;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginlevel.setText(_level);


        ini();


    }




    private void  ini(){

        saledashboard.setOnAction(event -> {

            FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/sale.fxml"));
            Node node = null;

            try {

                node = fxmlLoader.load();
                switchPane.getChildren().clear();
                switchPane.getChildren().add(node);



            } catch (IOException e) {

                throw new RuntimeException(e);
            }



        });

        newSalebtn.setOnAction(_ -> {

            Stage stage = new Stage();


            oList.clear();


            FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/newSales.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            Stage mainStage = (Stage) switchPane.getScene().getWindow();
            stage.setTitle("New Sale");
            stage.initOwner(mainStage);
            stage.setScene(scene);
            stage.show();

        });

        exitbtn.setOnAction(_ -> {

            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/loginform.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            Stage mainStage = (Stage) loginlevel.getScene().getWindow();
            stage.setTitle("အကောင့်ဝင်ရန်");
            stage.setScene(scene);
            mainStage.close();
            stage.show();

        });



        showStockbtn.setOnAction(_ -> {

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
