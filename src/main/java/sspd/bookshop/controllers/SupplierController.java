package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sspd.bookshop.Alerts.AlertBox;
import sspd.bookshop.databases.Supplierdb;
import sspd.bookshop.models.Supplier;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierController  implements Initializable {

    @FXML
    private TextArea address;

    @FXML
    private TableColumn<Supplier, String> addressCol;

    @FXML
    private TableColumn<Supplier, String> codeCol;

    @FXML
    private TableColumn<Supplier, String> nameCol;

    @FXML
    private TableColumn<Supplier, String> phoneCol;

    @FXML
    private TextField code;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private Button savebtn;

    @FXML
    private Label sizetxt;

    @FXML
    private TableView suppliertable;


    Supplierdb supplierdb = new Supplierdb();

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        tabelIni();

        ini();


       suppliertable.setEditable(true);

        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {

            String value = String.valueOf(event.getNewValue());

            if(null!=value && !value.isEmpty()){

                event.getRowValue().setS_name(value);
                Supplier supplier = (Supplier) suppliertable.getSelectionModel().getSelectedItem();


                if(supplierdb.update(supplier)==1){


                    AlertBox.showInformation("‌ထောက်ပံ့သူ", "ပြင်ဆင်ခြင်းအောင်မြင်ပါသည်။");
                    getLoadData(suppliertable);

                }



            }

        });

        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setOnEditCommit(event -> {

            String value = String.valueOf(event.getNewValue());

            if(null!=value && !value.isEmpty()){


                Supplier supplier = (Supplier) suppliertable.getSelectionModel().getSelectedItem();
                event.getRowValue().setS_phone(value);

                if(supplierdb.update(supplier)==1){


                    AlertBox.showInformation("‌ထောက်ပံ့သူ", "ပြင်ဆင်ခြင်းအောင်မြင်ပါသည်။");
                    getLoadData(suppliertable);

                }



            }

        });

        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(event -> {

            String value = String.valueOf(event.getNewValue());

            if(null!=value && !value.isEmpty()){

                event.getRowValue().setS_address(value);
                Supplier supplier = (Supplier) suppliertable.getSelectionModel().getSelectedItem();


                if(supplierdb.update(supplier)==1){


                    AlertBox.showInformation("‌ထောက်ပံ့သူ", "ပြင်ဆင်ခြင်းအောင်မြင်ပါသည်။");
                    getLoadData(suppliertable);

                }



            }

        });


    }



    private void ini() {


         getLoadData(suppliertable);
        code.setText(getIDGenerate());




        savebtn.setOnAction(_ -> {

            String scode = code.getText();
            String sname = name.getText();
            String sphone =phone.getText();
            String saddress = address.getText();

            Supplier supplier  = new Supplier(scode,sname,sphone,saddress);

            if(supplierdb.create(supplier)==1){


                AlertBox.showInformation("‌ထောက်ပံ့သူ", "အသစ်ထည့်သွင်းခြင်းအောင်မြင်ပါသည်။");
                getLoadData(suppliertable);
                code.setText(getIDGenerate());


            }


        });



    }

    private void getLoadData(TableView tableView){


        Supplierdb supplierdb = new Supplierdb();
        List<Supplier> supplierList =  supplierdb.getList();

        tableView.getItems().setAll(supplierList);

        sizetxt.setText(String.valueOf(supplierList.size()));

    }

    private void tabelIni(){


        codeCol.setCellValueFactory(new PropertyValueFactory<>("s_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("s_name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("s_phone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("s_address"));

    }

    private String getIDGenerate(){


        String text = "#su";

        Supplierdb supplierdb = new Supplierdb();
        String lastsupplier =  supplierdb.getList().getFirst().getS_id();


        return text+(Integer.parseInt(lastsupplier.substring(3))+1);

    }

}
