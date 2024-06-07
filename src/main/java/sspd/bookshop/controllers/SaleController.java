package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import sspd.bookshop.databases.Authordb;
import sspd.bookshop.databases.Categorydb;
import sspd.bookshop.databases.Supplierdb;
import sspd.bookshop.models.Author;
import sspd.bookshop.models.Category;
import sspd.bookshop.models.Supplier;

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

    @FXML
    private TableColumn<Category, String> caID;

    @FXML
    private TableColumn<Category, String> caName;

    @FXML
    private TextField caid;

    @FXML
    private TextField caname;

    @FXML
    private TextField categorySearch;

    @FXML
    private TableView  categorytable;

    @FXML
    private TableColumn<Supplier, String> suAddress;

    @FXML
    private TableColumn<Supplier, String> suID;

    @FXML
    private TableColumn<Supplier, String> suName;

    @FXML
    private TableColumn<Supplier, String> suPhone;

    @FXML
    private TextArea suaddress;

    @FXML
    private TextField suid;

    @FXML
    private TextField suname;

    @FXML
    private TextField suphone;
    @FXML
    private TableView  suppliertable;

    @FXML
    private TextField suSearch;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


       // ->>> Author Set <<<<<-

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


       // ->>> Author Set Close <<<<-

        // ->>> Category Set Open <<<<-

        caid.setText(getCategoryID());

        getFindLoadCategoryData();
        getIniCategoryTable();

        categorytable.setEditable(true);

        caName.setCellFactory(TextFieldTableCell.forTableColumn());

        caName.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if(null != value && !value.isEmpty()){

                event.getRowValue().setCategory_name(value);

                getcategoryRowUpdate();

            }

        });


        // ->>> Supplier Set Open <<<<-

        getIniSupplierTable();

        suid.setText(getSupplierID());

        getFindLoadSupplierData();

        suppliertable.setEditable(true);

        suName.setCellFactory(TextFieldTableCell.forTableColumn());

        suName.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if(null != value && !value.isEmpty()){

                event.getRowValue().setS_name(value);

                getSupplierRowUpdate();

            }

        });

        suPhone.setCellFactory(TextFieldTableCell.forTableColumn());

        suPhone.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if(null != value && !value.isEmpty()){

                event.getRowValue().setS_phone(value);

                getSupplierRowUpdate();

            }

        });

        suAddress.setCellFactory(TextFieldTableCell.forTableColumn());

        suAddress.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if(null != value && !value.isEmpty()){

                event.getRowValue().setS_address(value);

                getSupplierRowUpdate();

            }

        });





    }

    // ->>> Author Set <<<-

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

    // ->>> Author Set Close <<<-


    // ->>>> Category Set <<<<-

    @FXML
    void savenewCategory(MouseEvent event) {

        if(caname.getText().isEmpty() || caid.getText().isEmpty()){

            JOptionPane.showMessageDialog(null,"Please Category fill required field");

        }
        else {



            Categorydb categorydb = new Categorydb();

            Category category = new Category(caid.getText(),caname.getText());

            categorydb.create(category);

            getFindLoadCategoryData();

            caid.setText(getCategoryID());

            caname.setText("");
        }

    }

    private String getCategoryID(){

        String defaultid = "#ca1";

        Categorydb categorydb = new Categorydb();
        List<Category> categoryList = categorydb .getList();

        if(categoryList .size()==0){

            return defaultid;

        }
        else {

            return "#ca"+ Integer.toString(categoryList .size()+1);

        }



    }

    private void getFindLoadCategoryData() {

        ObservableList<Category> observableList = FXCollections.observableArrayList();

        Categorydb adb = new Categorydb();

        List<Category> categoryList = null;

        categoryList  = adb.getList();

        for(Category m :categoryList ){

            observableList.add(m);
        }


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Category> filteredData = new FilteredList<>(observableList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        categorySearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(filter -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filter.getCategory_id().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else if (filter.getCategory_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else if (filter.getCategory_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Category> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(categorytable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        categorytable.setItems(sortedData);
    }

    private void getIniCategoryTable(){


        caID.setCellValueFactory(new PropertyValueFactory<Category,String>("category_id"));
        caName.setCellValueFactory(new PropertyValueFactory<Category,String>("category_name"));


    }

    private void getcategoryRowUpdate(){

        Category category = (Category) categorytable.getSelectionModel().getSelectedItem();


        Category categoryupate = new Category(category.getCategory_id(),category.getCategory_name());

        Categorydb categorydb = new Categorydb();

        categorydb.update(categoryupate);

    }

    // ->>>> Category Set Close <<<<-

    @FXML
    void savenewSupplier(MouseEvent event) {

        if(suid.getText().isEmpty() || suname.getText().isEmpty() || suphone.getText().isEmpty() || suaddress.getText().isEmpty()){

            JOptionPane.showMessageDialog(null,"Please Supplier fill required field");

        }

        else {



           Supplierdb supplierdb = new Supplierdb();

            Supplier supplier =new Supplier(suid.getText(),suname.getText(),suphone.getText(),suaddress.getText());

            supplierdb.create(supplier);

            getFindLoadSupplierData();

            suid.setText(getSupplierID());

            suname.setText("");
            suphone.setText("");
            suaddress.setText("");
        }


    }

    private void getIniSupplierTable(){


        suID.setCellValueFactory(new PropertyValueFactory<Supplier,String>("s_id"));
        suName.setCellValueFactory(new PropertyValueFactory<Supplier,String>("s_name"));
        suPhone.setCellValueFactory(new PropertyValueFactory<Supplier,String>("s_phone"));
        suAddress.setCellValueFactory(new PropertyValueFactory<Supplier,String>("s_address"));


    }

    private String getSupplierID(){

        String defaultid = "#su1";

        Supplierdb supplierdb  = new Supplierdb();
        List<Supplier> supplierList = supplierdb .getList();

        if(supplierList  .size()==0){

            return defaultid;

        }
        else {

            return "#su"+ Integer.toString(supplierList  .size()+1);

        }



    }

    private void getFindLoadSupplierData() {

        ObservableList<Supplier> observableList = FXCollections.observableArrayList();

       Supplierdb supplierdb = new Supplierdb();

        List<Supplier> supplierList = null;

        supplierList  = supplierdb.getList();

        for(Supplier m :supplierList ){

            observableList.add(m);
        }


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Supplier> filteredData = new FilteredList<>(observableList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        suSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(filter -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filter.getS_id().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else if (filter.getS_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getS_phone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else if (filter.getS_address().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }


                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Supplier> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(suppliertable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        suppliertable.setItems(sortedData);
    }

    private void getSupplierRowUpdate(){

        Supplier supplier = (Supplier) suppliertable.getSelectionModel().getSelectedItem();


        Supplier supplierupate = new Supplier(supplier.getS_id(),supplier.getS_name(),supplier.getS_phone(),supplier.getS_address());

        Supplierdb supplierdb = new Supplierdb();

        supplierdb.update(supplierupate);

    }




}
