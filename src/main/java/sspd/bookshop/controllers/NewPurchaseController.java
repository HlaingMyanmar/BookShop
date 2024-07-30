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
import sspd.bookshop.modules.Deliver;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.net.URL;
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

        newItem.setOnAction(event -> {



            String itemcodee = stockidtxt.getText();
            String itemnamee =stocknametxt.getText();
            int  itemqtyy = Integer.parseInt(stockqtytxt.getText());
            int itempricee =Integer.parseInt(stockpricetxt.getText());
            String itemautho= stockauthorcobox.getValue();
            String itemca=stockcategorycobox.getValue();

            Book book = new Book(itemcodee,itemnamee,itemqtyy,itempricee,itemautho,itemca,itempricee*itemqtyy);


            boolean isDuplicate = predataList.stream()
                    .anyMatch(b -> b.getBookid().equals(stockidtxt.getText()));

            if (isDuplicate) {

                AlertBox.showWarning("‌ကျေးဇူးပြုပြန်လည်စစ်ဆေးပေးပါ။","သင့်၏ စာအုပ် အိုင်ဒီသည် ထပ်နေပါသည်။");

            } else {


                predataList.add(book);
                purchasetable.setItems(FXCollections.observableList(predataList));

                lbCount.setText(String.valueOf(predataList.size()));

                double total = 0.0;

                double sum= book.getTotal();
                 total +=sum;

                lbTotal.setText(convertToMyanmarCurrency(total));




            }












        });













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

        return  getID("#Pu", id);

    }

    private String getStockID(){

        Bookdb bookdb = new Bookdb();

        String id = (bookdb.getList().isEmpty())?null: (bookdb.getList().getFirst().getBookid());

        return getStockIDGenerate("#St",id);

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();

    }
}
