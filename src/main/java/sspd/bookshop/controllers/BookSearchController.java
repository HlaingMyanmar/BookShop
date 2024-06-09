package sspd.bookshop.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class BookSearchController {

    @FXML
    private ComboBox<?> bauthor;

    @FXML
    private TableColumn<?, ?> bauthorCol;

    @FXML
    private ComboBox<?> bcategory;

    @FXML
    private TableColumn<?, ?> bcategoryCol;

    @FXML
    private TextField bcode;

    @FXML
    private TableColumn<?, ?> bcodeCol;

    @FXML
    private TextField bdescription;

    @FXML
    private TableColumn<?, ?> bnameCol;

    @FXML
    private TableView<?> booktable;

    @FXML
    private TextField bprice;

    @FXML
    private TableColumn<?, ?> bpriceCol;

    @FXML
    private TextField bqty;

    @FXML
    private TableColumn<?, ?> bqtyCol;

    @FXML
    private ImageView filter1;

    @FXML
    private ImageView filter2;

    @FXML
    private AnchorPane hidePane;

    @FXML
    private TextField searchBox;

    @FXML
    private TextField searchBox1;

    @FXML
    private Button showAction;

    @FXML
    void bookrefreshAction(MouseEvent event) {

    }

    @FXML
    void noticeAction(MouseEvent event) {

    }

    @FXML
    void printsaveNewBook(MouseEvent event) {

    }

    @FXML
    void saveNewBook(MouseEvent event) {

    }


}
