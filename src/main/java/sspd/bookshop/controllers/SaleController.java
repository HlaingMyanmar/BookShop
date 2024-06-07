package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
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
    private TextField authorSearch;

    @FXML
    private TableView authortable;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // Author
       auid.setText(getAuthorID());

       getIniAuthorTable();
       getFindLoadAuthorData();

       authortable.setEditable(true);

       auName.setCellFactory(TextFieldTableCell.forTableColumn());

       auName.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if(null != value && !value.isEmpty()){

                event.getRowValue().setAuthor_name(value);

                getauthorRowUpdate();

            }

        });


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

    private void getauthorRowUpdate(){

        Author author = (Author ) authortable.getSelectionModel().getSelectedItem();


        Author authorupate = new  Author(author.getAuthor_id(),author.getAuthor_name());

        Authordb authordb = new Authordb();

        authordb.update(authorupate);

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

    private void getFindLoadAuthorData() {

        ObservableList<Author> observableList = FXCollections.observableArrayList();

        Authordb adb = new Authordb();

        List<Author> authorList = null;

        authorList = adb.getList();

        for(Author m :authorList){

            observableList.add(m);
        }


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Author> filteredData = new FilteredList<>(observableList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        authorSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(filter -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filter.getAuthor_id().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else if (filter.getAuthor_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else if (filter.getAuthor_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Author> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(authortable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        authortable.setItems(sortedData);
    }
}
