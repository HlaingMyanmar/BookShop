package sspd.bookshop.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.databases.PurchaseReturnDetaildb;
import sspd.bookshop.databases.PurchaseReturndb;
import sspd.bookshop.databases.Purchasedb;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Purchase;
import sspd.bookshop.models.PurchaseReturn;
import sspd.bookshop.models.PurchaseReturnDetail;
import sspd.bookshop.modules.Deliver;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        try{
            if(predataList.isEmpty()){


                String id  =  pid.getText();
                String code = (String) bookList.getValue();

                int qty = Integer.parseInt(rqty.getText());

                int amount = Integer.parseInt(ramount.getText());

                String remark = rreason.getText();

                Purchase p = new Purchase(id,code,qty,amount,remark);

                predataList.add(p);




                returntable.setItems(predataList);

                pid.setText("");
                bookList.setValue("");
                rqty.setText("");
                ramount.setText("");
                rreason.setText("");

            }

            else {
                JOptionPane.showMessageDialog(null, "Can't not Insert 2 Items","Notice Option",0);
            }





        }catch (NumberFormatException e){

        }










    }

    @FXML
    void addlistItem(KeyEvent event) {

    }

    @FXML
    void confirmItem(MouseEvent event) {



        Timestamp retrundate = Timestamp.valueOf(rdate.getText());

        if (rdate.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Please Insert Return Date");

        } else {

            int size = returntable.getItems().size();


            Purchase prd = (Purchase) returntable.getItems().getFirst();



            PurchaseReturnDetaildb db = new PurchaseReturnDetaildb();

            PurchaseReturn pr = new PurchaseReturn(prd.getPuid(),retrundate);

            db.create(pr);


            /// Purchase Return Detail Insert

            PurchaseReturndb rdb = new PurchaseReturndb();
            Timestamp date  = rdb.getList().getFirst().getRdate();



            PurchaseReturnDetail prd1 = new PurchaseReturnDetail(prd.getPuid(),date,getBookCode(prd.getBcode()),prd.getQty(),prd.getTotal(),prd.getRemark());

            db.insert(prd1);

            SimpleIntegerProperty qty = new SimpleIntegerProperty(prd.getQty());

            Book b = new Book(getBookCode(prd.getBcode()),qty);

            db.subBookQty(b);



        }

       Stage stage = (Stage) returntable.getScene().getWindow();
       stage.close();

    }

    @FXML
    void getTotal(KeyEvent event) {

        if(event.getCode()== KeyCode.ENTER){

           String bname = (String) bookList.getValue();

           int total = 0;

           int  qty = Integer.parseInt(rqty.getText());

            Purchasedb p = new Purchasedb();

            List<Purchase> pList = p.getList();

            for(Purchase pu : pList){

                if(pu.getBcode().equals(bname)){

                   total=  pu.getPrice()* qty;

                    ramount.setText(Integer.toString(total));

                }

            }


        }



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

        iniTable();

      //  rdid.setText(getPurchaseReturnID());

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        rdate.setText(String.valueOf(formattedNow));


    }

    private  void iniTable(){

        pidCol.setCellValueFactory(new PropertyValueFactory<>("puid"));
        bcodeCol.setCellValueFactory(new PropertyValueFactory<>("bcode"));
        qttCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        remarkCol.setCellValueFactory(new PropertyValueFactory<>("remark"));



    }
}
