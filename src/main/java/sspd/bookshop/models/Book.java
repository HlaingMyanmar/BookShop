package sspd.bookshop.models;

import javafx.beans.property.SimpleIntegerProperty;

public class Book {

    private String bookname;
    private String bookid;
    private SimpleIntegerProperty quantity;
    private SimpleIntegerProperty price;
    private String aid;
    private String cid;
    private SimpleIntegerProperty total;

    public Book( String bookid,String bookname, int quantity, int price,String aid,String cid){

        this.bookname = bookname;
        this.bookid = bookid;
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleIntegerProperty(price);
        this.aid = aid;
        this.cid = cid;


    }
    public Book( String bookid,String bookname, int quantity, int price,String aid,String cid,int total){

        this.bookname = bookname;
        this.bookid = bookid;
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleIntegerProperty(price);
        this.aid = aid;
        this.cid = cid;
        this.total = new SimpleIntegerProperty(quantity * price);


    }

    public Book(String bookid, SimpleIntegerProperty quantity) {
        this.bookid = bookid;
        this.quantity = quantity;
    }



    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getTotal() {
        return total.get();
    }

    public SimpleIntegerProperty totalProperty() {
        return total;
    }

    public void setTotal(int total) {
        this.total.set(total);
    }
}
