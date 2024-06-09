package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import sspd.bookshop.databases.Purchasedb;

import sspd.bookshop.models.Purchase;
import sspd.bookshop.modules.Deliver;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class PurcharestockController extends Deliver implements Initializable {

    @FXML
    private TableColumn<?, ?> authorCol;

    @FXML
    private TableColumn<?, ?> categroyCol;

    @FXML
    private TableColumn<?, ?> codeCol;

    @FXML
    private ComboBox<String> itemauthor;

    @FXML
    private ComboBox<String> itemcategory;

    @FXML
    private TextField itemcode;

    @FXML
    private TextField itemname;

    @FXML
    private TextField itemprice;

    @FXML
    private TextField itemqty;

    @FXML
    private TextField itemtotal;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> priceCol;

    @FXML
    private TableView<?> purchasetable;

    @FXML
    private TableColumn<?, ?> qtyCol;

    @FXML
    private TextField stockdate;

    @FXML
    private TextField stockid;

    @FXML
    private ComboBox<String> supplierid;

    @FXML
    private ComboBox<String> suppliername;

    @FXML
    private TableColumn<?, ?> totalCol;

    @FXML
    void addItem(MouseEvent event) {

    }

    @FXML
    void confirmItem(MouseEvent event) {

    }

    @FXML
    void findSupplierIDAction(MouseEvent event) {

        supplierid.setValue(getSupplierName(suppliername.getValue()));
    }

    private String getID(){

        String defaultid = "#it1";

        Purchasedb pd = new Purchasedb();
        List<Purchase> purchaseList = pd.getList();

        if(purchaseList .size()==0){

            return defaultid;

        }
        else {

            return "#it"+ Integer.toString(purchaseList .size()+1);

        }



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stockid.setText(getID());


    }
}
