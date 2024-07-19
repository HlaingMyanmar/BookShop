package sspd.bookshop.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.databases.Saledb;
import sspd.bookshop.launch.Bookshop;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Sale;
import sspd.bookshop.modules.Deliver;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.util.*;

import static sspd.bookshop.controllers.NewSaleController.oList;
import static sspd.bookshop.controllers.SaleDashboardController._ordered;

public class SaleUpdateController extends Deliver implements Initializable {

    @FXML
    private Label amountlb;

    @FXML
    private TextField aname;



    @FXML
    private TextField bcode;

    @FXML
    private TextField bname;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, String> bookcodeCol;

    @FXML
    private TableColumn<Sale, String> booknameCol;

    @FXML
    private TableColumn<Book, String> categoryCol;

    @FXML
    private TableColumn<Book, Integer> qtyCol;

    @FXML
    private TableColumn<Book, Integer> priceCol;

    @FXML
    private TableColumn<Book, Integer> totalCol;

    @FXML
    private TextField caname;



    @FXML
    private TextField cname;

    @FXML
    private TextField cphone;

    @FXML
    private TextField odate;

    @FXML
    private TextField oid;

    @FXML
    private TableView otable;

    @FXML
    private Label pcslb;



    @FXML
    private TextField ptxt;


    @FXML
    private TextField qtytxt;

    @FXML
    private AnchorPane test;

    @FXML
    private TextField total;


    Bookdb bookdb = new Bookdb();

    Saledb saledb = new Saledb();



    @FXML
    void addItem(MouseEvent event) {

        String orderID = oid.getText();

        Date date = Date.valueOf(odate.getText());

        String customerName = cname.getText();

        String customerPhone = cphone.getText();

        Book book = new Book(bcode.getText(),bname.getText(),Integer.parseInt(qtytxt.getText()),Integer.parseInt(ptxt.getText()),aname.getText(),caname.getText());

        Sale sale = new Sale(orderID,date,customerName,customerPhone,book.getBookid(),book.getBookname(),getCategoryCode(book.getCid()),getAuthorCode(book.getAid()),book.getQuantity(),book.getPrice(),book.getTotal());

        bookdb.subQty(book);

        saledb.create(sale);

        oList.add(book);



        getOrderList();
        getQtyCalulate();
        getTotalCalulate();

    }

    @FXML
    void addlistItem(KeyEvent event) {

    }

    @FXML
    void comfirm(MouseEvent event) {

    }

    @FXML
    void getDataAction(MouseEvent event) {

        try {

            bcode.setText(_book.getBookid());
            bname.setText(_book.getBookname());
            caname.setText(_book.getCid());
            aname.setText(_book.getAid());
            ptxt.setText(String.valueOf(_book.getPrice()));

        }catch (NullPointerException ex){

            JOptionPane.showMessageDialog(null,"Please Find Item 'F1' Click ","Notice",1);

        }
    }

    @FXML
    void getbook(KeyEvent event) {

    }

    @FXML
    void helpAction(KeyEvent event) {

        if(event.getCode()== KeyCode.F1){


            getChildbookTable();



        }

    }

    @FXML
    void print(MouseEvent event) {

        getPrint();

    }
    private void getPrint(){

        List<Sale> saleList = new ArrayList<>();

        for(Book b : oList){



            Sale sale = new Sale(oid.getText(),Date.valueOf(odate.getText()),cname.getText(),cphone.getText(),b.getBookid(),b.getBookname(),b.getCid(),b.getAid(),b.getQuantity(),b.getPrice(),b.getTotal());

            saleList.add(sale);


        }


        try{

            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(saleList);


            Map<String,Object> parameters = new HashMap<String,Object>();
            parameters.put("Collection",itemsJRBean);

            //InputStream input = new FileInputStream(new File("F:\\Java Projects\\Reports\\SaleInvoice\\invoice.jrxml"));
            InputStream input = new FileInputStream(new File("F:\\Java Projects\\src\\main\\resources\\report\\saleinvoice.jrxml"));



            JasperDesign jasperDesign = JRXmlLoader.load(input);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,new JREmptyDataSource());

            JasperViewer viewer = new JasperViewer(jasperPrint,false);
            viewer.setVisible(true);



        }catch (NullPointerException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    void purchaseCodeKeyAction(KeyEvent event) {

        if (event.getCode()== KeyCode.ENTER){


            int i = (Integer.parseInt(qtytxt.getText())) * (Integer.parseInt(ptxt.getText()));

            total.setText(String.valueOf(i));
        }

    }

    @FXML
    void removeItem(MouseEvent event) {

        int selectedIndex = otable.getSelectionModel().getSelectedIndex();


        if (selectedIndex >= 0) {

           Saledb saledb  = new Saledb();
           Sale sale  = new Sale(_ordered.getOrderid(),oList.get(selectedIndex).getQuantity(),oList.get(selectedIndex).getBookid());
           Book book =  oList.get(selectedIndex);
           int oldvlue = saledb.getQty(_ordered.getOrderid(),book.getBookid());
           Book b = new Book(book.getBookid(),oldvlue,book.getPrice());

            bookdb.sumQty(b);
            saledb.delete(sale);

            otable.getItems().remove(selectedIndex);
            getQtyCalulate();
            getTotalCalulate();

            _book = null;

            getQtyCalulate();
            getTotalCalulate();

        } else {

            JOptionPane.showMessageDialog(null,"No Item Select","Notice",0);
            _book = null;
        }

    }

    private void getordertableInit(){

        bookcodeCol.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        booknameCol.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("cid"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("aid"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getordertableInit();

        oid.setText(_ordered.getOrderid());
        odate.setText(String.valueOf(_ordered.getOrderdate()));
        cname.setText(_ordered.getCulname());
        cphone.setText(_ordered.getCuphone());

        getOrderList();

        pcslb.setText("0 pcs");
        amountlb.setText("0 MMK");
        getQtyCalulate();
        getTotalCalulate();




        otable.setEditable(true);

        qtyCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        qtyCol.setOnEditCommit(event -> {

            String value = String.valueOf(event.getNewValue());

            if(null != value && !value.isEmpty()){

                event.getRowValue().setQuantity(Integer.parseInt(value));

                getRowUpdate();
                getOrderList();
                getQtyCalulate();
                getTotalCalulate();

            }

        });

    }

    private void getOrderList(){

        Saledb saledb = new Saledb();

        oList.clear();

        List<Book> bookList=  saledb.findByOrderID(_ordered.getOrderid());

        for (Book b:bookList){

            Book book = new Book(b.getBookid(),getBookName(b.getBookid()),b.getQuantity(),b.getPrice(),getAuthorName(b.getAid()),getCategoryName(b.getCid()));

            oList.add(book);
        }

        otable.getItems().setAll(oList);


    }

    private void getChildbookTable(){

        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/booksearch.fxml"));

        Scene scene = null;


        try {


            scene = new Scene(fxmlLoader.load());


        } catch (IOException e) {

            throw new RuntimeException(e);

        }
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage mainStage = (Stage) otable.getScene().getWindow();
        stage.initOwner(mainStage);
        stage.setScene(scene);
        stage.show();
    }

    private void getQtyCalulate(){

        int totalqty = 0;

        otable. getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        otable.getSelectionModel().selectAll();

        ObservableList ob =  otable.getSelectionModel().getSelectedItems();

        pcslb.setText(ob.size() +" pcs");




    }

    private void getTotalCalulate(){

        int total = 0;

        otable. getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        otable.getSelectionModel().selectAll();

        ObservableList ob =  otable.getSelectionModel().getSelectedItems();

        int p = 0;

        for(Object b :ob){

            total  = total+((Book)ob.get(p)).getTotal();
            p++;
        }

        amountlb.setText(total +" MMK");




    }

    private  void getRowUpdate(){

        int index = otable.getSelectionModel().getSelectedIndex();

        Book book =  oList.get(index);

        int oldvlue = saledb.getQty(_ordered.getOrderid(),book.getBookid());


        if(oList.get(index).getQuantity()>getDataList(book.getBookid()).getQuantity()){


            JOptionPane.showMessageDialog(null,"This is not Have!!!"+"\nThis is item have "+getDataList(book.getBookid()).getQuantity()+" pcs","Notice",0);

        }
        else {

            int newqty = oList.get(index).getQuantity();

            if(oldvlue>newqty){


                int sumBook =  oldvlue-oList.get(index).getQuantity();

                System.out.println("Sum : "+sumBook);

                Book b = new Book(book.getBookid(),sumBook,book.getPrice());

                bookdb.sumQty(b);

                oList.get(index).setTotal(oList.get(index).getQuantity()*oList.get(index).getPrice());

                Sale sale = new Sale(_ordered.getOrderid(),book.getQuantity(),book.getBookid());

                saledb.getOrderIDupdate(sale);



            }
            else {


                int subBook =  oList.get(index).getQuantity()- oldvlue;

                System.out.println("Sub : "+subBook);

                Book b = new Book(book.getBookid(),subBook,book.getPrice());

                bookdb.subQty(b);

                oList.get(index).setTotal(oList.get(index).getQuantity()*oList.get(index).getPrice());

                Sale sale = new Sale(_ordered.getOrderid(),book.getQuantity(),book.getBookid());

                saledb.getOrderIDupdate(sale);


            }










        }







    }

}

