package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sspd.bookshop.Alerts.AlertBox;
import sspd.bookshop.databases.Categorydb;
import sspd.bookshop.models.Category;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private TableView categorytable;

    @FXML
    private TableColumn<Category, String> codeCol;

    @FXML
    private TableColumn<Category, String> nameCol;

    @FXML
    private TextField code;

    @FXML
    private TextField name;

    @FXML
    private Button savebtn;

    Categorydb categorydb = new Categorydb();

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        ini();



        categorytable.setEditable(true);

        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {

            String value = String.valueOf(event.getNewValue());

          


        });


    }

    private void ini() {

        code.setText(getIDGenerate());



        tabelIni();
        getLoadData(categorytable);

        savebtn.setOnAction(_ -> {

            String cacode = code.getText();
            String caname = name.getText();

            Category category  = new Category(cacode,caname);

            if(categorydb.create(category)==1){


                AlertBox.showInformation("‌အုပ်စုအမျိုးအစား", "အသစ်ထည့်သွင်းခြင်းအောင်မြင်ပါသည်။");
                getLoadData(categorytable);
                code.setText(getIDGenerate());


            }


        });


    }

    private void getLoadData(TableView tableView){




        Categorydb categorydb = new Categorydb();
        List<Category>categoryList =  categorydb.getList();

        tableView.getItems().setAll(categoryList);

    }

    private void tabelIni(){


        codeCol.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("category_name"));

    }

    private String getIDGenerate(){


        String text = "#ca";

        Categorydb categorydb = new Categorydb();
        String lastcategory =  categorydb.getList().getFirst().getCategory_id();


        return text+(Integer.parseInt(lastcategory.substring(3))+1);

    }


}
