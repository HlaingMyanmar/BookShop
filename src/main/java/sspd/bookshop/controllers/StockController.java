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
import sspd.bookshop.models.Book;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StockController implements Initializable {

    @FXML
    private TableColumn<Book, String> bauthorCol;

    @FXML
    private TableColumn<Book,String> bcategoryCol;

    @FXML
    private TableColumn<Book, String> bcodeCol;

    @FXML
    private TableColumn<Book, String> bnameCol;

    @FXML
    private TableView booktable;

    @FXML
    private TableColumn<Book, Double> bpriceCol;

    @FXML
    private TableColumn<Book, Double> bqtyCol;

    @FXML
    private TableColumn<Book, Double> btotalCol;

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

    private void getIniBookTable() {

        bcodeCol.setCellValueFactory(new PropertyValueFactory<Book, String>("bookid"));
        bnameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("bookname"));
        bcategoryCol.setCellValueFactory(new PropertyValueFactory<Book, String>("cid"));
        bauthorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("aid"));
        bqtyCol.setCellValueFactory(new PropertyValueFactory<Book, Double>("quantity"));
        bpriceCol.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        btotalCol.setCellValueFactory(new PropertyValueFactory<Book, Double>("total"));


    }

    public void initialize() {

        getIniBookTable();

        ObservableList<Book> observableList = FXCollections.observableArrayList();

        Bookdb bookdb = new Bookdb();
        List<Book> bookList = bookdb.getList();
        observableList.addAll(bookList);

        FilteredList<Book> filteredData = new FilteredList<>(observableList, b -> true);

        addTextFieldListener(searchCom, filteredData);
        addTextFieldListener(searchGroup, filteredData);
        addTextFieldListener(searchID, filteredData);
        addTextFieldListener(searchItem, filteredData);
        addTextFieldListener(searchPrice, filteredData);
        addTextFieldListener(searchQty, filteredData);
        addTextFieldListener(searchTotal, filteredData);

        SortedList<Book> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(booktable.comparatorProperty());

        booktable.setItems(sortedData);
    }

    private void addTextFieldListener(TextField textField, FilteredList<Book> filteredData) {


        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(book -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (book.getBookid().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (book.getBookname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (book.getAid().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (book.getCid().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchCom && book.getCid().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchGroup && book.getAid().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchItem && book.getBookname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchPrice && String.valueOf(book.getPrice()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchQty && String.valueOf(book.getQuantity()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (textField == searchTotal && String.valueOf(book.getTotal()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }
}





