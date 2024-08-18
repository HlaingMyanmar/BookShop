package sspd.bookshop.controllers;

import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import sspd.bookshop.Alerts.AlertBox;
import sspd.bookshop.databases.Orderdb;
import sspd.bookshop.databases.Purchasedb;
import sspd.bookshop.databases.Saledb;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Order;
import sspd.bookshop.models.Purchase;
import sspd.bookshop.models.Sale;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

import static sspd.bookshop.controllers.NewSaleController.oList;
import static sspd.bookshop.modules.Currency.convertToMyanmarCurrency;

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



    @FXML
    private Button daybtn;

    @FXML
    private Spinner<Integer> monthPicker;

    @FXML
    private Spinner<Integer> yearPicker;

    @FXML
    private Spinner<Integer> dayPicker;

    @FXML
    private Button monthbtn;

    @FXML
    private JFXToggleButton specialSearchtoglebtn;


    @FXML
    private Label lbCount;

    @FXML
    private Label lbTotal;



    @FXML
    private Button yearbtn;




    private void ini(){

        getorderTableIni();
        setDisable();


        newSalebtn.setOnAction(_ -> {
            Stage stage = new Stage();


            oList.clear();


            FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/newSales.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            Stage mainStage = (Stage) newSalebtn.getScene().getWindow();
            stage.setTitle("New Sale");
            stage.initOwner(mainStage);
            stage.setScene(scene);
            stage.show();


        });

        ordertable.setOnMouseClicked(event -> {





            if (event.getClickCount() == 2) {

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

        int day = LocalDate.now().getDayOfMonth();
        dayPicker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, day));

        int year = LocalDate.now().getYear();
        yearPicker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, year));

        int month = LocalDate.now().getMonthValue();
        monthPicker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));
        monthPicker.getValueFactory().setValue(month);


      specialSearchtoglebtn.setOnAction(_ -> {


          if (specialSearchtoglebtn.isSelected()) {


              getLoadData();
              setEnable();

          } else {

              setDisable();
              getLoadDataIni();

          }


      });


        daybtn.setOnAction(_e -> {

            Orderdb orderdb = new Orderdb();

            try {

                List<Order> orderList = orderdb.getDay(dayPicker.getValue(),monthPicker.getValue(),yearPicker.getValue());
                ObservableList<Order> oList = FXCollections.observableArrayList();

                oList.addAll(orderList);
                ordertable.setItems(oList);


                double sum = orderList.stream()
                        .mapToDouble(Order::getTotal)
                        .sum();
                lbTotal.setText(convertToMyanmarCurrency(sum));
                lbCount.setText(String.valueOf(orderList.size()));

                if(orderList.isEmpty()){

                    AlertBox.showWarning("Data Option","No Data Found!!!");
                }

            }catch (NullPointerException e){


            }


        });

        monthbtn.setOnAction(event -> {

            Orderdb orderdb = new Orderdb();

            try {

                List<Order> orderList = orderdb.getMonth(monthPicker.getValue(), yearPicker.getValue());
                ObservableList<Order> oList = FXCollections.observableArrayList();

                oList.addAll(orderList);
                ordertable.setItems(oList);


                double sum = orderList.stream()
                        .mapToDouble(Order::getTotal)
                        .sum();
                lbTotal.setText(convertToMyanmarCurrency(sum));
                lbCount.setText(String.valueOf(orderList.size()));

                if(orderList.isEmpty()){

                    AlertBox.showWarning("Data Option","No Data Found!!!");
                }

            }catch (NullPointerException e){


            }


        });


      yearbtn.setOnAction(_ -> {

         Orderdb orderdb = new Orderdb();

          try {

              List<Order> orderList = orderdb.getYear(yearPicker.getValue());
              ObservableList<Order> oList = FXCollections.observableArrayList();
              oList.addAll(orderList);
              ordertable.setItems(oList);


              double sum = orderList.stream()
                      .mapToDouble(Order::getTotal)
                      .sum();
              lbTotal.setText(convertToMyanmarCurrency(sum));
              lbCount.setText(String.valueOf(orderList.size()));

              if(orderList.isEmpty()){

                  AlertBox.showWarning("Data Option","No Data Found!!!");
              }



          }catch (NullPointerException _){

          }


      });






    }

    private  void getLoadDataIni(){

        Orderdb orderdb = new Orderdb();

        try {

            List<Order> orderList =  orderdb.getDay(dayPicker.getValue(),monthPicker.getValue(),yearPicker.getValue());

            ObservableList<Order> oList = FXCollections.observableArrayList();
            oList.addAll(orderList);
            ordertable.setItems(oList);

            double sum = orderList.stream()
                    .mapToDouble(Order::getTotal)
                    .sum();
            lbTotal.setText(convertToMyanmarCurrency(sum));
            lbCount.setText(String.valueOf(orderList.size()));


        }catch (NullPointerException _){


        }

    }

    private void setDisable(){

        searchID.setDisable(true);
        searchdate.setDisable(true);
        searchcustomer.setDisable(true);
        searchphone.setDisable(true);
        searchamount.setDisable(true);
    }
    private void setEnable(){

        searchID.setDisable(false);
        searchdate.setDisable(false);
        searchcustomer.setDisable(false);
        searchphone.setDisable(false);
        searchamount.setDisable(false);
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
