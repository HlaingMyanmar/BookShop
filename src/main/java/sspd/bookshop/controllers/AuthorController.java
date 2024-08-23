package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sspd.bookshop.Alerts.AlertBox;
import sspd.bookshop.databases.Authordb;
import sspd.bookshop.models.Author;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AuthorController  implements Initializable {

    @FXML
    private TableView  authortable;

    @FXML
    private TextField code;

    @FXML
    private TableColumn<Author, String> codeCol;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Author, String> nameCol;

    @FXML
    private Button savebtn;

    @FXML
    private Label sizetxt;

    Authordb authordb = new Authordb();

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        ini();





        authortable.setEditable(true);

        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {

            String value = String.valueOf(event.getNewValue());

            if(null!=value && !value.isEmpty()){

                event.getRowValue().setAuthor_name(value);
                Author author = (Author) authortable.getSelectionModel().getSelectedItem();


                if(authordb.update(author)==1){


                    AlertBox.showInformation("‌ထုတ်လုပ်သူ", "ပြင်ဆင်ခြင်းအောင်မြင်ပါသည်။");
                    getLoadData(authortable);

                }



            }

        });


    }

    private void ini() {

        code.setText(getIDGenerate());



        tabelIni();
        getLoadData(authortable);

        savebtn.setOnAction(_ -> {

            String cacode = code.getText();
            String caname = name.getText();

           Author author  = new Author(cacode,caname);

            if(authordb.create(author)==1){


                AlertBox.showInformation("‌ထုတ်လုပ်သူ", "အသစ်ထည့်သွင်းခြင်းအောင်မြင်ပါသည်။");
                getLoadData(authortable);
                code.setText(getIDGenerate());

                name.setText("");


            }


        });




    }

    private void getLoadData(TableView tableView){


        Authordb authordb = new Authordb();
        List<Author> authorList =  authordb.getList();

        tableView.getItems().setAll(authorList);

        sizetxt.setText(String.valueOf(authorList.size()));

    }

    private void tabelIni(){


        codeCol.setCellValueFactory(new PropertyValueFactory<>("author_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("author_name"));

    }

    private String getIDGenerate(){


        String text = "#au";

        Authordb authordb = new Authordb();
        String lastauthor =  authordb.getList().getFirst().getAuthor_id();


        return text+(Integer.parseInt(lastauthor.substring(3))+1);

    }
}
