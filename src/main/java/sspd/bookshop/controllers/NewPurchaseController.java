package sspd.bookshop.controllers;

import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sspd.bookshop.Alerts.AlertBox;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.databases.NetPurchasePricedb;
import sspd.bookshop.databases.Purchasedb;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.NetPurchaseprice;
import sspd.bookshop.models.Purchase;
import sspd.bookshop.modules.Deliver;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static sspd.bookshop.controllers.NetPriceController.*;
import static sspd.bookshop.controllers.NewSaleController.oList;
import static sspd.bookshop.controllers.StockController._bookid;
import static sspd.bookshop.modules.Currency.convertToMyanmarCurrency;
import static sspd.bookshop.modules.IDGenerate.getID;
import static sspd.bookshop.modules.IDGenerate.getStockIDGenerate;

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
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, String> categroyCol;

    @FXML
    private TableColumn<Book, Double> totalCol;

    @FXML
    private TableView  purchasetable;

    @FXML
    private TableColumn<Book, Integer> qtyCol;


    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button newItem;

    @FXML
    private Button addItem;

    @FXML
    private Button removeItem;

    @FXML
    private Button getpricebtn;


    @FXML
    private AnchorPane switchPane;

    @FXML
    private JFXCheckBox itemnew;

    @FXML
    private JFXCheckBox itemold;



    ObservableList<Book> predataList = FXCollections.observableArrayList();

    Bookdb bookdb = new Bookdb();

    private String itemid_ = null;

    static  List<NetPriceController> netPriceList = new LinkedList<>();









    public void ini(){

        _bookid =null;

        getpricebtn.setOnAction(_ -> {


            Stage stage = new Stage();


            oList.clear();

            FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/netprice.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            Stage mainStage = (Stage) getpricebtn.getScene().getWindow();
            stage.setTitle("get Price");
            stage.initOwner(mainStage);
            stage.setScene(scene);
            stage.show();



        });

        itemnew.setOnAction(_ -> {

            if(itemnew.isSelected()){

                itemold.setSelected(false);
                stockidtxt.setText(getStockID());

            }


        });

        itemold.setOnAction(_ -> {

            if(itemold.isSelected()){
                itemnew.setSelected(false);
            }

        });




        getCategoryName(stockcategorycobox);

        getAuthorName(stockauthorcobox);


        //stockidtxt.setText(_bookid);



         getIniPurchaseTable();
         purchaseidtxt.setText(getPurchaseID());
         datetxt.setText(String.valueOf(LocalDate.now()));

         //Set Supplier Name
          getSupplierName(suppliername,supplierid);


         // Set Main Pane

        mainPane.setOnKeyPressed(event -> {


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
                Stage mainStage = (Stage) mainPane.getScene().getWindow();

                stage.setTitle("ပစ္စည်းများ");
                stage.initOwner(mainStage);
                stage.setScene(scene);
                stage.show();



            }

        });

        stockidtxt.setOnMouseClicked(event -> {

            if(_bookid==null){

                AlertBox.showInformation("‌ရွေးချယ်ရန်။","ပစ္စည်းတစ်ခုခုရွေးချယ်ပါ။");


            }

            else {

                stockidtxt.setText(_bookid.getBookid());
                stocknametxt.setText(_bookid.getBookname());
                stockcategorycobox.setValue(_bookid.getCid());
                stockauthorcobox.setValue(_bookid.getAid());
                stockqtytxt.setText(String.valueOf(_bookid.getQuantity()));
                stockpricetxt.setText(String.valueOf(_bookid.getPrice()));
                stocktotaltxt.setText(String.valueOf(_bookid.getQuantity()*_bookid.getPrice()));

                _bookid =null;

            }



        });

        stockqtytxt.setOnKeyReleased(event -> {

            int qty ;
            double price ;

            if (stockqtytxt.getText().isEmpty() || stockpricetxt.getText().isEmpty()){

                    qty = 0;
                    price = 0.0;

            }
            else {

                qty = Integer.parseInt(stockqtytxt.getText());
                price = Double.parseDouble(stockpricetxt.getText());

            }

            double total = qty*price;

            stocktotaltxt.setText(convertToMyanmarCurrency(total));


        });

        stockpricetxt.setOnKeyReleased(event -> {


            int qty ;
            double price ;

            if (stockqtytxt.getText().isEmpty() || stockpricetxt.getText().isEmpty()){

                qty = 0;
                price = 0.0;

            }
            else {

                qty = Integer.parseInt(stockqtytxt.getText());
                price = Double.parseDouble(stockpricetxt.getText());

            }

            double total = qty*price;

            stocktotaltxt.setText(convertToMyanmarCurrency(total));


        });

        addItem.setOnAction(event -> {

            String itemcodee = stockidtxt.getText();
            String itemnamee =stocknametxt.getText();
            int  itemqtyy = Integer.parseInt(stockqtytxt.getText());
            int itempricee =Integer.parseInt(stockpricetxt.getText());
            String itemautho= stockauthorcobox.getValue();
            String itemca=stockcategorycobox.getValue();

            Book book = new Book(itemcodee,itemnamee,itemqtyy,itempricee,itemautho,itemca,itempricee*itemqtyy);

            boolean isDuplicate = predataList.stream()
                    .anyMatch(b -> b.getBookid().equals(stockidtxt.getText()));

            if(isDuplicate){

                AlertBox.showError("သတိထားစရာ","သင့်၏ ပစ္စည်း အိုင်ဒီ ထပ်နေပါသည်။ကျေးဇူးပြု၍ပြန်လည်စစ်ဆေးပါ။");

            }

            else {


                if(itemnew.isSelected()){


                        Book b = new Book(itemcodee,itemnamee,0,0,getAuthorCode(itemautho),getCategoryCode(itemca),itempricee*itemqtyy);
                        bookdb.create(b);
                        itemid_ =  itemcodee;
                        stockidtxt.setText(getStockID());







                }


                predataList.add(book);
                purchasetable.setItems(predataList);
                getBookClear();
                lbCount.setText(String.valueOf(predataList.size()));

                double sum = predataList.stream()
                        .mapToDouble(Book::getTotal)
                        .sum();
                lbTotal.setText(convertToMyanmarCurrency(sum));

                String purchaseId = purchaseidtxt.getText();

                Date purchasedate = Date.valueOf(datetxt.getText());

                System.out.println(book.getBookid());

                NetPurchaseprice netPurchaseprice = new NetPurchaseprice(purchaseId,purchasedate,book.getBookid(),_usdname,_usdamount, _amount, _transportation, _otherexpense,  _qty, _percent,  _netresult);
                NetPurchasePricedb netPurchasePricedb = new NetPurchasePricedb();
                netPurchasePricedb.create(netPurchaseprice);


            }


        });

        removeItem.setOnAction(event -> {


            int selectedIndex = purchasetable.getSelectionModel().getSelectedIndex();


            if (selectedIndex >= 0) {

                purchasetable.getItems().remove(selectedIndex);

            } else {

                AlertBox.showWarning("သတိထားစရာ","သင့်သည် ပစ္စည်းအား မှတ်သားထားခြင်းမရှိပါ။");
            }



        });

        newItem.setOnAction(event -> {

            String purchaseId = purchaseidtxt.getText();

            String remark = stockremarktxt.getText();

            Date purchasedate = Date.valueOf(datetxt.getText());

            String supplierID = supplierid.getText();

            if(purchaseidtxt.getText().isEmpty() || datetxt.getText().isEmpty() || supplierid.getText().isEmpty()){

                AlertBox.showError("သတိထားစရာ","ကျေးဇူးပြု၍ အဝယ်ကုဒ်, ရက်စွဲ , ရောင်းသူ အိုင်ဒီ များကိုပြန်လည်စစ်ဆေးပေးပါ။");

            }
            else {

                int result = 0;


                int size = purchasetable.getItems().size();

                Bookdb bookdb = new Bookdb();
                Purchasedb purchasedb =new Purchasedb();

                for(int i = 0;i<size;i++){

                    Book book = (Book)  purchasetable.getItems().get(i);

                    boolean b = bookdb.getList().stream()
                            .anyMatch(bb -> book.getBookid().equals(bb.getBookid()));


                    if(b){

                        purchasedb.sumBookQty(book);

                    }
                    else {

                        Book newBook = new Book(book.getBookid(),book.getBookname(),book.getQuantity(),book.getPrice(),getAuthorCode(book.getAid()),getCategoryCode(book.getCid()));

                        bookdb.create(newBook);

                    }

                    Purchase p = new Purchase(purchaseId,purchasedate,book.getBookid(),getCategoryCode(book.getCid()),getAuthorCode(book.getAid()),supplierID,book.getQuantity(),book.getPrice(),remark );
                    result = purchasedb.create(p);

                }

                if(result==1){

                    AlertBox.showInformation("အသစ်ထည်သွင်းခြင်း","ပစ္စည်းဝယ်ခြင်း အချက်အလက်ထည့်သွင်းခြင်း အောင်မြင်ပါသည်။");

                    Stage stage = (Stage) purchasetable.getScene().getWindow();
                    stage.close();
                }



            }




        });













    }

    private  void getBookClear(){

        stockidtxt.setText("");
        stocknametxt.setText("");
        stockqtytxt.setText("");
        stockpricetxt.setText("");
        stockauthorcobox.setValue("");
        stockcategorycobox.setValue("");
        stocktotaltxt.setText("");


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
    private String getPurchaseID(){

        Purchasedb purchasedb = new Purchasedb();

        String id= (purchasedb.getList().isEmpty())?null: (purchasedb.getListGenerateID().getLast().getPuid());

        return  getID("#P", id);

    }

    private String getStockID(){

        Bookdb bookdb = new Bookdb();

        String id = (bookdb.getList2().isEmpty())?null: (bookdb.getListgenerateID().getLast().getBookid());


        return getStockIDGenerate("#S",id);

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();






    }
}
