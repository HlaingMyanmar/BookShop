package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
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

import static sspd.bookshop.controllers.SaleController.checkPoint;


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
    private TableColumn<Book, Integer> priceCol;

    @FXML
    private TableView purchasetable;

    @FXML
    private TableColumn<Book, Integer> qtyCol;

    @FXML
    private TextField stockdate;

    @FXML
    private TextField stockid;
    @FXML
    private Button btnGet;

    @FXML
    private ComboBox<String> supplierid;

    @FXML
    private ComboBox<String> suppliername;



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

        Bookdb bookdb = new Bookdb();



        predataList .add(book);



            purchasetable.setItems( predataList);


            itemname.setText("");
            itemauthor.setValue("");
            itemcategory.setValue("");
            itemqty.setText("");
            itemprice.setText("");
            itemtotal.setText("");


        itemcodee =getBookID();

        String id = itemcodee;

        itemcode.setText("#bo"+(Integer.parseInt(id.substring(3))+1));

    }
    @FXML
    void addlistItem(KeyEvent event) {

        if(event.getCode()==KeyCode.ENTER){


            String itemcodee = itemcode.getText();
            String itemnamee =itemname.getText();
            int  itemqtyy = Integer.parseInt(itemqty.getText());
            int itempricee =Integer.parseInt(itemprice.getText());
            String itemautho= itemauthor.getValue();
            String itemca=itemcategory.getValue();

            Book book = new Book(itemcodee,itemnamee,itemqtyy,itempricee,itemautho,itemca);

            Bookdb bookdb = new Bookdb();



            predataList .add(book);



            purchasetable.setItems( predataList);


            itemname.setText("");
            itemauthor.setValue("");
            itemcategory.setValue("");
            itemqty.setText("");
            itemprice.setText("");
            itemtotal.setText("");


            String id = itemcodee;

            itemcode.setText("#bo"+(Integer.parseInt(id.substring(3))+1));

        }

    }
    @FXML
    void findSupplierID(KeyEvent event) {

        if(event.getCode()==KeyCode.ENTER){

            supplierid.setValue(getSupplierCode(suppliername.getValue()));


        }


    }

    @FXML
    void confirmItem(MouseEvent event) {



        String puid = stockid.getText();
        Date pudate = Date.valueOf(stockdate.getText());
        String sid = supplierid.getValue();


        if(supplierid.getValue() == null ||stockid.getText().equals("") || stockdate.getText().equals("")){

            JOptionPane.showMessageDialog(null,"Please Insert Supplier ID");

        }

        else {


            int size = purchasetable.getItems().size();

            Bookdb bookdb = new Bookdb();

            Purchasedb purchasedb = new Purchasedb();


            for(int i = 0;i<size;i++){

                Book book = (Book)  purchasetable.getItems().get(i);


                if(!book.getBookid().equals( getBookID())){

//                Purchasedb purchasedb = new Purchasedb();
//
//                Purchase p = new Purchase(puid,pudate,book.getBookid(),getCategoryCode(book.getCid()),getAuthorCode(book.getAid()),sid,book.getQuantity(),book.getPrice());
//                purchasedb.create(p);
//
                   // bookdb.sumQty(book);

                    purchasedb.sumBookQty(book);

                }
                else {



                    Book newBook = new Book(book.getBookid(),book.getBookname(),book.getQuantity(),book.getPrice(),getAuthorCode(book.getAid()),getCategoryCode(book.getCid()));

                   // bookdb.create(newBook);

                    purchasedb.createBook(newBook);



                }

                Purchase p = new Purchase(puid,pudate,book.getBookid(),getCategoryCode(book.getCid()),getAuthorCode(book.getAid()),sid,book.getQuantity(),book.getPrice());
                purchasedb.create(p);


            }

            Stage subStage = (Stage) purchasetable.getScene().getWindow();



            checkPoint = 1;

            subStage.close();

        }















    }

    @FXML

    void getTotal(KeyEvent event){

        try {

            if(event.getCode()== KeyCode.ENTER || event.getCode()==KeyCode.TAB){

                int qty = Integer.parseInt(itemqty.getText());
                int price = Integer.parseInt(itemprice.getText());
                itemtotal.setText(String.valueOf(qty*price));

            }

        }catch (NumberFormatException e){

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


    private String getPurchaseID(){

        String defaultid = "#it1";

        Purchasedb pd = new Purchasedb();
        List<Purchase> purchaseList = pd.getList();



        if(purchaseList.isEmpty()){

            return defaultid;

        }
        else {

            String id = purchaseList.getFirst().getPuid();

            String subid = id.substring(3);


            return "#it"+ (Integer.parseInt(subid)+1);


        }



    }

    private void getIniPurchaseTable(){

        codeCol.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        categroyCol.setCellValueFactory(new PropertyValueFactory<>("cid"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("aid"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stockid.setText(getPurchaseID());

        suppliername.setItems(getSupplierNameList());

        stockdate.setText(String.valueOf(LocalDate.now()));

        getIniPurchaseTable();

        itemcode.setText(getBookID());

        itemauthor.setItems(getAuthorNameList());

        itemcategory.setItems(getCategoryNameList());



        purchasetable.setEditable(true);

        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        nameCol.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if(null != value && !value.isEmpty()){

                event.getRowValue().setBookname(value);



            }

        });

        categroyCol.setCellFactory(TextFieldTableCell.forTableColumn());

        categroyCol.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if(null != value && !value.isEmpty()){

                event.getRowValue().setCid(value);



            }

        });

        authorCol.setCellFactory(TextFieldTableCell.forTableColumn());

        authorCol.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if(null != value && !value.isEmpty()){

                event.getRowValue().setAid(value);

                getBookRowUpdate();

            }

        });
        qtyCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        qtyCol.setOnEditCommit(event -> {
            Integer value = event.getNewValue();

            if (value != null) {

                event.getRowValue().setQuantity(value);
            }
        });
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceCol.setOnEditCommit(event -> {
            Integer value = event.getNewValue();

            if (value != null) {

                event.getRowValue().setPrice(value);

            }
        });



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

    @FXML
    void getFilterData(MouseEvent event) {

        try {



        itemcode.setText(_book.getBookid());
        itemname.setText(_book.getBookname());
        itemauthor.setValue(_book.getAid());
        itemcategory.setValue(_book.getCid());
        itemprice.setText(String.valueOf(_book.getPrice()));
        itemtotal.setText(String.valueOf(_book.getPrice()*_book.getQuantity()));

        _book =null;
        btnGet.setText("Get New Item");
        btnGet.setStyle("-fx-background-color:#FC4035;");


        }catch (NullPointerException e){

            itemcode.setText(getBookID());
            itemname.setText("");
            itemauthor.setValue("");
            itemcategory.setValue("");
            itemqty.setText("");
            itemprice.setText("");
            itemtotal.setText("");
            btnGet.setText("Get");
            btnGet.setStyle("-fx-background-color:#0072CD;");


        }







    }

    @FXML
    void removeAction(MouseEvent event) {

      int selectedIndex = purchasetable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            purchasetable.getItems().remove(selectedIndex);
        } else {
            // Handle case where no item is selected (optional)
            System.out.println("No item selected");
        }











    }

    private void getBookRowUpdate(){

        purchasetable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        purchasetable.getSelectionModel().selectAll();
        ObservableList i = purchasetable.getSelectionModel().getSelectedItems();

        ObservableList<Book> bookList= FXCollections.observableArrayList();

        int p=0;

        for(Object z: i){


            Book book = (Book) i.get(p);
            bookList.add(book);
            p++;
        }

        for(Book b :bookList){

            System.out.println(b.getBookname());
        }

    }

//
//     if(itemcode.getText().isEmpty() || itemcategory.getValue().equals("") || itemauthor.getValue().equals("")|| itemname.getText().isEmpty()){
//
//        JOptionPane.showMessageDialog(null,"Please Supplier fill required field");
//
//    }
//
//        else {
//
//
//
//
//
//        String authorcode = getAuthorCode(itemauthor.getValue());
//
//        String categorycode =getCategoryCode(itemcategory.getValue());
//
//        int qty;
//
//        if(itemqty.getText().equals("")){
//
//            qty = 0;
//        }
//        else
//        {
//            qty = Integer.parseInt(itemqty.getText());
//
//        }
//
//
//
//        int price ;
//
//        if(itemprice.getText().equals("")){
//
//            price = 0;
//        }
//        else
//        {
//            price = Integer.parseInt(itemprice.getText());
//        }
//
//
//
//        Book book1 =new Book(itemcode.getText(),itemname.getText(),qty,price, authorcode,categorycode);
//
//        bookdb.create(book1);
//
//        purchasetable.setItems( predataList);
//
//
//        itemname.setText("");
//        itemauthor.setValue("");
//        itemcategory.setValue("");
//        itemqty.setText("");
//        itemprice.setText("");
//        itemtotal.setText("");
//
//    }


}

