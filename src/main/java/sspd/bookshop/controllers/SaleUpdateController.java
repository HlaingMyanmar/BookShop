package sspd.bookshop.controllers;

import javafx.collections.ObservableList;
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
import sspd.bookshop.databases.Saledb;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Sale;
import sspd.bookshop.modules.Deliver;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static sspd.bookshop.controllers.NewSaleController.oList;
import static sspd.bookshop.controllers.SaleDashboardController._ordered;

public class SaleUpdateController extends Deliver implements Initializable {

    @FXML
    private Label amountlb;

    @FXML
    private TextField aname;



    @FXML
    private TextField bcode;

    @FXML
    private TextField bname;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, String> bookcodeCol;

    @FXML
    private TableColumn<Sale, String> booknameCol;

    @FXML
    private TableColumn<Book, String> categoryCol;

    @FXML
    private TableColumn<Book, Integer> qtyCol;

    @FXML
    private TableColumn<Book, Integer> priceCol;

    @FXML
    private TableColumn<Book, Integer> totalCol;

    @FXML
    private TextField caname;



    @FXML
    private TextField cname;

    @FXML
    private TextField cphone;

    @FXML
    private TextField odate;

    @FXML
    private TextField oid;

    @FXML
    private TableView otable;

    @FXML
    private Label pcslb;



    @FXML
    private TextField ptxt;


    @FXML
    private TextField qtytxt;

    @FXML
    private AnchorPane test;

    @FXML
    private TextField total;



    @FXML
    void addItem(MouseEvent event) {

    }

    @FXML
    void addlistItem(KeyEvent event) {

    }

    @FXML
    void comfirm(MouseEvent event) {

    }

    @FXML
    void getDataAction(MouseEvent event) {

    }

    @FXML
    void getbook(KeyEvent event) {

    }

    @FXML
    void helpAction(KeyEvent event) {

    }

    @FXML
    void print(MouseEvent event) {

    }

    @FXML
    void purchaseCodeKeyAction(KeyEvent event) {

    }

    @FXML
    void removeItem(MouseEvent event) {

    }
    private void getordertableInit(){

        bookcodeCol.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        booknameCol.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("cid"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("aid"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getordertableInit();

        oid.setText(_ordered.getOrderid());
        odate.setText(String.valueOf(_ordered.getOrderdate()));
        cname.setText(_ordered.getCulname());
        cphone.setText(_ordered.getCuphone());

        getOrderList();




    }

    private void getOrderList(){

        Saledb saledb = new Saledb();

        oList.clear();

        List<Book> bookList=  saledb.findByOrderID(_ordered.getOrderid());

        for (Book b:bookList){

            Book book = new Book(b.getBookid(),getBookName(b.getBookid()),b.getQuantity(),b.getPrice(),getAuthorName(b.getAid()),getCategoryName(b.getCid()));

            oList.add(book);
        }

        otable.getItems().setAll(oList);


    }
}

