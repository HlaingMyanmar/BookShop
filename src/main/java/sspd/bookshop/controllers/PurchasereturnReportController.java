package sspd.bookshop.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PurchasereturnReportController {

    @FXML
    private TableColumn<?, ?> pauthorCol;

    @FXML
    private TableColumn<?, ?> pcategoryCol;

    @FXML
    private TableColumn<?, ?> pcodeCol;

    @FXML
    private TableColumn<?, ?> pdateCol;

    @FXML
    private TableColumn<?, ?> pnameCol;

    @FXML
    private TableColumn<?, ?> ppriceCol;

    @FXML
    private TableColumn<?, ?> pqtyCol;

    @FXML
    private TextField psearch;

    @FXML
    private TextField psearch1;

    @FXML
    private TableColumn<?, ?> psupplierCol;

    @FXML
    private TableColumn<?, ?> ptotalCol;

    @FXML
    private AnchorPane purchasePane;

    @FXML
    private TableView<?> purchasetable;

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

}
