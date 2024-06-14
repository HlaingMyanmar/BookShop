package sspd.bookshop.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sspd.bookshop.models.Purchase;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class PurchasereturnReportController implements Initializable {

    @FXML
    private TableColumn<Purchase, String> booknameCol;

    @FXML
    private TableColumn<Purchase, Date> dateCol;

    @FXML
    private TableColumn<Purchase, Integer> ppriceCol;

    @FXML
    private TableColumn<Purchase, Integer> pqtyCol;

    @FXML
    private TextField psearch;

    @FXML
    private TextField psearch1;

    @FXML
    private TableColumn<Purchase, Integer> ptotalCol;

    @FXML
    private TableColumn<Purchase, String> purchaseID;

    @FXML
    private AnchorPane purchasePane;

    @FXML
    private TableView purchasetable;

    @FXML
    private TableColumn<Purchase, String> reasonCol;

    @FXML
    private TableColumn<Purchase, String> suppliernameCol;

    @FXML
    private Label totalPrice;

    @FXML
    private Label totalQty;

    @FXML
    void getPurchaseSelectPrint(MouseEvent event) {

    }

    @FXML
    void getQtyPrice(KeyEvent event) {

    }

    @FXML
    void newPurchareAction(MouseEvent event) {

    }

    @FXML
    void purchasetableClickAction(MouseEvent event) {

    }

    @FXML
    void searchPurchareBookAction(MouseEvent event) {

    }

    @FXML
    void searchpurchareAction(MouseEvent event) {

    }

    private void getIniPurchaseTable(){

        dateCol.setCellValueFactory(new PropertyValueFactory<Purchase,Date>("pudate"));
        booknameCol.setCellValueFactory(new PropertyValueFactory<>("bcode"));
        reasonCol.setCellValueFactory(new PropertyValueFactory<>("remark"));
        suppliernameCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        pqtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ppriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        ptotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getIniPurchaseTable();

    }
}
