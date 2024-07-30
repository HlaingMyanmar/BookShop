package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sspd.bookshop.databases.Purchasedb;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Purchase;
import sspd.bookshop.modules.IDGenerate;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static sspd.bookshop.modules.IDGenerate.getID;

public class NewPurchaseController implements Initializable {


    @FXML
    private TextField datetxt;

    @FXML
    private Label lbCount;

    @FXML
    private Label lbTotal;

    @FXML
    private TextField purchaseidtxt;

    @FXML
    private TextField purchasenametxt;


    @FXML
    private ComboBox<String> stockauthorcobox;

    @FXML
    private ComboBox<String> stockcategorycobox;

    @FXML
    private ComboBox<String> saleidtxt;

    @FXML
    private TextField stockidtxt;

    @FXML
    private TextField stocknametxt;

    @FXML
    private TextField stockpricetxt;

    @FXML
    private TextField stockqtytxt;

    @FXML
    private TextArea stockremarktxt;

    @FXML
    private TextField stocktotaltxt;

    @FXML
    private TableColumn<Book, String> codeCol;

    @FXML
    private TableColumn<Book, String> nameCol;

    @FXML
    private TableColumn<Book, Double> priceCol;

    @FXML
    private TableView  purchasetable;

    @FXML
    private TableColumn<Book, Integer> qtyCol;

    @FXML
    private TableColumn<Book, Double> totalCol;






    public void ini(){

         purchaseidtxt.setText(getPurchaseID("#P1"));
         datetxt.setText(String.valueOf(LocalDate.now()));





    }

    private String getPurchaseID(String prefix){

        Purchasedb purchasedb = new Purchasedb();

        String id = purchasedb.getList().getFirst().getPuid();

        getID(prefix,id);

        return id;

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();

    }
}
