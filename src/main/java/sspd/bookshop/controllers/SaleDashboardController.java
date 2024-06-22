package sspd.bookshop.controllers;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Order;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static sspd.bookshop.controllers.NewSaleController.oList;

public class SaleDashboardController implements Initializable {

    @FXML
    private TableColumn<Order, String> cuCol;

    @FXML
    private TableColumn<Order, String> cuphoneCol;

    @FXML
    private TableColumn<Order, Date> oDateCol;

    @FXML
    private TableColumn<Order, String> oidCol;

    @FXML
    private TableColumn<Order, Integer> totalCol;

    @FXML
    private TableView ordertable;

    @FXML
    private AnchorPane purchasePane1;

    @FXML
    private TextArea reportArea;

    @FXML
    private JFXCheckBox saleCheck;

    @FXML
    private JFXCheckBox salereturnCheck;

    @FXML
    private TextField ssearch;

    @FXML
    private TextField ssearch1;



    @FXML
    void getQtyPrice(KeyEvent event) {

    }

    @FXML
    void getSalePrint(MouseEvent event) {

    }

    @FXML
    void newSaleAction(MouseEvent event) throws IOException {

        Stage stage = new Stage();


        oList.clear();

        FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/newSale.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage mainStage = (Stage) reportArea.getScene().getWindow();
        stage.setTitle("New Sale");
        stage.initOwner(mainStage);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void purchasecheckBoxAction(MouseEvent event) {

    }

    @FXML
    void purchasereturncheckBoxAction(MouseEvent event) {

    }

    @FXML
    void searchPurchareBookAction(MouseEvent event) {

    }

    @FXML
    void searchpurchareAction(MouseEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getorderTableIni();


    }

    private void getorderTableIni(){


        oidCol.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        oDateCol.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        cuCol.setCellValueFactory(new PropertyValueFactory<>("culname"));
        cuphoneCol.setCellValueFactory(new PropertyValueFactory<>("cuphone"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

    }
}
