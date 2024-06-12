package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.databases.PurchaseReturnDetaildb;
import sspd.bookshop.databases.Purchasedb;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Purchase;
import sspd.bookshop.models.PurchaseReturnDetail;
import sspd.bookshop.modules.Deliver;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static sspd.bookshop.controllers.PurchaseSearchController.purchaseID;

public class PurchasereturnController extends Deliver implements Initializable {

    @FXML
    private TableColumn<PurchaseReturnDetail, Integer> amountCol;

    @FXML
    private TableColumn<PurchaseReturnDetail, String> bcodeCol;



    @FXML
    private TableColumn<PurchaseReturnDetail, String> pidCol;

    @FXML
    private TableColumn<PurchaseReturnDetail, Integer> qttCol;

    @FXML
    private TextField ramount;

    @FXML
    private TextField rdate;

    @FXML
    private TableColumn<PurchaseReturnDetail, Date> rdateCol;

    @FXML
    private TextField rdid;

    @FXML
    private TextField pid;

    @FXML
    private TableColumn<PurchaseReturnDetail, String> remarkCol;

    @FXML
    private TableView returntable;

    @FXML
    private TableColumn<PurchaseReturnDetail, String> ridCol;

    @FXML
    private TextField rqty;

    @FXML
    private TextArea rreason;

    @FXML
    private ComboBox bookList;

    ObservableList<Purchase> predataList = FXCollections.observableArrayList();

    @FXML
    void addItem(MouseEvent event) {


       String id  =  pid.getText();
       String code = (String) bookList.getValue();

       int qty = Integer.parseInt(rqty.getText());

       int amount = Integer.parseInt(ramount.getText());

       String remark = rreason.getText();

       Purchase p = new Purchase(id,code,qty,amount,remark);

       predataList.add(p);

       returntable.setItems(predataList);







    }

    @FXML
    void addlistItem(KeyEvent event) {

    }

    @FXML
    void confirmItem(MouseEvent event) {

    }

    @FXML
    void getTotal(KeyEvent event) {

    }

    @FXML
    void helpAction(KeyEvent event) {

    }
    @FXML
    void getPurchaseID(MouseEvent event) {

        pid.setText(purchaseID);
    }


    @FXML
    void getBookNameAction(MouseEvent event) {

        Purchasedb p = new Purchasedb();

        List<Purchase> pList = p.getFindId(pid.getText());

        List<String> updateList = new ArrayList<>();


        for(Purchase pu : pList){



           // Purchase pur = new Purchase(pu.getPrice(),getBookName(pu.getPuid()));
            updateList.add(getBookName(pu.getBcode()));


        }


        bookList.getItems().setAll(updateList);



    }

    @FXML
    void purchaseCodeKeyAction(KeyEvent event) throws IOException {




        Stage stage = new Stage();

        if(event.getCode()== KeyCode.F1){

            FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/purchasesearch.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Purchase Search");
            Stage mainStage = (Stage) returntable.getScene().getWindow();
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);
            stage.setScene(scene);
            stage.show();





        }

    }

    @FXML
    void removeAction(MouseEvent event) {

    }

    private String getPurchaseReturnID(){

        String defaultid = "#ret1";

        PurchaseReturnDetaildb prddb = new PurchaseReturnDetaildb();

        List<PurchaseReturnDetail> prddList  = prddb.getdetailList();

        if(prddList .size()==0){

            return defaultid;

        }
        else {

            return "#ret"+ Integer.toString(prddList .size()+1);

        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rdid.setText(getPurchaseReturnID());

        rdate.setText(String.valueOf(LocalDate.now()));


    }
}
