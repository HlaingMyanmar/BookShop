package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.databases.Purchasedb;

import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Purchase;
import sspd.bookshop.modules.Deliver;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


public class PurcharestockController extends Deliver implements Initializable {

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, String> categroyCol;

    @FXML
    private TableColumn<Book, String> codeCol;

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
    private TableColumn<Book, String> nameCol;

    @FXML
    private TableColumn<Book, String> priceCol;

    @FXML
    private TableView purchasetable;

    @FXML
    private TableColumn<Book, String> qtyCol;

    @FXML
    private TextField stockdate;

    @FXML
    private TextField stockid;

    @FXML
    private ComboBox<String> supplierid;

    @FXML
    private ComboBox<String> suppliername;

    @FXML
    private TableColumn<String ,String> totalCol;

    ObservableList<Book> predataList = FXCollections.observableArrayList();

    @FXML
    void addItem(MouseEvent event) {

        String itemcodee = itemcode.getText();
        String itemnamee =itemname.getText();
        int  itemqtyy = Integer.parseInt(itemqty.getText());
        int itempricee =Integer.parseInt(itemprice.getText());
        String itemautho= itemauthor.getValue();
        String itemca=itemcategory.getValue();

        Book book = new Book(itemcodee,itemnamee,itemqtyy,itempricee,itemautho,itemca);

        predataList .add(book);

        purchasetable.setItems( predataList);

    }

    @FXML
    void confirmItem(MouseEvent event) {

    }
    @FXML

    void getTotal(KeyEvent event){

        if(event.getCode()== KeyCode.ENTER){

            int qty = Integer.parseInt(itemqty.getText());
            int price = Integer.parseInt(itemprice.getText());
            itemtotal.setText(String.valueOf(qty*price));

        }

    }

    @FXML
    void itemcodeKeyAction(KeyEvent event) {


        if(event.getCode()== KeyCode.ENTER){

            Book book =  getDataList(itemcode.getText());


            try{

                if(itemcode.getText().equals(getBookID())){

                    JOptionPane.showMessageDialog(null,"This is New ID");
                    itemname.setText("");
                    itemauthor.setValue("");
                    itemcategory.setValue("");
                    itemprice.setText("");
                    itemtotal.setText("");
                }
                else {

                    itemname.setText(book.getBookname());
                    itemauthor.setValue(book.getAid());
                    itemcategory.setValue(book.getCid());
                    itemprice.setText(String.valueOf(book.getPrice()));
                    itemtotal.setText(String.valueOf(book.getPrice()*book.getQuantity()));


                }






            }catch (NullPointerException e){

                JOptionPane.showMessageDialog(null,"Error");

            }



            // Book book =  getDataList(itemcode.getText());


        }






    }

    private String getBookID(){

        String defaultid = "#bo1";

        Bookdb bookdb = new Bookdb();
        List<Book> bookList = bookdb.getList();

        if(bookList .size()==0){

            return defaultid;

        }
        else {

            return "#bo"+ Integer.toString(bookList .size()+1);

        }



    }

    @FXML
    void findSupplierIDAction(MouseEvent event) {

        supplierid.setValue(getSupplierCode(suppliername.getValue()));
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

    private void getIniPurchaseTable(){

        codeCol.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        categroyCol.setCellValueFactory(new PropertyValueFactory<>("cid"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("aid"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stockid.setText(getID());

        suppliername.setItems(getSupplierNameList());

        stockdate.setText(String.valueOf(LocalDate.now()));

        getIniPurchaseTable();

        itemcode.setText(getBookID());


    }

    @FXML
    void helpAction(KeyEvent event) {

        if(event.getCode()== KeyCode.F1){

            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/booksearch.fxml"));
            Scene scene = null;


            try {


                scene = new Scene(fxmlLoader.load());


            } catch (IOException e) {

                throw new RuntimeException(e);

            }
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            Stage mainStage = (Stage) itemauthor.getScene().getWindow();
            stage.initOwner(mainStage);
            stage.setScene(scene);
            stage.show();



        }

    }

}

