package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.models.Book;
import sspd.bookshop.modules.Deliver;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookSearchController extends Deliver implements Initializable {

    @FXML
    private TableColumn<Book, String> bauthorCol;

    @FXML
    private TableColumn<Book, String> bcategoryCol;

    @FXML
    private TableColumn<Book, String> bcodeCol;

    @FXML
    private TableColumn<Book, String> bnameCol;

    @FXML
    private TableColumn<Book, Integer> bpriceCol;

    @FXML
    private TableColumn<Book, Integer> bqtyCol;

    @FXML
    private TextField searchBox;
    @FXML
    private TextField searchBox1;

    @FXML
    private TableView booktable;

    @FXML
    private ImageView filter1;
    @FXML
    private ImageView filter2;







    private void getIniBookTable(){

        bcodeCol.setCellValueFactory(new PropertyValueFactory<Book,String>("bookid"));
        bnameCol.setCellValueFactory(new PropertyValueFactory<Book,String>("bookname"));
        bcategoryCol.setCellValueFactory(new PropertyValueFactory<Book,String>("cid"));
        bauthorCol.setCellValueFactory(new PropertyValueFactory<Book,String>("aid"));
        bqtyCol.setCellValueFactory(new PropertyValueFactory<Book,Integer>("quantity"));
        bpriceCol.setCellValueFactory(new PropertyValueFactory<Book,Integer>("price"));



    }

    private void getFindLoadBookData() {



        ObservableList<Book> observableList = FXCollections.observableArrayList();

        Bookdb bookdb = new Bookdb();

        List<Book> bookList= null;

        bookList = bookdb.getList();

        for(Book m :bookList ){

            observableList.add(m);
        }


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Book> filteredData = new FilteredList<>(observableList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(filter -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filter.getBookid().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else if (filter.getBookname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getAid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else if (filter.getCid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }


                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Book> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(booktable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        booktable.setItems(sortedData);


    }


    private void setFilter(){

        ObservableList i = booktable.getSelectionModel().getSelectedItems();

        ObservableList<Book> updateList= FXCollections.observableArrayList();

        int p=0;

        for(Object z: i){

            Book book = (Book) i.get(p);
            updateList.add(book);
            p++;
        }

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Book> filteredData = new FilteredList<>(updateList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.

        searchBox1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(filter -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filter.getBookid().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                else if (filter.getBookname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (filter.getAid().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                else if (filter.getCid().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }


                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Book> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(booktable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        booktable.setItems(sortedData);

        //System.out.println(book.getBookname());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        booktable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        getIniBookTable();
        getFindLoadBookData();


    }
    @FXML
    void noticeAction(MouseEvent event) {




        if(searchBox.getText().equals("")){

            searchBox1.setEditable(false);


            JOptionPane.showMessageDialog(null,"Please First Start , start filter box","Notice",0);

            searchBox.setStyle("-fx-border-color:red;");



            searchBox.setPromptText("Please Fill Filter check!!!");


        }
        else {

            searchBox.setStyle("");


            searchBox1.setEditable(true);




            setFilter();


        }





    }

    @FXML
    void bookrefreshAction(MouseEvent event) {

        getFindLoadBookData();
        searchBox.setText("");
        searchBox.setPromptText("Start Search . ..................................");
        searchBox1.setText("");



    }

    @FXML
    void searchtableClick(MouseEvent event) {

        if (event.getClickCount() == 2) {

            Book book =  (Book) booktable.getSelectionModel().getSelectedItem();



                _book  =  (Book) booktable.getSelectionModel().getSelectedItem();
                Stage mainStage = (Stage) booktable.getScene().getWindow();

                mainStage.close();

            }






        }
    }


