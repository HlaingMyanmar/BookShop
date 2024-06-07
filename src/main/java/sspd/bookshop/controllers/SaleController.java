package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sspd.bookshop.DAO.Authordb;
import sspd.bookshop.models.Author;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SaleController  implements Initializable {


    @FXML
    private TableColumn<Author, String> auID;

    @FXML
    private TableColumn<Author, String> auName;

    @FXML
    private TextField auid;
    @FXML
    private TextField auname;


    @FXML
    private TableView authortable;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       auid.setText(getAuthorID());

       getIniAuthorTable();
       getLoadAuthorData();

    }

    @FXML
    void savenewAuthor(MouseEvent event) {


        if(auname.getText().isEmpty() || auid.getText().isEmpty()){

            JOptionPane.showMessageDialog(null,"Please fill required field");

        }
        else {



        Authordb authordb = new Authordb();

        Author author = new Author(auid.getText(),auname.getText());

        authordb.create(author);

        getLoadAuthorData();
        auid.setText(getAuthorID());

        auname.setText("");
        }

    }


    private void getLoadAuthorData(){

        Authordb authordb = new Authordb();
        List<Author> authorList = authordb.getList();

        authortable.getItems().setAll(authorList);



    }

    private void getIniAuthorTable(){


        auID.setCellValueFactory(new PropertyValueFactory<Author,String>("author_id"));
        auName.setCellValueFactory(new PropertyValueFactory<Author,String>("author_name"));


    }


    private String getAuthorID(){

        String defaultid = "#au1";

        Authordb authordb = new Authordb();
        List<Author> authorList = authordb.getList();

            if(authorList.size()==0){

                return defaultid;

            }
            else {

                return "#au"+ Integer.toString(authorList.size()+1);

            }



    }
}
