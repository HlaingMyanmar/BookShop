package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sspd.bookshop.databases.Orderdb;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Order;
import sspd.bookshop.models.Sale;
import sspd.bookshop.modules.Deliver;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static sspd.bookshop.modules.Deliver._book;

public class NewSaleController extends Deliver implements Initializable {

    @FXML
    private TextField aname;

    @FXML
    private Label amountlb;

    @FXML
    private Label pcslb;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TextField bcode;

    @FXML
    private TextField bname;

    @FXML
    private TableColumn<Book, String> bookcodeCol;

    @FXML
    private TableColumn<Sale, String> booknameCol;

    @FXML
    private TextField caname;

    @FXML
    private TableColumn<Book, String> categoryCol;

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
    private TableColumn<Book, Integer> priceCol;

    @FXML
    private TextField ptxt;

    @FXML
    private TableColumn<Book, Integer> qtyCol;

    @FXML
    private TextField qtytxt;

    @FXML
    private TextField total;

    @FXML
    private TableColumn<Book, Integer> totalCol;

    public static ObservableList<Book> oList = FXCollections.observableArrayList();


    @FXML
    void addItem(MouseEvent event) {

    boolean bo = false;



    String bookcode = bcode.getText();
    String bookname = bname.getText();
    String category = caname.getText();
    String author = aname.getText();
    int quantity = Integer.parseInt(qtytxt.getText());
    int price    = Integer.parseInt(ptxt.getText());
    int total = quantity*price;


    Book book = new Book(bookcode,bookname,quantity,price,author,category,total);


    if(bcode.getText().equals("") || bname.getText().equals("") || caname.getText().equals("") || aname.getText().equals("") || qtytxt.getText().equals("") || ptxt.getText().equals("") || this.total.getText().equals("")){


        JOptionPane.showMessageDialog(null,"Please Fill required data?","Notice",0);


    } else if (book.getQuantity() > getDataList(bookcode).getQuantity()) {

        JOptionPane.showMessageDialog(null,"This is not Have!!!"+"\nThis is item have "+getDataList(bookcode).getQuantity()+" pcs","Notice",0);
        qtytxt.setText(String.valueOf(getDataList(bookcode).getQuantity()));

    }


    else {



    for(Book b :oList){

        if( b.getBookid().equals(bookcode)){

            bo = true;

        }

    }


    if (!bo){




        oList.add(book);

        otable.setItems(oList);
        getQtyCalulate();
        getTotalCalulate();

        getClear();
    }

    else {

        JOptionPane.showMessageDialog(null,"This data is duplicate !!!!","Notice",2);
        getClear();

    }
    }




    }

    private void getClear(){

        bcode.setText("");
        bname.setText("");
        caname.setText("");
        aname.setText("");
        qtytxt.setText("");
        this.total.setText("");
        ptxt.setText("");
        _book = null;
    }

    @FXML
    void addlistItem(KeyEvent event) {

    }

    @FXML
    void comfirm(MouseEvent event) {

    }

    @FXML
    void print(MouseEvent event) {

    }

    @FXML
    void helpAction(KeyEvent event) {

        if(event.getCode()== KeyCode.F1){


            getChildbookTable();



        }
    }

    @FXML
    void purchaseCodeKeyAction(KeyEvent event) {

        if (event.getCode()== KeyCode.ENTER){


            int i = (Integer.parseInt(qtytxt.getText())) * (Integer.parseInt(ptxt.getText()));

            total.setText(String.valueOf(i));
        }




    }

    @FXML
    void getbook(KeyEvent event) {

        if(event.getCode()== KeyCode.ENTER){

            Book book =  getDataList(bcode.getText());

            bcode.setText(book.getBookid());
            bname.setText(book.getBookname());
            caname.setText(book.getCid());
            aname.setText(book.getAid());
            ptxt.setText(String.valueOf(book.getPrice()));


        }

    }

    @FXML
    void remove(MouseEvent event) {
        int selectedIndex = otable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {

            otable.getItems().remove(selectedIndex);
            getQtyCalulate();
            getTotalCalulate();

            _book = null;

        } else {

            JOptionPane.showMessageDialog(null,"No Item Select","Notice",0);
            _book = null;
        }


    }

    @FXML
    void getDataAction(MouseEvent event) {

        try {

            bcode.setText(_book.getBookid());
            bname.setText(_book.getBookname());
            caname.setText(_book.getCid());
            aname.setText(_book.getAid());
            ptxt.setText(String.valueOf(_book.getPrice()));

        }catch (NullPointerException ex){

            JOptionPane.showMessageDialog(null,"Please Find Item 'F1' Click ","Notice",1);

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getordertableInit();

        oid.setText(getOrderID());

        odate.setText(String.valueOf(Date.valueOf(LocalDate.now())));



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

    private String getOrderID(){

        String defaultid = "#Or1";

        Orderdb orderdb = new Orderdb();
        List<Order> orderList = orderdb.getList();

        if(orderList .size()==0){

            return defaultid;

        }
        else {

            return "#bo"+ Integer.toString(orderList .size()+1);

        }



    }

    private void getChildbookTable(){

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
        Stage mainStage = (Stage) otable.getScene().getWindow();
        stage.initOwner(mainStage);
        stage.setScene(scene);
        stage.show();
    }

    private void getQtyCalulate(){

        int totalqty = 0;

       otable. getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

       otable.getSelectionModel().selectAll();

       ObservableList ob =  otable.getSelectionModel().getSelectedItems();

       pcslb.setText(ob.size() +" pcs");




    }

    private void getTotalCalulate(){

        int total = 0;

        otable. getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        otable.getSelectionModel().selectAll();

        ObservableList ob =  otable.getSelectionModel().getSelectedItems();

        int p = 0;

        for(Object b :ob){

             total  = total+((Book)ob.get(p)).getTotal();
             p++;
        }

        amountlb.setText(total +" MMK");




    }



}
