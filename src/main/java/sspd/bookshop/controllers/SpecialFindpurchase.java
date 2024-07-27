package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.databases.Purchasedb;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Purchase;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SpecialFindpurchase implements Initializable {

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
    private TableView purchasetable;

    @FXML
    private TextField searchCom;

    @FXML
    private TextField searchGroup;

    @FXML
    private TextField searchID;

    @FXML
    private TextField searchItem;

    @FXML
    private TextField searchPrice;

    @FXML
    private TextField searchQty;

    @FXML
    private TextField searchTotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initialize();

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


    }

    public void initialize() {

        getIniPurchaseTable();

        ObservableList<Purchase> observableList = FXCollections.observableArrayList();

        Purchasedb purchasedb = new Purchasedb();
        List<Purchase> purchaseList = purchasedb.getList();
        observableList.addAll(purchaseList);

        FilteredList<Purchase> filteredData = new FilteredList<>(observableList, b -> true);

        addTextFieldListener(searchCom, filteredData);
        addTextFieldListener(searchGroup, filteredData);
        addTextFieldListener(searchID, filteredData);
        addTextFieldListener(searchItem, filteredData);
        addTextFieldListener(searchPrice, filteredData);
        addTextFieldListener(searchQty, filteredData);
        addTextFieldListener(searchTotal, filteredData);

        SortedList<Purchase> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(purchasetable.comparatorProperty());

        purchasetable.setItems(sortedData);

    }

    private void addTextFieldListener(TextField textField, FilteredList<Purchase> filteredData) {

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(purchase -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (textField == searchID && purchase.getPuid().equalsIgnoreCase(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchCom && purchase.getSid().contains(lowerCaseFilter)) {
                    return true;
                } else if (purchase.getPudate().toString().equalsIgnoreCase(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchItem && purchase.getBcode().contains(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchGroup && purchase.getCid().contains(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchGroup && purchase.getAid().contains(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchPrice && String.valueOf(purchase.getPrice()).equalsIgnoreCase(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchQty && String.valueOf(purchase.getQty()).equalsIgnoreCase(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchTotal && String.valueOf(purchase.getTotal()).equalsIgnoreCase(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }



}



