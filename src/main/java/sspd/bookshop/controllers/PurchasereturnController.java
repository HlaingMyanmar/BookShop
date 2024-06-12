package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.PurchaseReturnDetail;

import java.sql.Date;
import java.util.List;

public class PurchasereturnController {

    @FXML
    private TableColumn<PurchaseReturnDetail, Integer> amountCol;

    @FXML
    private TableColumn<PurchaseReturnDetail, String> bcodeCol;

    @FXML
    private TextField findBookCode;

    @FXML
    private ImageView findPurchaseID;

    @FXML
    private TableColumn<PurchaseReturnDetail, String> pid;

    @FXML
    private TableColumn<PurchaseReturnDetail, Integer> qttCol;

    @FXML
    private TextField ramount;

    @FXML
    private TextField rdate;

    @FXML
    private TableColumn<PurchaseReturnDetail, Date> rdateCol;

    @FXML
    private TextField rdid;

    @FXML
    private TableColumn<PurchaseReturnDetail, String> remarkCol;

    @FXML
    private TableView returntable;

    @FXML
    private TableColumn<PurchaseReturnDetail, String> ridCol;

    @FXML
    private TextField rqty;

    @FXML
    private TextArea rreason;

    @FXML
    void addItem(MouseEvent event) {

    }

    @FXML
    void addlistItem(KeyEvent event) {

    }

    @FXML
    void confirmItem(MouseEvent event) {

    }

    @FXML
    void getTotal(KeyEvent event) {

    }

    @FXML
    void helpAction(KeyEvent event) {

    }

    @FXML
    void itemcodeKeyAction(KeyEvent event) {

    }

    @FXML
    void removeAction(MouseEvent event) {

    }

    private String getPurchaseReturnID(){

        String defaultid = "#ret1";

        Bookdb bookdb = new Bookdb();
        List<Book> bookList = bookdb.getList();

        if(bookList .size()==0){

            return defaultid;

        }
        else {

            return "#bo"+ Integer.toString(bookList .size()+1);

        }



    }
}
