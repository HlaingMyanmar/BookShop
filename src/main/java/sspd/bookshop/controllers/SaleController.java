package sspd.bookshop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import sspd.bookshop.databases.Orderdb;
import sspd.bookshop.databases.Saledb;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Order;
import sspd.bookshop.models.Sale;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class SaleController implements Initializable {

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
    private Button newSalebtn;

    @FXML
    private StackPane previewPane;

    @FXML
    private TextField searchID;

    @FXML
    private TextField searchamount;

    @FXML
    private TextField searchcustomer;

    @FXML
    private TextField searchdate;

    @FXML
    private TextField searchphone;




    private void ini(){

        getorderTableIni();
        getLoadData();

        newSalebtn.setOnAction(_ -> {



        });

        ordertable.setOnMouseClicked(event -> {


            if (event.getClickCount() == 1) {
                Order order = (Order) ordertable.getSelectionModel().getSelectedItem();

                Saledb saledb = new Saledb();
                List<Sale> saleList = saledb.findByOrderCode(order.getOrderid());

                

                try {
                    getReport(saleList);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (JRException e) {
                    throw new RuntimeException(e);
                }


            }
        });

    }


    private void getorderTableIni(){


        oidCol.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        oDateCol.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        cuCol.setCellValueFactory(new PropertyValueFactory<>("culname"));
        cuphoneCol.setCellValueFactory(new PropertyValueFactory<>("cuphone"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    private void getLoadData(){

        ObservableList<Order> observableList = FXCollections.observableArrayList();

        Orderdb orderdb = new Orderdb();
        List<Order> orderList = orderdb.getList();
        observableList.addAll(orderList);

        FilteredList<Order> filteredData = new FilteredList<>(observableList, b -> true);

        addTextFieldListener(searchID, filteredData);
        addTextFieldListener(searchdate, filteredData);
        addTextFieldListener(searchID, filteredData);
        addTextFieldListener(searchcustomer, filteredData);
        addTextFieldListener(searchphone, filteredData);
        addTextFieldListener(searchamount, filteredData);



        SortedList<Order> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(ordertable.comparatorProperty());


        ordertable.setItems(sortedData);


    }

    private void addTextFieldListener(TextField textField, FilteredList<Order> filteredData) {


        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(order -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (order.getOrderid().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(order.getOrderdate()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (order.getCulname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (order.getCuphone().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (String.valueOf(order.getTotal()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });


        });


    }

    private void getReport(List<Sale>saleList) throws FileNotFoundException, JRException {


        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(saleList );


        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("Collection",itemsJRBean);

        //InputStream input = new FileInputStream(new File("F:\\Java Projects\\Reports\\SaleInvoice\\invoice.jrxml"));
        InputStream input = new FileInputStream(new File("E:\\Java Projects\\src\\main\\resources\\report\\saleinvoice.jrxml"));


        JasperDesign jasperDesign = JRXmlLoader.load(input);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,new JREmptyDataSource());

        JasperViewer viewer = new JasperViewer(jasperPrint,false);
        //viewer.setVisible(true);

        SwingNode swingNode = new SwingNode();

        JPanel panel = (JPanel) viewer.getContentPane();

        swingNode.setContent(panel);

        previewPane.getChildren().add(swingNode);

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ini();

    }
}
