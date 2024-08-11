package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sspd.bookshop.Alerts.AlertBox;
import sspd.bookshop.databases.Orderdb;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Order;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static sspd.bookshop.controllers.StockController._bookid;

public class NewSalesController implements Initializable {

    @FXML
    private Button addbtn;

    @FXML
    private Label amountlb;

    @FXML
    private TextField aname;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TextField bcode;

    @FXML
    private TextField bname;

    @FXML
    private TableColumn<Book, String> bookcodeCol;

    @FXML
    private TableColumn<Book, String> booknameCol;

    @FXML
    private TextField caname;

    @FXML
    private TableColumn<Book, String> categoryCol;

    @FXML
    private TextField cname;

    @FXML
    private Button comfirmbtn;

    @FXML
    private TextField cphone;

    @FXML
    private TextField odate;

    @FXML
    private TextField oid;

    @FXML
    private TableView  otable;

    @FXML
    private Label pcslb;

    @FXML
    private TableColumn<Book, Double> priceCol;

    @FXML
    private Button printbtn;

    @FXML
    private TextField ptxt;

    @FXML
    private TableColumn<Book, Integer> qtyCol;

    @FXML
    private TextField qtytxt;

    @FXML
    private Button removebtn;

    @FXML
    private AnchorPane help;

    @FXML
    private TextField total;

    @FXML
    private TableColumn<Book, Double> totalCol;

    public static ObservableList<Book> _oList = FXCollections.observableArrayList();

    private void ini(){

        getordertableInit();
        oid.setText(getOrderID());
        odate.setText(String.valueOf(Date.valueOf(LocalDate.now())));

        addbtn.setOnAction(_ -> {

            if(bcode.getText().isEmpty() || bname.getText().isEmpty() || caname.getText().isEmpty() || aname.getText().isEmpty() || qtytxt.getText().isEmpty() || ptxt.getText().isEmpty() || this.total.getText().isEmpty()){


                AlertBox.showWarning("သတိထားရန်။","လိုအပ်သည့် အချက်အလက်များကို ဖြည့်သွင်းပါ။");

            }else {

                String bookcode = bcode.getText();
                String bookname = bname.getText();
                String category = caname.getText();
                String author = aname.getText();
                int quantity = Integer.parseInt(qtytxt.getText());
                int price    = Integer.parseInt(ptxt.getText());
                int total = quantity*price;

                Book book = new Book(bookcode,bookname,quantity,price,author,category,total);

            }








        });

        help.setOnKeyPressed(event -> {


            if(event.getCode()== KeyCode.F1){

                Stage stage = new Stage();

                FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/stock.fxml"));
                Scene scene = null;


                try {
                    scene = new Scene(fxmlLoader.load());

                } catch (IOException e) {

                    throw new RuntimeException(e);
                }
                stage.initStyle(StageStyle.UTILITY);
                stage.initModality(Modality.WINDOW_MODAL);
                Stage mainStage = (Stage) help.getScene().getWindow();

                stage.setTitle("ပစ္စည်းများ");
                stage.initOwner(mainStage);
                stage.setScene(scene);
                stage.show();




            }




        });

        bcode.setOnMouseClicked(_ -> {

            if(_bookid==null){

                AlertBox.showInformation("‌ရွေးချယ်ရန်။","ပစ္စည်းတစ်ခုခုရွေးချယ်ပါ။\n\nF1 Key နှိပ်ပြီး ပစ္စည်းရွေးချယ်ပါ။");


            }
            else {

                bcode.setText(_bookid.getBookid());
                bname.setText(_bookid.getBookname());
                caname.setText(_bookid.getCid());
                aname.setText(_bookid.getAid());
                qtytxt.setText(String.valueOf(_bookid.getQuantity()));
                ptxt.setText(String.valueOf(_bookid.getPrice()));
                total.setText(String.valueOf(_bookid.getTotal()));

                _bookid =null;

            }






        });

        qtytxt.setOnKeyReleased(_ -> {

            int qty ;
            double price ;

            if (qtytxt.getText().isEmpty() || ptxt.getText().isEmpty()){

                qty = 0;
                price = 0.0;

            }
            else {

                qty = Integer.parseInt(qtytxt.getText());
                price = Double.parseDouble(ptxt.getText());

            }

            double gtotal = qty*price;

            total.setText(String.valueOf(gtotal));

        });

        ptxt.setOnKeyReleased(_ -> {

            int qty ;
            double price ;

            if (qtytxt.getText().isEmpty() || ptxt.getText().isEmpty()){

                qty = 0;
                price = 0.0;

            }
            else {

                qty = Integer.parseInt(qtytxt.getText());
                price = Double.parseDouble(ptxt.getText());

            }

            double gtotal = qty*price;

            total.setText(String.valueOf(gtotal));

        });

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

            return "#Or"+ (orderList.size() + 1);

        }



    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();
        _bookid =null;

    }


}
