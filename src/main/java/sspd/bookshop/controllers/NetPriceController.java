package sspd.bookshop.controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sspd.bookshop.models.NetPurchaseprice;

import java.net.URL;
import java.util.ResourceBundle;

import static sspd.bookshop.controllers.NewPurchaseController.netPriceList;


public class NetPriceController implements Initializable {

    @FXML
    private TextField amounttxt;

    @FXML
    private JFXComboBox<String> currencycombo;

    @FXML
    private TextField dollortxt;

    @FXML
    private TextField finalpricetxt;

    @FXML
    private TextField otherexpentxt;

    @FXML
    private TextField pertxt;

    @FXML
    private TextField qtytxt;



    @FXML
    private TextField trantxt;

    @FXML
    private Button calculatebtn;


    public  static  int _transportation= 0;
    public static  int  _otherexpense = 0;
    public static int _qty = 0 ;
    public static double _usdamount = 0;
    public static int _amount = 0;
    public static  String _usdname = null;
    public static  double _percent = 0;
    public static  double _netresult  = 0;


    private void ini(){

        ObservableList<String> currencyList = FXCollections.observableArrayList();

        currencyList.add("MMK (Kyat)");
        currencyList.add("Dollor ($)");
        currencycombo.setItems(currencyList);

        currencycombo.setOnAction(_ -> {

            if(currencycombo.getValue().equals("MMK (Kyat)")){
            dollortxt.setEditable(false);
            dollortxt.setText("1");


        }
            else {
                dollortxt.setEditable(true);
                dollortxt.setText("0");
            }

        });


        calculatebtn.setOnAction(event -> {

            double tran = Double.parseDouble(trantxt.getText());
            double otherexpense = Double.parseDouble(otherexpentxt.getText());
            int qty = Integer.parseInt(qtytxt.getText());
            double dollor = Double.parseDouble(dollortxt.getText());
            double amount = Double.parseDouble(amounttxt.getText());



            int percentage = Integer.parseInt(pertxt.getText());


            double finalresult = ((tran+otherexpense)/qty)+(dollor*amount);

            int netresult = (int) ((finalresult*((double) percentage /100))+finalresult);

            finalpricetxt.setText(String.valueOf(netresult));

           _transportation= (int) tran;
           _otherexpense = (int) otherexpense;
           _qty = qty ;
           _usdamount =  dollor;
           _usdname = currencycombo.getValue();
           _amount = (int) amount;
           _percent = percentage;
           _netresult  = (int) netresult;




        });



//


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();

    }
}
