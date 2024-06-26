package sspd.bookshop.controllers;
import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sspd.bookshop.databases.Orderdb;
import sspd.bookshop.databases.Saledb;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Order;
import sspd.bookshop.modules.Deliver;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import static sspd.bookshop.controllers.NewSaleController.oList;

public class SaleDashboardController extends Deliver implements Initializable {

    @FXML
    private TableColumn<Order, String> cuCol;

    @FXML
    private TableColumn<Order, String> cuphoneCol;

    @FXML
    private TableColumn<Order, Date> oDateCol;

    @FXML
    private TableColumn<Order, String> oidCol;

    @FXML
    private TableColumn<Order, Integer> totalCol;

    @FXML
    private TableView ordertable;

    @FXML
    private AnchorPane purchasePane1;

    @FXML
    private TextArea reportArea;

    @FXML
    private JFXCheckBox saleCheck;

    @FXML
    private JFXCheckBox salereturnCheck;

    @FXML
    private TextField ssearch;

    @FXML
    private TextField ssearch1;


    static Order _ordered = null;



    @FXML
    void getQtyPrice(KeyEvent event) {

    }

    @FXML
    void getSalePrint(MouseEvent event) {

    }

    @FXML
    void newSaleAction(MouseEvent event) throws IOException {

        Stage stage = new Stage();


        oList.clear();

        FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/newSale.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage mainStage = (Stage) reportArea.getScene().getWindow();
        stage.setTitle("New Sale");
        stage.initOwner(mainStage);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void purchasecheckBoxAction(MouseEvent event) {

    }

    @FXML
    void purchasereturncheckBoxAction(MouseEvent event) {

    }

    @FXML
    void searchPurchareBookAction(MouseEvent event) {

    }

    @FXML
    void searchpurchareAction(MouseEvent event) {

        if(ssearch.getText().equals("")){

            ssearch1.setEditable(false);


            JOptionPane.showMessageDialog(null,"Please First Start , start filter box","Notice",0);

            ssearch.setStyle("-fx-border-color:red;");



            ssearch.setPromptText("Please Fill Filter check!!!");


        }
        else {

            ssearch.setStyle("");


            ssearch1.setEditable(true);

            ordertable.getSelectionModel().selectAll();


            setFilter();


        }




    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getorderTableIni();

        getFindLoadBookData();

        ordertable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);




    }


    @FXML
    void selectItemAction(MouseEvent event) throws IOException {


        if(event.getClickCount()==1){

            Order order = (Order) ordertable.getSelectionModel().getSelectedItem();


            Saledb saledb = new Saledb();

            List<Book> bookList =  saledb.findByOrderID(order.getOrderid());

            StringBuilder listContent = new StringBuilder();

            listContent.append("\t\t\t\t\t Item List\n\n");

            int i = 1;

            double grand = 0.0;

            for (Book b : bookList) {

                String bookname = getBookName(b.getBookid());
                String qty = String.valueOf(b.getQuantity());
                String price = String.valueOf(b.getPrice());
                String total = String.valueOf(b.getTotal());

                grand = grand+b.getTotal();


                listContent.append(i+". "+bookname+"\n\t"+qty+" pcs"+"\t"+price+" MMK"+"\t"+total+" MMK"+"\n\n");
                i++;


            }

            listContent.append("Total Amount : "+grand+" MMK"+"\n\n");

            listContent.append("\t\t\t\t\t End\n\n");

            reportArea.setText(listContent.toString());


        }

        else if (event.getClickCount()==2){

           _ordered = (Order) ordertable.getSelectionModel().getSelectedItem();

           Stage stage = new Stage();


            oList.clear();

            FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/saleupdate.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            Stage mainStage = (Stage) ordertable.getScene().getWindow();
            stage.setTitle("Update Sale");
            stage.initOwner(mainStage);
            stage.setScene(scene);
            stage.show();



        }








    }

    private void getorderTableIni(){


        oidCol.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        oDateCol.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        cuCol.setCellValueFactory(new PropertyValueFactory<>("culname"));
        cuphoneCol.setCellValueFactory(new PropertyValueFactory<>("cuphone"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    private void getFindLoadBookData() {



        ObservableList<Order> observableList = FXCollections.observableArrayList();

        Orderdb orderdb = new Orderdb();

        List<Order> orderList= null;

        orderList = orderdb.getList();

        for(Order m :orderList){

            observableList.add(m);
        }


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Order> filteredData = new FilteredList<>(observableList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.

        ssearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(filter -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filter.getOrderid().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else if (filter.getOrderdate().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getCulname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else if (filter.getCuphone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else if (String.valueOf(filter.getTotal()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }


                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Order> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(ordertable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        ordertable.setItems(sortedData);


    }


    private void setFilter(){

        ObservableList i = ordertable.getSelectionModel().getSelectedItems();

        ObservableList<Order> updateList= FXCollections.observableArrayList();

        int p=0;

        for(Object z: i){

           Order order = (Order) i.get(p);
            updateList.add(order);
            p++;
        }


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Order> filteredData = new FilteredList<>(updateList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.

        ssearch1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(filter -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (filter.getOrderid().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else if (filter.getOrderdate().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (filter.getCulname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else if (filter.getCuphone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else if (String.valueOf(filter.getTotal()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }


                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Order> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(ordertable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        ordertable.setItems(sortedData);
    }



}
