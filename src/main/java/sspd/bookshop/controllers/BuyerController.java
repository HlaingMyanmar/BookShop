package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sspd.bookshop.databases.Purchasedb;
import sspd.bookshop.models.Purchase;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class BuyerController implements Initializable {

    @FXML
    private TableColumn<Purchase, String> pauthorCol;

    @FXML
    private TableColumn<Purchase, String> pcategoryCol;

    @FXML
    private TableColumn<Purchase, String> pcodeCol;

    @FXML
    private TableColumn<Purchase, Date> pdateCol;

    @FXML
    private TableColumn<Purchase, String> pnameCol;

    @FXML
    private TableColumn<Purchase, Integer> ppriceCol;

    @FXML
    private TableColumn<Purchase, Integer> pqtyCol;

    @FXML
    private TableColumn<Purchase, String> psupplierCol;

    @FXML
    private TableColumn<Purchase, Integer> ptotalCol;

    @FXML
    private TableColumn<Purchase, String>editCol;

    @FXML
    private TableView purchasetable;

    @FXML
    private Label lbCount;

    @FXML
    private Label lbTotal;

    @FXML
    private DatePicker dayPicker;

    @FXML
    private Spinner<Integer> monthPicker;

    @FXML
    private Spinner<Integer> yearPicker;

    @FXML
    private Button daybtn;

    @FXML
    private Button monthbtn;

    @FXML
    private Button yearbtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getIni();

    }



    private void getIniPurchaseTable() {

        pcodeCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("puid"));
        pdateCol.setCellValueFactory(new PropertyValueFactory<Purchase, Date>("pudate"));
        pnameCol.setCellValueFactory(new PropertyValueFactory<>("bcode"));
        pcategoryCol.setCellValueFactory(new PropertyValueFactory<>("cid"));
        pauthorCol.setCellValueFactory(new PropertyValueFactory<>("aid"));
        psupplierCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        pqtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ppriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        ptotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        editCol.setCellValueFactory(new PropertyValueFactory<>(""));


    }

    private void getIni(){

        int year = LocalDate.now().getYear();
        yearPicker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, year));

        int month = LocalDate.now().getMonthValue();
        monthPicker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));
        monthPicker.getValueFactory().setValue(month);


        getIniPurchaseTable();

        getLoadData();

    }


    private  void getLoadData(){

        try {

            Purchasedb purchasedb = new Purchasedb();
            List<Purchase> purchaseList = purchasedb.getDate(Date.valueOf(dayPicker.getValue()));

            purchasetable.getItems().setAll(purchaseList);

        }catch (NullPointerException _){


        }

    }



}
