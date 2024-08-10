package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import sspd.bookshop.Alerts.AlertBox;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.databases.Purchasedb;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Purchase;
import sspd.bookshop.modules.Deliver;
import sspd.bookshop.modules.TableUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static sspd.bookshop.controllers.LoginController._level;
import static sspd.bookshop.modules.Currency.convertToMyanmarCurrency;

public class BuyerController extends Deliver implements Initializable {

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

    @FXML
    private Button printpurchasebtn;

    @FXML
    private Button deletepurchasebtn;

    @FXML
    private Button newpurchasebtn;

    static  Purchase _updatepurchase = null;

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

        newpurchasebtn.setOnAction(_ -> {

            if(getPermission()) {

                Stage stage = new Stage();

                FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/newpurchase.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());

                } catch (IOException e) {

                    throw new RuntimeException(e);
                }
                stage.initStyle(StageStyle.UTILITY);
                stage.initModality(Modality.WINDOW_MODAL);
                Stage mainStage = (Stage) newpurchasebtn.getScene().getWindow();
                stage.setTitle("အဝယ် အသစ်ထည့်သွင်းခြင်း။");
                stage.initOwner(mainStage);
                stage.setScene(scene);
                stage.show();


            }

        });

        daybtn.setOnAction(_e -> {

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
    private void tableCellsetIcon() {



        Callback<TableColumn<Purchase, String>, TableCell<Purchase, String>> cellFactory = (param) -> {

            return new TableCell<Purchase, String>() {

                private final Button editButton = new Button("+");

                {
                    editButton.setOnAction(event -> {
                        int index = getIndex();
                        if (index < 0 || index >= purchasetable.getItems().size()) {
                            return;
                        }

                        Purchase purchase = (Purchase) purchasetable.getItems().get(index);

                        Purchasedb purchasedb = new Purchasedb();
                        List<Purchase> purchaseList = purchasedb.getList();


                        TableView<Purchase> purchaseTableView = new TableView<>();

                        TableColumn<Purchase, String> pauthorCol = new TableColumn<>("ထုတ်လုပ်သူ");

                        TableColumn<Purchase, String> pcategoryCol= new TableColumn<>("အုပ်စုအမျိုးအစား");

                        TableColumn<Purchase, String> pcodeCol= new TableColumn<>("အဝယ်ကုဒ်");

                        TableColumn<Purchase, Date> pdateCol= new TableColumn<>("ရက်စွဲ");

                        TableColumn<Purchase, String> pnameCol= new TableColumn<>("အမျိုးအမည်");

                        TableColumn<Purchase, Integer> ppriceCol= new TableColumn<>("ဈေးနှုန်း");

                        TableColumn<Purchase, Integer> pqtyCol= new TableColumn<>("အရေအတွက်");

                        TableColumn<Purchase, String> psupplierCol= new TableColumn<>("ထုတ်လုပ်သည့်");

                        TableColumn<Purchase, Integer> ptotalCol= new TableColumn<>("စုစုပေါင်း");

                        pcodeCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("puid"));
                        pdateCol.setCellValueFactory(new PropertyValueFactory<Purchase, Date>("pudate"));
                        pnameCol.setCellValueFactory(new PropertyValueFactory<>("bcode"));
                        pcategoryCol.setCellValueFactory(new PropertyValueFactory<>("cid"));
                        pauthorCol.setCellValueFactory(new PropertyValueFactory<>("aid"));
                        psupplierCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
                        pqtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
                        ppriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
                        ptotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));


                        purchaseTableView.getColumns().addAll(psupplierCol,pcodeCol,pdateCol,pnameCol,pcategoryCol,pauthorCol,pqtyCol,ppriceCol,ptotalCol);

                        List<Purchase> filteredList = purchaseList.stream()
                                .filter(p -> p.getPuid().equals(purchase.getPuid()))
                                .collect(Collectors.toList());

                        purchaseTableView.getItems().setAll(filteredList);

                        TableUtils.setColumnWidthsProportional(purchaseTableView);
                        TableUtils.resizeTableColumnsToFitContent(purchaseTableView);



                        double totalAmount = filteredList.stream()
                                .mapToDouble(Purchase::getTotal) // Assuming getTotal() returns an int
                                .sum();

                        Label totalLabel = new Label("စုစုပေါင်းကုန်ကျငွေ : ( " + convertToMyanmarCurrency(totalAmount)+" )");
                        Label labelcount = new Label("အရေအတွက်: ( " + filteredList.size()+" )ခုရှိပါသည်။");

                        totalLabel.setStyle("-fx-font-weight: bold; -fx-padding: 15;");
                        labelcount.setStyle("-fx-font-weight: bold; -fx-padding: 15;");

                        Popup popup = new Popup();
                        HBox hBox = new HBox(labelcount,totalLabel);
                        VBox vbox = new VBox(purchaseTableView,hBox);
                        vbox.setPadding(new Insets(10));
                        vbox.setStyle("-fx-background-color: white; -fx-border-color: gray;");
                        popup.getContent().add(vbox);

                        Point2D cellLocation = localToScreen(getLayoutBounds().getMinX(), getLayoutBounds().getMaxY());
                        popup.setX(cellLocation.getX());
                        popup.setY(cellLocation.getY());

                        popup.setAutoHide(true);
                        popup.show(getScene().getWindow());

                        AtomicInteger qty = new AtomicInteger();
                        AtomicReference<String> bookcode = new AtomicReference<>();
                        AtomicReference<String> pcode = new AtomicReference<>();


                        purchaseTableView.setOnMouseClicked(even -> {


                            if (even.getClickCount() == 2) {

                                Purchase selectedItem = purchaseTableView.getSelectionModel().getSelectedItem();
                                if (selectedItem != null) {
                                   qty.set(selectedItem.getQty());
                                   bookcode.set(getBookCode(selectedItem.getBcode()));
                                   pcode.set(selectedItem.getPuid());
                                }
                            }

                        });

                        purchaseTableView.setEditable(true);

                        pqtyCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

                        pqtyCol.setOnEditCommit(event1 -> {

                            String value = String.valueOf(event1.getNewValue());


                            try {

                                int intValue = Integer.parseInt(value);


                                event1.getRowValue().setQty(intValue);

                                Bookdb bookdb = new Bookdb();

                                System.out.println("Bcode:"+bookcode.get()+"Qty:"+qty.get()+"new Qty:"+intValue+"Pcode+"+pcode.get());


                                if(qty.get()<intValue){

                                    int resultSub = bookdb.subQty(new Book(bookcode.get(),qty.get()));

                                    int resultSum = bookdb.sumQty(new Book(bookcode.get(),intValue));

                                    int resultPurchaseQty = purchasedb.updateQty(new Purchase(pcode.get(),intValue,bookcode.get()));

                                    if(resultSum==1 && resultSub==1 && resultPurchaseQty==1){

                                        AlertBox.showInformation("ပြုပြင်ခြင်း။","ပြန်လည်ထက်တိုးခြင်း ‌အောင်မြင်ပါသည်။");

                                    }
                                    qty.set(0);

                                }
                                else {

                                    int finalqty = qty.get()-intValue;
                                    int resultSub = bookdb.subQty(new Book(bookcode.get(),finalqty));

                                    int resultPurchaseQty = purchasedb.updateQty(new Purchase(pcode.get(),intValue,bookcode.get()));

                                    if( resultSub==1 && resultPurchaseQty==1){

                                        AlertBox.showInformation("ပြုပြင်ခြင်း။","ပြန်လည်နုတ်ယူခြင်း ‌အောင်မြင်ပါသည်။");

                                    }
                                    qty.set(0);


                                }








                            } catch (NumberFormatException e) {


                            }
                        });

                        purchaseTableView.setOnMouseClicked(event1 -> {

                            if(getPermission()){

                                Purchase  p = purchaseTableView.getSelectionModel().getSelectedItem();

                                _updatepurchase = new Purchase(p.getPuid(),p.getPudate(),getBookCode(p.getBcode()));


                                Stage stage = new Stage();

                                FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/netprice.fxml"));
                                Scene scene = null;
                                try {
                                    scene = new Scene(fxmlLoader.load());

                                } catch (IOException e) {

                                    throw new RuntimeException(e);
                                }
                                stage.initStyle(StageStyle.UTILITY);
                                stage.initModality(Modality.WINDOW_MODAL);
                                Stage mainStage = (Stage) newpurchasebtn.getScene().getWindow();
                                stage.setTitle("မူရင်း။");
                                stage.initOwner(mainStage);
                                stage.setScene(scene);
                                stage.show();


                            }











                        });







                    });
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };
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

    private boolean getPermission(){

        boolean pre = false;


        if(_level.equals("Technical") || _level.equals("Admin")){

                pre =true;


        }

        else {
            AlertBox.showWarning("သတ်မှတ်ချက်များ","ကန့်သက်စည်းမျဉ်း "+"\n"+"\nသင်ဝင်‌ရောက်ခွင့်မရှိပါ။");
        }
        return pre;


    }





}
