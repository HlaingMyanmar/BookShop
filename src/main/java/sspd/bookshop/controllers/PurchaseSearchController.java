package sspd.bookshop.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sspd.bookshop.databases.Purchasedb;
import sspd.bookshop.models.Purchase;

import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;


public class PurchaseSearchController implements Initializable {

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
    private TextField psearch;

    @FXML
    private TextField psearch1;


    @FXML
    private TableView purchasetable;

    public static String purchaseID = null;

    @FXML
    void getQtyPrice(KeyEvent event) {

    }

    @FXML
    void purchasetableClickAction(MouseEvent event) {

        if(event.getClickCount()==2){

          Purchase purchase = (Purchase) purchasetable.getSelectionModel().getSelectedItem();


            purchaseID =  purchase.getPuid();

            Stage mainStage =(Stage) purchasetable.getScene().getWindow();

            mainStage.close();



        }

    }

    @FXML
    void searchPurchareBookAction(MouseEvent event) {

    }

    @FXML
    void searchpurchareAction(MouseEvent event){

        if(psearch.getText().equals("")){

            psearch1.setEditable(false);


            JOptionPane.showMessageDialog(null,"Please First Start , start filter box","Notice",0);

            psearch.setStyle("-fx-border-color:red;");



            psearch.setPromptText("Please Fill Filter check!!!");


        }
        else {

            psearch.setStyle("");


            psearch1.setEditable(true);

            purchasetable.getSelectionModel().selectAll();


            setPurchaseFilter();


        }

    }

    public void getFindLoadPurchaseData() {

        ObservableList<Purchase> observableList = FXCollections.observableArrayList();

        Purchasedb purchasedb = new Purchasedb();

        List<Purchase> purchaseList = null;

        purchaseList  = purchasedb.getList();

        for(Purchase m :purchaseList ){

            observableList.add(m);
        }


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Purchase> filteredData = new FilteredList<>(observableList, b -> true);

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
                else if (filter.getPudate().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getBcode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getAid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getSid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else if (filter.getCid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Purchase> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(purchasetable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        purchasetable.setItems(sortedData);




    }

    private void getIniPurchaseTable(){

        pcodeCol.setCellValueFactory(new PropertyValueFactory<Purchase,String>("puid"));
        pdateCol.setCellValueFactory(new PropertyValueFactory<Purchase,Date>("pudate"));
        pnameCol.setCellValueFactory(new PropertyValueFactory<>("bcode"));
        pcategoryCol.setCellValueFactory(new PropertyValueFactory<>("cid"));
        pauthorCol.setCellValueFactory(new PropertyValueFactory<>("aid"));
        psupplierCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        pqtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ppriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        ptotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));



    }
    private void setPurchaseFilter(){

        ObservableList i = purchasetable.getSelectionModel().getSelectedItems();

        ObservableList<Purchase> updateList= FXCollections.observableArrayList();

        int p=0;

        for(Object z: i){

            Purchase  purchase = (Purchase) i.get(p);
            updateList.add(purchase);
            p++;
        }

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Purchase> filteredData = new FilteredList<>(updateList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.

        psearch1.textProperty().addListener((observable, oldValue, newValue) -> {
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
                else if (filter.getPudate().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getBcode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getAid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getSid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else if (filter.getCid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Purchase> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(purchasetable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        purchasetable.setItems(sortedData);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getIniPurchaseTable();
        getFindLoadPurchaseData();
        purchasetable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }
}




