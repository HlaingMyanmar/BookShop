package sspd.bookshop.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import sspd.bookshop.databases.PurchaseReturnDetaildb;

import sspd.bookshop.models.Purchase;

import sspd.bookshop.models.PurchaseReturnDetail;

import java.net.URL;
import java.sql.Date;

import java.util.List;
import java.util.ResourceBundle;

public class PurchasereturnReportController implements Initializable {

    @FXML
    private TableColumn<Purchase, String> booknameCol;

    @FXML
    private TableColumn<Purchase, Date> dateCol;



    @FXML
    private TableColumn<Purchase, Integer> pqtyCol;

    @FXML
    private TextField psearch;

    @FXML
    private TextField psearch1;

    @FXML
    private TableColumn<Purchase, Integer> ptotalCol;

    @FXML
    private TableColumn<Purchase, String> purchaseIDCol;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getIniPurchaseTable();
        getFindLoadPurchaseData();

    }


    private void getIniPurchaseTable(){

        dateCol.setCellValueFactory(new PropertyValueFactory<>("rdate"));
        booknameCol.setCellValueFactory(new PropertyValueFactory<>("bcode"));
        reasonCol.setCellValueFactory(new PropertyValueFactory<>("returnReason"));
        suppliernameCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        pqtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ptotalCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        purchaseIDCol.setCellValueFactory(new PropertyValueFactory<>("puid"));



    }

    public void getFindLoadPurchaseData() {

        ObservableList<PurchaseReturnDetail> observableList = FXCollections.observableArrayList();

        PurchaseReturnDetaildb purchasedb = new PurchaseReturnDetaildb();

        List<PurchaseReturnDetail> purchaseList = null;

        purchaseList  = purchasedb.getdetailList();

        for(PurchaseReturnDetail m :purchaseList ){

            observableList.add(m);
        }


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<PurchaseReturnDetail> filteredData = new FilteredList<>(observableList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        psearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(filter -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filter.getPuid().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else if (filter.getRdate().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getPuid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getBcode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getSid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }


                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<PurchaseReturnDetail> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(purchasetable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        purchasetable.setItems(sortedData);




    }


}
