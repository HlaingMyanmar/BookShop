package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import sspd.bookshop.Alerts.AlertBox;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.databases.Purchasedb;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Purchase;
import sspd.bookshop.modules.Deliver;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

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


    ObservableList<Book> predataList = FXCollections.observableArrayList();






    public void ini(){

        getCategoryName(stockcategorycobox);

        getAuthorName(stockauthorcobox);



         getIniPurchaseTable();
         purchaseidtxt.setText(getPurchaseID());
         stockidtxt.setText(getStockID());
         datetxt.setText(String.valueOf(LocalDate.now()));

         //Set Supplier Name
          getSupplierName(suppliername,supplierid);


         // Set Main Pane

        mainPane.setOnKeyPressed(event -> {


            if(event.getCode()== KeyCode.F1){



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

                predataList.add(book);
                purchasetable.setItems(predataList);
                getBookClear();
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

        String id= (purchasedb.getList().isEmpty())?null: (purchasedb.getList().getFirst().getPuid());


        return  getID("#P", id);

    }

    private String getStockID(){

        Bookdb bookdb = new Bookdb();

        String id = (bookdb.getList().isEmpty())?null: (bookdb.getList2().getFirst().getBookid());

        System.out.println(id);

        return getStockIDGenerate("#S",id);

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();

    }
}
