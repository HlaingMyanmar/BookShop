package sspd.bookshop.controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


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
            int dollor = Integer.parseInt(dollortxt.getText());
            double amount = Double.parseDouble(amounttxt.getText());

            int percentage = Integer.parseInt(pertxt.getText());


            double finalresult = ((tran+otherexpense)/qty)+(dollor*amount);

            double netresult = (finalresult*((double) percentage /100))+finalresult;

            finalpricetxt.setText(String.valueOf(netresult));




        });



//


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();

    }
}
