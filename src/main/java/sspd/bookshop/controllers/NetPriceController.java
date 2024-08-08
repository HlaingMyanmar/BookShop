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
    private Button submitbtn;

    @FXML
    private TextField trantxt;

    private void ini(){

        ObservableList<String> currencyList = FXCollections.observableArrayList();

        currencyList.add("MMK (Kyat)");
        currencyList.add("Dollor ($)");
        currencycombo.setItems(currencyList);


        if(currencycombo.getValue().equals("MMK (Kyat)")){
            dollortxt.setText();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();

    }
}
