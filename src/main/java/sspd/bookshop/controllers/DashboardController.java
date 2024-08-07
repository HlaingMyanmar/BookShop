package sspd.bookshop.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import net.sf.jasperreports.engine.*;
import sspd.bookshop.Alerts.AlertBox;
import sspd.bookshop.databases.*;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.*;
import sspd.bookshop.modules.Deliver;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static sspd.bookshop.controllers.ApplicationViewController.cho;
import static sspd.bookshop.controllers.NewSaleController.oList;

public class DashboardController extends Deliver implements Initializable {


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
    private TableView categorytable;

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
    private TableView suppliertable;

    @FXML
    private TextField suSearch;


    @FXML
    private TableView booktable;


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
    private TableColumn<Book, Integer> btotalCol;

    @FXML
    private TextField searchBox;
    @FXML
    private TextField searchBox1;

    @FXML
    private JFXCheckBox purchaseCb;

    @FXML
    private JFXCheckBox purchasereturnCb;

    @FXML
    private Pane switchPane;

    @FXML
    private TableColumn<Purchase, String> pauthorCol;

    @FXML
    private TableColumn<Purchase, String> pcategoryCol;

    @FXML
    private TableColumn<Purchase, String> pcodeCol;

    @FXML
    private TableColumn<Purchase, Date> pdateCol;

    @FXML
    private TableColumn<Purchase, String> pnameCol;

    @FXML
    private TableColumn<Purchase, Integer> ppriceCol;

    @FXML
    private TableColumn<Purchase, Integer> pqtyCol;

    @FXML
    private TableColumn<Purchase, String> psupplierCol;

    @FXML
    private TableColumn<Purchase, Integer> ptotalCol;

    @FXML
    private TableColumn<Purchase, String>editCol;

    @FXML
    private TableView purchasetable;

    @FXML
    private TextField psearch;

    @FXML
    private TextField psearch1;
    

    @FXML
    private Label totalPrice;

    @FXML
    private Label totalQty;

    @FXML
    private AnchorPane purchasePane;

    public static int checkPoint = 0;


    @FXML
    private Tab mPane;


    @FXML
    private Tab oPane;

    @FXML
    private Tab pPane;

    @FXML
    private TabPane tabPane;


    @FXML
    private Label grandTotaltxt;

    @FXML
    private Label totalQtytxt;

    @FXML
    private JFXButton emptybtn;

    @FXML
    private JFXButton returnbtn;

    @FXML
    private Button purchaseclearBtn;

    public static String _pid =null;






    @FXML
    void bootableClickeAction(MouseEvent event) {


        // Set Total Qty and Amount

        Book book =  (Book) booktable.getSelectionModel().getSelectedItem();


        grandTotaltxt.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+book.getTotal()+" )ကျပ် ရှိပါသည်။");

        totalQtytxt.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+book.getQuantity()+" )ခုရှိပါသည်။");



    }


    private int getItemSize(){

        Bookdb bookdb  = new Bookdb();

        List<Book> bookList =bookdb.getList();

        return bookList.size();

    }



    private double getItemTotal(){

        Bookdb bookdb  = new Bookdb();

        List<Book> bookList =bookdb.getList();

        return bookList.stream()
                .mapToDouble(Book::getTotal)
                .sum();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        totalQtytxt.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+getItemSize()+" )ခုရှိပါသည်။");


        grandTotaltxt.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+getItemTotal()+" )ကျပ် ရှိပါသည်။");



        if (cho == "P") {


            tabPane.getTabs().remove(mPane);
            tabPane.getTabs().remove(oPane);

        }

        if (cho == "S") {

            tabPane.getTabs().remove(pPane);
            tabPane.getTabs().remove(oPane);

        }

        if (cho == "Si") {


            tabPane.getTabs().remove(pPane);
            tabPane.getTabs().remove(mPane);
        }

        booktable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        purchasetable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        auid.setText(getAuthorID());

        getIniAuthorTable();
        getFindLoadAuthorData();

        authortable.setEditable(true);

        auName.setCellFactory(TextFieldTableCell.forTableColumn());

        auName.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if (null != value && !value.isEmpty()) {

                event.getRowValue().setAuthor_name(value);

                getauthorRowUpdate();
                getUpdateData();

            }

        });

        caid.setText(getCategoryID());

        getFindLoadCategoryData();
        getIniCategoryTable();

        categorytable.setEditable(true);

        caName.setCellFactory(TextFieldTableCell.forTableColumn());

        caName.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if (null != value && !value.isEmpty()) {

                event.getRowValue().setCategory_name(value);

                getcategoryRowUpdate();

            }

        });

        getIniSupplierTable();

        suid.setText(getSupplierID());

        getFindLoadSupplierData();

        suppliertable.setEditable(true);

        suName.setCellFactory(TextFieldTableCell.forTableColumn());

        suName.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if (null != value && !value.isEmpty()) {

                event.getRowValue().setS_name(value);

                getSupplierRowUpdate();

            }

        });

        suPhone.setCellFactory(TextFieldTableCell.forTableColumn());

        suPhone.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if (null != value && !value.isEmpty()) {

                event.getRowValue().setS_phone(value);

                getSupplierRowUpdate();

            }

        });

        suAddress.setCellFactory(TextFieldTableCell.forTableColumn());

        suAddress.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if (null != value && !value.isEmpty()) {

                event.getRowValue().setS_address(value);

                getSupplierRowUpdate();

            }

        });


        getIniBookTable();

        getFindLoadBookData();

        booktable.setEditable(true);

        bnameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        bnameCol.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if (null != value && !value.isEmpty()) {

                event.getRowValue().setBookname(value);

                getBookRowUpdate();

            }

        });

        bcategoryCol.setCellFactory(TextFieldTableCell.forTableColumn());

        bcategoryCol.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if (null != value && !value.isEmpty()) {

                event.getRowValue().setCid(value);

                getBookRowUpdate();

            }

        });

        bauthorCol.setCellFactory(TextFieldTableCell.forTableColumn());

        bauthorCol.setOnEditCommit(event -> {

            String value = event.getNewValue();

            if (null != value && !value.isEmpty()) {

                event.getRowValue().setAid(value);

                getBookRowUpdate();

            }

        });


        if (searchBox.getText().equals("")) {
            searchBox1.setEditable(false);
        }


        getIniPurchaseTable();

        getFindLoadPurchaseIniData();

       // getFindLoadPurchaseData();


    }

    // ->>> Author Set <<<-

    @FXML
    void savenewAuthor(MouseEvent event) {


        if (auname.getText().isEmpty() || auid.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Please fill required field");

        } else {


            Authordb authordb = new Authordb();

            Author author = new Author(auid.getText(), auname.getText());

            authordb.create(author);

            getFindLoadAuthorData();

            auid.setText(getAuthorID());

            auname.setText("");

            getUpdateData();

        }

    }


    private void getauthorRowUpdate() {

        Author author = (Author) authortable.getSelectionModel().getSelectedItem();


        Author authorupate = new Author(author.getAuthor_id(), author.getAuthor_name());

        Authordb authordb = new Authordb();

        authordb.update(authorupate);

        getUpdateData();

    }

    private void getIniAuthorTable() {


        auID.setCellValueFactory(new PropertyValueFactory<>("author_id"));
        auName.setCellValueFactory(new PropertyValueFactory<>("author_name"));


    }

    private String getAuthorID() {

        String defaultid = "#au1";

        Authordb authordb = new Authordb();
        List<Author> authorList = authordb.getList();

        if (authorList.size() == 0) {

            return defaultid;

        } else {


            return "#au" + Integer.toString(authorList.size() + 1);

        }


    }

    private void getFindLoadAuthorData() {

        ObservableList<Author> observableList = FXCollections.observableArrayList();

        Authordb adb = new Authordb();

        List<Author> authorList = null;

        authorList = adb.getList();

        for (Author m : authorList) {

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

                if (filter.getAuthor_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (filter.getAuthor_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getAuthor_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else
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

        getUpdateData();
    }

    // ->>> Author Set Close <<<-


    // ->>>> Category Set <<<<-

    @FXML
    void savenewCategory(MouseEvent event) {

        if (caname.getText().isEmpty() || caid.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Please Category fill required field");

        } else {


            Categorydb categorydb = new Categorydb();

            Category category = new Category(caid.getText(), caname.getText());

            categorydb.create(category);

            getFindLoadCategoryData();

            caid.setText(getCategoryID());

            caname.setText("");

            getUpdateData();
        }

    }

    private String getCategoryID() {

        String defaultid = "#ca1";

        Categorydb categorydb = new Categorydb();
        List<Category> categoryList = categorydb.getList();

        if (categoryList.size() == 0) {

            return defaultid;

        } else {

            return "#ca" + Integer.toString(categoryList.size() + 1);

        }


    }

    private void getFindLoadCategoryData() {

        ObservableList<Category> observableList = FXCollections.observableArrayList();

        Categorydb adb = new Categorydb();

        List<Category> categoryList = null;

        categoryList = adb.getList();

        for (Category m : categoryList) {

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

                if (filter.getCategory_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (filter.getCategory_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getCategory_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else
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

        getUpdateData();
    }

    private void getIniCategoryTable() {


        caID.setCellValueFactory(new PropertyValueFactory<Category, String>("category_id"));
        caName.setCellValueFactory(new PropertyValueFactory<Category, String>("category_name"));


    }

    private void getcategoryRowUpdate() {

        Category category = (Category) categorytable.getSelectionModel().getSelectedItem();


        Category categoryupate = new Category(category.getCategory_id(), category.getCategory_name());

        Categorydb categorydb = new Categorydb();

        categorydb.update(categoryupate);

        getUpdateData();


    }

    // ->>>> Category Set Close <<<<-


    // ->>>> Supplier Set Open <<<<<

    @FXML
    void savenewSupplier(MouseEvent event) {

        if (suid.getText().isEmpty() || suname.getText().isEmpty() || suphone.getText().isEmpty() || suaddress.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Please Supplier fill required field");

        } else {


            Supplierdb supplierdb = new Supplierdb();

            Supplier supplier = new Supplier(suid.getText(), suname.getText(), suphone.getText(), suaddress.getText());

            supplierdb.create(supplier);

            getFindLoadSupplierData();

            suid.setText(getSupplierID());

            suname.setText("");
            suphone.setText("");
            suaddress.setText("");

            getUpdateData();
        }


    }

    private void getIniSupplierTable() {


        suID.setCellValueFactory(new PropertyValueFactory<Supplier, String>("s_id"));
        suName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("s_name"));
        suPhone.setCellValueFactory(new PropertyValueFactory<Supplier, String>("s_phone"));
        suAddress.setCellValueFactory(new PropertyValueFactory<Supplier, String>("s_address"));


    }

    private String getSupplierID() {

        String defaultid = "#su1";

        Supplierdb supplierdb = new Supplierdb();
        List<Supplier> supplierList = supplierdb.getList();

        if (supplierList.size() == 0) {

            return defaultid;

        } else {

            return "#su" + Integer.toString(supplierList.size() + 1);

        }


    }

    private void getFindLoadSupplierData() {

        ObservableList<Supplier> observableList = FXCollections.observableArrayList();

        Supplierdb supplierdb = new Supplierdb();

        List<Supplier> supplierList = null;

        supplierList = supplierdb.getList();

        for (Supplier m : supplierList) {

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

                if (filter.getS_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (filter.getS_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getS_phone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getS_address().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else
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

        getUpdateData();
    }

    private void getSupplierRowUpdate() {

        Supplier supplier = (Supplier) suppliertable.getSelectionModel().getSelectedItem();


        Supplier supplierupate = new Supplier(supplier.getS_id(), supplier.getS_name(), supplier.getS_phone(), supplier.getS_address());

        Supplierdb supplierdb = new Supplierdb();

        supplierdb.update(supplierupate);
        getUpdateData();

    }


    // ->>>> Supplier Set Close <<<<<


    // ->>>> Book Set Open <<<<<

    private String getBookID() {

        String defaultid = "#bo1";

        Bookdb bookdb = new Bookdb();
        List<Book> bookList = bookdb.getList();

        if (bookList.size() == 0) {

            return defaultid;

        } else {

            return "#bo" + Integer.toString(bookList.size() + 1);

        }


    }

    private void getIniBookTable() {

        bcodeCol.setCellValueFactory(new PropertyValueFactory<Book, String>("bookid"));
        bnameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("bookname"));
        bcategoryCol.setCellValueFactory(new PropertyValueFactory<Book, String>("cid"));
        bauthorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("aid"));
        bqtyCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("quantity"));
        bpriceCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("price"));
        btotalCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("total"));


    }

    public void getFindLoadBookData() {



        ObservableList<Book> observableList = FXCollections.observableArrayList();

        Bookdb bookdb = new Bookdb();

        List<Book> bookList = null;

        bookList = bookdb.getList();

        for (Book m : bookList) {

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

                if (filter.getBookid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (filter.getBookname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getAid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getCid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Book> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(booktable.comparatorProperty());



        searchBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {



                totalQtytxt.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+sortedData.size()+" )ခုရှိပါသည်။");


                double sum = filteredData.stream()
                        .mapToDouble(Book::getTotal)
                        .sum();

                grandTotaltxt.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+sum+" )ကျပ် ရှိပါသည်။");


            }
        });


        // 5. Add sorted (and filtered) data to the table.
        booktable.setItems(sortedData);


        int size = 0;


        for(Book b: sortedData){

            if(b.getQuantity()==0){



                size++;

            }
        }

        emptybtn.setText("ကုန်နေ‌သောပစ္စည်း( " +size+ " )ခုရှိပါသည်။");

        emptybtn.setOnAction(event -> {


            ObservableList<Book> books = FXCollections.observableArrayList();

            for(Book b: sortedData){

                if(b.getQuantity()==0){

                    books.add(b);
                }



            }

            booktable.setItems(books);


        });
















    }


    private void getBookRowUpdate() {


        Book book = (Book) booktable.getSelectionModel().getSelectedItem();

        int i = booktable.getSelectionModel().getSelectedIndex();

        Book book1 = new Book(book.getBookid(), book.getBookname(), book.getQuantity(), book.getPrice(), getAuthorCode(book.getAid()), getCategoryCode(book.getCid()));

        Bookdb bookdb = new Bookdb();

        bookdb.update(book1);
        getUpdateData();

    }


    private void setFilter() {


        ObservableList i = booktable.getSelectionModel().getSelectedItems();

        ObservableList<Book> updateList = FXCollections.observableArrayList();

        int p = 0;

        for (Object z : i) {

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
                } else if (filter.getBookname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filter.getAid().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (filter.getCid().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Book> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(booktable.comparatorProperty());




        searchBox1.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {


                totalQtytxt.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+sortedData.size()+" )ခုရှိပါသည်။");

                double sum = filteredData.stream()
                        .mapToDouble(Book::getTotal)
                        .sum();



                grandTotaltxt.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+sum+" )ကျပ် ရှိပါသည်။");


            }
        });
        // 5. Add sorted (and filtered) data to the table.
        booktable.setItems(sortedData);









        //System.out.println(book.getBookname());
    }



    @FXML
    void noticeAction(MouseEvent event) {


        if (searchBox.getText().equals("")) {

            searchBox1.setEditable(false);


            AlertBox.showWarning("သတိထားပါ။ ","ကျေးဇူးပြု၍ ရှာ‌ဖွေခြင်း (၁)တွင် ပထမဦးဆုံးရှာ‌ဖွေပါ။");


            searchBox.setStyle("-fx-border-color:red;");


            searchBox.setPromptText("ဤနေရာတွင် အရင်ရှာဖွေပါ။");


        } else {

            searchBox.setStyle("");


            searchBox1.setEditable(true);

            booktable.getSelectionModel().selectAll();


            setFilter();


        }


    }

    @FXML
    void bookrefreshAction(MouseEvent event) {

        getFindLoadBookData();
        searchBox.setText("");
        searchBox.setPromptText("Start Search . ..................................");
        searchBox1.setText("");


        grandTotaltxt.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+getItemTotal()+" )ကျပ် ရှိပါသည်။");

        totalQtytxt.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+getItemSize()+" )ခုရှိပါသည်။");








    }


    @FXML
    public void printsaveNewBook(MouseEvent mouseEvent) {
    }


    private void getUpdateData() {

        getFindLoadBookData();


    }


    @FXML
    void newPurchareAction(MouseEvent event) {


        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/purchase.fxml"));
        Scene scene = null;


        try {


            scene = new Scene(fxmlLoader.load());


        } catch (IOException e) {

            throw new RuntimeException(e);

        }
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage mainStage = (Stage) purchasetable.getScene().getWindow();
        stage.setTitle("New Purchase");
        stage.initOwner(mainStage);
        stage.setScene(scene);
        stage.show();


    }

    private void getIniPurchaseTable() {

        pcodeCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("puid"));
        pdateCol.setCellValueFactory(new PropertyValueFactory<Purchase, Date>("pudate"));
        pnameCol.setCellValueFactory(new PropertyValueFactory<>("bcode"));
        pcategoryCol.setCellValueFactory(new PropertyValueFactory<>("cid"));
        pauthorCol.setCellValueFactory(new PropertyValueFactory<>("aid"));
        psupplierCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        pqtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ppriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        ptotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        editCol.setCellValueFactory(new PropertyValueFactory<>(""));


    }

    public void getFindLoadPurchaseIniData(){

        Purchasedb purchasedb = new Purchasedb();

        List<Purchase> purchaseList = purchasedb.getList2();



        ObservableList<Purchase> purchaseObservableList = FXCollections.observableArrayList();

        purchaseObservableList.addAll(purchaseList);

        purchasetable.setItems(purchaseObservableList);

        totalPrice.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+ getSumPurchase(purchasedb.getList())+" )ကျပ် ရှိပါသည်။");
        totalQty.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+purchaseObservableList.size()+" )ခုရှိပါသည်။");

        PurchaseReturndb prdb = new PurchaseReturndb();

        returnbtn.setText("ပြန်လည်ပို့ဆောင်ထားသော ပစ္စည်း ("+prdb.getList().size()+")ခုရှိပါသည်");


        returnbtn.setOnAction(event -> {

            Purchasedb pd = new Purchasedb();
            List<Purchase> pdbList = pd.getList2();

            List<PurchaseReturn> prdbList = prdb.getList();

            ObservableList<Purchase> updateList = FXCollections.observableArrayList(
                     pdbList.stream()
                    .filter(p -> prdbList.stream()
                    .anyMatch(pr -> pr.getPuid().equals(p.getPuid())))
                    .collect(Collectors.toList())
            );

            purchasetable.setItems(updateList);

            totalPrice.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+ getSumPurchase(updateList)+" )ကျပ် ရှိပါသည်။");
            totalQty.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+updateList.size()+" )ခုရှိပါသည်။");

            returnbtn.setText("ပြန်လည်ပို့ဆောင်ထားသော ပစ္စည်း (" + prdbList.size() + ")ခုရှိပါသည်");


        });

        purchaseclearBtn.setOnAction(event1 -> {

            psearch.setText("");
            psearch1.setText("");

            getFindLoadPurchaseIniData();

        });

        tableCellsetIcon();

    }

    public void getFindLoadPurchaseData() {

        ObservableList<Purchase> observableList = FXCollections.observableArrayList();

        Purchasedb purchasedb = new Purchasedb();

        List<Purchase> purchaseList = null;

        purchaseList = purchasedb.getList();

        for (Purchase m : purchaseList) {

            observableList.add(m);
        }


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Purchase> filteredData = new FilteredList<>(observableList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        psearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(filter -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {


                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filter.getPuid().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true; // Filter matches first name.
                } else if (filter.getPudate().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getBcode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getAid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getSid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getCid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else{



                    return false;
                }


                  // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Purchase> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(suppliertable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.


        purchaseclearBtn.setOnAction(event -> {

            psearch.setText("");
            psearch1.setText("");

            getFindLoadPurchaseIniData();





        });






        psearch.setOnKeyPressed(event -> {


            if (event.getCode() ==KeyCode.ENTER){

                String id = psearch.getText();

                if(id.contains("#")){

                    ObservableList<Purchase> upList = FXCollections.observableArrayList();

                    Purchasedb pdb = new Purchasedb();

                    List<Purchase> pList = pdb.getList();

                    upList.addAll(pList.stream()
                            .filter(purchase -> purchase.getPuid().equals(id))
                            .toList());

                    purchasetable.setItems(upList);

                    totalPrice.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+ getSumPurchase(upList)+" )ကျပ် ရှိပါသည်။");

                    totalQty.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+upList.size()+" )ခုရှိပါသည်။");



                }
                else {

                    totalPrice.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+ getSumPurchase(sortedData)+" )ကျပ် ရှိပါသည်။");
                    totalQty.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+sortedData.size()+" )ခုရှိပါသည်။");

                }





            }


        });





        purchasetable.setItems(sortedData);



        totalQty.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+sortedData.size()+" )ခုရှိပါသည်။");

        totalPrice.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+ getSumPurchase(sortedData)+" )ကျပ် ရှိပါသည်။");







    }



    private void getAutoRunning() {

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                getFindLoadPurchaseData();


            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    @FXML
    void purchasetableClickAction(MouseEvent event) {

        if (event.getClickCount() == 1 && checkPoint == 1) {

            getFindLoadPurchaseData();

            checkPoint = 2;

        } else if (event.getClickCount() == 2) {

            ObservableList<Purchase> updateList = FXCollections.observableArrayList();

            Purchasedb pdb = new Purchasedb();

            List<Purchase> pList = pdb.getList();

            Purchase purchase = (Purchase) purchasetable.getSelectionModel().getSelectedItem();


            for (Purchase p : pList) {

                if (p.getPuid().equals(purchase.getPuid())) {

                    updateList.add(p);
                }

            }

            totalPrice.setText("တန်ဖိုးစုစု‌ပေါင်း ( " + getSumPurchase(updateList) + " )ကျပ် ရှိပါသည်။");
            totalQty.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( " + updateList.size() + " )ခုရှိပါသည်။");


            purchasetable.setItems(updateList);


            //


            Purchasedb pd = new Purchasedb();
            PurchaseReturndb prdb = new PurchaseReturndb();
            List<Purchase> pdbList = pd.getList2();

            List<PurchaseReturn> prdbList = prdb.getList();

            ObservableList<Purchase> updateList2 = FXCollections.observableArrayList(
                    pdbList.stream()
                            .filter(p -> prdbList.stream()
                            .anyMatch(pr -> pr.getPuid().equals(p.getPuid())))
                            .collect(Collectors.toList())


            );

            for(Purchase p :updateList2){

                if(p.getPuid().equals(purchase.getPuid())){

                    _pid =p.getPuid();


                    purchasePane.setVisible(false);

                    purchaseCb.setSelected(false);

                    purchasereturnCb.setSelected(true);


                    FXMLLoader fxmlLoader2 = new FXMLLoader(Bookshop.class.getResource("/layout/purchasereturnreport.fxml"));
                    Node node2 = null;

                    try {

                        node2 = fxmlLoader2.load();
                        switchPane.getChildren().add(node2);

                    } catch (IOException e) {

                        throw new RuntimeException(e);
                    }


                } else {
                    purchaseCb.setSelected(true);
                }


            }

            purchaseclearBtn.setOnAction(event1 -> {

                psearch.setText("");
                psearch1.setText("");

                getFindLoadPurchaseIniData();

            });


        }
    }

    @FXML
    void searchPurchareBookAction(MouseEvent event) {




        getFindLoadPurchaseData();
        getFindLoadBookData();


    }

    @FXML
    void searchpurchareAction(MouseEvent event) {

        if (psearch.getText().equals("")) {

            psearch1.setEditable(false);


            JOptionPane.showMessageDialog(null, "Please First Start , start filter box", "Notice", 0);

            psearch.setStyle("-fx-border-color:red;");


            psearch.setPromptText("Please Fill Filter check!!!");


        } else {

            psearch.setStyle("");


            psearch1.setEditable(true);

            purchasetable.getSelectionModel().selectAll();


            setPurchaseFilter();


        }

    }

    private void tableCellsetIcon(){


        Callback<TableColumn<Purchase,String>,TableCell<Purchase,String>> cellFactory = (param) -> {


            final TableCell<Purchase,String> cell = new TableCell<Purchase,String>(){


                public void updateItem(String item,boolean empty){

                    super.updateItem(item,empty);


                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }

                    else {
                        final Button editButton = new Button("+");

                        editButton.setOnAction(event -> {


                            System.out.println("Teting");


                        });

                        setGraphic(editButton);
                        setText(null);
                    }


                }

            };




            return cell;



        };



        editCol.setCellFactory(cellFactory);

    }

    private void setPurchaseFilter() {

        ObservableList i = purchasetable.getSelectionModel().getSelectedItems();

        ObservableList<Purchase> updateList = FXCollections.observableArrayList();

        int p = 0;

        for (Object z : i) {

            Purchase purchase = (Purchase) i.get(p);
            updateList.add(purchase);
            p++;
        }

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Purchase> filteredData = new FilteredList<>(updateList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.

        psearch1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(filter -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filter.getPuid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (filter.getPudate().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getBcode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getAid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getSid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (filter.getCid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Purchase> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(booktable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        psearch1.setOnKeyPressed(event -> {


            if (event.getCode() ==KeyCode.ENTER){

                String id = psearch.getText();

                if(id.contains("#")){

                    ObservableList<Purchase> upList = FXCollections.observableArrayList();

                    Purchasedb pdb = new Purchasedb();

                    List<Purchase> pList = pdb.getList();

                    upList.addAll(pList.stream()
                            .filter(purchase -> purchase.getPuid().equals(id))
                            .toList());

                    purchasetable.setItems(upList);

                    totalPrice.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+ getSumPurchase(pList)+" )ကျပ် ရှိပါသည်။");

                    totalQty.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+pList.size()+" )ခုရှိပါသည်။");


                }
                else {

                    totalPrice.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+ getSumPurchase(sortedData)+" )ကျပ် ရှိပါသည်။");

                    totalQty.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+sortedData.size()+" )ခုရှိပါသည်။");

                }





            }


        });





        purchasetable.setItems(sortedData);

        totalPrice.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+ getSumPurchase(sortedData)+" )ကျပ် ရှိပါသည်။");
        totalQty.setText("ပစ္စည်းအရေအတွက် စုစုပေါင်း ( "+sortedData.size()+" )ခုရှိပါသည်။");
      //  getTotalSelectList(sortedData, totalQty, totalPrice);
    }


    @FXML
    void getPurchaseSelectPrint(MouseEvent event) throws FileNotFoundException, JRException {


    }




    @FXML
    void getpurchaseboxOnefindAction(KeyEvent event) {


//       if (event.getCode() ==KeyCode.ENTER){
//
//           String id = psearch.getText();
//
//           if(id.contains("#")){
//
//               ObservableList<Purchase> upList = FXCollections.observableArrayList();
//
//               Purchasedb pdb = new Purchasedb();
//
//               List<Purchase> pList = pdb.getList();
//
//               upList.addAll(pList.stream()
//                       .filter(purchase -> purchase.getPuid().equals(id))
//                       .toList());
//
//               purchasetable.setItems(upList);
//
//
//           }
//           else {
//
//               totalPrice.setText("တန်ဖိုးစုစု‌ပေါင်း ( "+ getSumPurchase(sortedData)+" )ကျပ် ရှိပါသည်။");
//
//           }
//
//
//
//
//
//       }



    }

    private double getSumPurchase( List<Purchase> p ){


        double sum = 0.0;

       for(Purchase p1 :p){

           sum +=p1.getTotal();

       }

       return sum;

    }





    private void getTotalSelectList(ObservableList<Purchase> observableList, Label qtyLabel, Label priceLabel) {

        qtyLabel.setText("Item : " + observableList.size());

        int total = 0;

        for (Purchase p : observableList) {

            total += p.getTotal();

        }

        priceLabel.setText("Total Amount : " + total + " MMK");


    }

    @FXML
    void purchasecheckBoxAction(MouseEvent event) {


        if (purchaseCb.isSelected()) {

            switchPane.getChildren().clear();

            purchasereturnCb.setSelected(false);
            purchasePane.setVisible(true);
//.
        } else {
            purchasereturnCb.setSelected(true);
        }

    }


    @FXML
    void purchasereturncheckBoxAction(MouseEvent event) {


        if (purchasereturnCb.isSelected()) {

            purchasePane.setVisible(false);


            purchaseCb.setSelected(false);


            FXMLLoader fxmlLoader2 = new FXMLLoader(Bookshop.class.getResource("/layout/purchasereturnreport.fxml"));
            Node node2 = null;

            try {

                node2 = fxmlLoader2.load();
                switchPane.getChildren().add(node2);

            } catch (IOException e) {

                throw new RuntimeException(e);
            }


        } else {
            purchaseCb.setSelected(true);
        }

    }

    @FXML
    public void newSaleAction(MouseEvent event) throws IOException {

        Stage stage = new Stage();


        oList.clear();

        FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/newSale.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage mainStage = (Stage) booktable.getScene().getWindow();
        stage.setTitle("New Sale");
        stage.initOwner(mainStage);
        stage.setScene(scene);
        stage.show();


    }


    //OrderTab controller


}
