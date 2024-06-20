package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import sspd.bookshop.databases.Orderdb;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Order;
import sspd.bookshop.models.Sale;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static sspd.bookshop.modules.Deliver._book;

public class NewSaleController implements Initializable {

    @FXML
    private TextField aname;

    @FXML
    private TableColumn<Sale, String> authorCol;

    @FXML
    private TextField bcode;

    @FXML
    private TextField bname;

    @FXML
    private TableColumn<Sale, String> bookcodeCol;

    @FXML
    private TableColumn<Sale, String> booknameCol;

    @FXML
    private TextField caname;

    @FXML
    private TableColumn<Sale, String> categoryCol;

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
    private TableColumn<Sale, Integer> priceCol;

    @FXML
    private TextField ptxt;

    @FXML
    private TableColumn<Sale, Integer> qtyCol;

    @FXML
    private TextField qtytxt;

    @FXML
    private TextField total;

    @FXML
    private TableColumn<Sale, Integer> totalCol;

    ObservableList<Book> oList = FXCollections.observableArrayList();

    @FXML
    void addItem(MouseEvent event) {

      Date date = Date.valueOf(odate.getText());

    String bookcode = bcode.getText();
    String bookname = bname.getText();
    String category = caname.getText();
    String author = aname.getText();
    int quantity = Integer.parseInt(qtytxt.getText());
    int price    = Integer.parseInt(ptxt.getText());

   // Sale sale = new Sale(oid.getText(),date,cname.getText(),cphone.getText(),bookcode,bookname,category,author,quantity,price,(quantity*price));

   // oList.add(sale);





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
    void remove(MouseEvent event) {

    }

    @FXML
    void getDataAction(MouseEvent event) {

        bcode.setText(_book.getBookid());
        bname.setText(_book.getBookname());
        caname.setText(_book.getCid());
        aname.setText(_book.getAid());
        ptxt.setText(String.valueOf(_book.getPrice()));


    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getordertableInit();

        oid.setText(getOrderID());

        odate.setText(String.valueOf(Date.valueOf(LocalDate.now())));



    }

    private void getordertableInit(){

        bookcodeCol.setCellValueFactory(new PropertyValueFactory<>("bcode"));
        booknameCol.setCellValueFactory(new PropertyValueFactory<>("bname"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("ccode"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("acode"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
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



}
