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
import sspd.bookshop.modules.Deliver;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static sspd.bookshop.modules.IDGenerate.getID;

public class NewPurchaseController extends Deliver implements Initializable {


    @FXML
    private TextField datetxt;

    @FXML
    private Label lbCount;

    @FXML
    private Label lbTotal;

    @FXML
    private TextField purchaseidtxt;

    @FXML
    private TextField supplierid;


    @FXML
    private ComboBox<String> stockauthorcobox;

    @FXML
    private ComboBox<String> stockcategorycobox;

    @FXML
    private ComboBox<String> suppliername;

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

         purchaseidtxt.setText(getPurchaseID());
         datetxt.setText(String.valueOf(LocalDate.now()));

         //Set Supplier Name
          getSupplierName(suppliername,supplierid);









    }

    private String getPurchaseID(){

        Purchasedb purchasedb = new Purchasedb();

        String id= (purchasedb.getList().isEmpty())?null: (purchasedb.getList().getFirst().getPuid());

        return  getID("#P-", id);

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();

    }
}
