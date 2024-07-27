package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import sspd.bookshop.Alerts.AlertBox;
import sspd.bookshop.databases.Purchasedb;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Purchase;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static sspd.bookshop.modules.Currency.convertToMyanmarCurrency;

public class BuyerController implements Initializable {

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
    private Label lbCount;

    @FXML
    private Label lbTotal;

    @FXML
    private Spinner<Integer> dayPicker;

    @FXML
    private Spinner<Integer> monthPicker;

    @FXML
    private Spinner<Integer> yearPicker;

    @FXML
    private Button daybtn;

    @FXML
    private Button monthbtn;

    @FXML
    private Button yearbtn;

    @FXML
    private Button clearbtn;

    @FXML
    private Button specialsearchbtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getIni();

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

    private void getIni(){

        getIniPurchaseTable();

        int day = LocalDate.now().getDayOfMonth();
        dayPicker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, day));

        int year = LocalDate.now().getYear();
        yearPicker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, year));

        int month = LocalDate.now().getMonthValue();
        monthPicker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));
        monthPicker.getValueFactory().setValue(month);

        daybtn.setOnAction(event -> {


            Purchasedb purchasedb = new Purchasedb();

            try {

                List<Purchase> purchaseList = purchasedb.getDay(dayPicker.getValue(),monthPicker.getValue(),yearPicker.getValue());
                List<Purchase> purchaseListSum = purchasedb.getDayTotal(dayPicker.getValue(),monthPicker.getValue(),yearPicker.getValue());

                double sum = purchaseListSum.stream()
                        .mapToDouble(Purchase::getTotal)
                        .sum();
                lbTotal.setText(convertToMyanmarCurrency(sum));
                lbCount.setText(String.valueOf(purchaseList.size()));
                if(purchaseList.isEmpty()){

                    AlertBox.showWarning("Data Option","No Data Found!!!");
                }
                purchasetable.getItems().setAll(purchaseList);

            }catch (NullPointerException e){


            }



        });



        monthbtn.setOnAction(event -> {

            Purchasedb purchasedb = new Purchasedb();

            try {

                List<Purchase> purchaseList = purchasedb.getMonth(monthPicker.getValue(), yearPicker.getValue());
                List<Purchase> purchaseListSum = purchasedb.getMonth(monthPicker.getValue(), yearPicker.getValue());
                double sum = purchaseListSum.stream()
                        .mapToDouble(Purchase::getTotal)
                        .sum();
                lbTotal.setText(convertToMyanmarCurrency(sum));
                lbCount.setText(String.valueOf(purchaseList.size()));
                if(purchaseList.isEmpty()){

                    AlertBox.showWarning("Data Option","No Data Found!!!");
                }
                purchasetable.getItems().setAll(purchaseList);

            }catch (NullPointerException e){


            }


        });

        yearbtn.setOnAction(event -> {

            Purchasedb purchasedb = new Purchasedb();

            try {

                List<Purchase> purchaseList = purchasedb.getYear(yearPicker.getValue());
                List<Purchase> purchaseListSum = purchasedb.getYearTotal(yearPicker.getValue());

                double sum = purchaseListSum.stream()
                        .mapToDouble(Purchase::getTotal)
                        .sum();
                lbTotal.setText(convertToMyanmarCurrency(sum));
                lbCount.setText(String.valueOf(purchaseList.size()));

                if(purchaseList.isEmpty()){

                    AlertBox.showWarning("Data Option","No Data Found!!!");
                }

                purchasetable.getItems().setAll(purchaseList);

            }catch (NullPointerException _){

            }

        });

        getLoadData();

        clearbtn.setOnAction(event -> {


            getIni();


        });

        tableCellsetIcon();


        purchasetable.setRowFactory(tv -> {
            TableRow<Purchase> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Purchase rowData = row.getItem();


                    


                }
            });
            return row;
        });

        specialsearchbtn.setOnAction(event -> {

            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/specialfindpurchase.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            Stage mainStage = (Stage) specialsearchbtn.getScene().getWindow();
            stage.setTitle("Purchase Option");
            stage.initOwner(mainStage);
            stage.setScene(scene);
            stage.show();


        });

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

    private  void getLoadData(){

        Purchasedb purchasedb = new Purchasedb();

        try {

            List<Purchase> purchaseList = purchasedb.getDay(dayPicker.getValue(),monthPicker.getValue(),yearPicker.getValue());
            List<Purchase> purchaseListSum = purchasedb.getDayTotal(dayPicker.getValue(),monthPicker.getValue(),yearPicker.getValue());

            purchasetable.getItems().setAll(purchaseList);

            double sum = purchaseListSum.stream()
                    .mapToDouble(Purchase::getTotal)
                    .sum();
            lbTotal.setText(convertToMyanmarCurrency(sum));
            lbCount.setText(String.valueOf(purchaseList.size()));


        }catch (NullPointerException _){


        }

    }





}
