package sspd.bookshop.models;

public class Book {

    private String bookname;
    private String bookid;
    private int quantity;
    private int price;

    public Book(String bookname,String bookid,int quantity,int price){

        this.bookname = bookname;
        this.bookid = bookid;
        this.quantity = quantity;
        this.price = price;


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
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
