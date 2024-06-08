package sspd.bookshop.models;

public class Book {

    private String bookname;
    private String bookid;
    private int quantity;
    private int price;
    private String aid;
    private String cid;

    public Book( String bookid,String bookname, int quantity, int price,String aid,String cid){

        this.bookname = bookname;
        this.bookid = bookid;
        this.quantity = quantity;
        this.price = price;
        this.aid = aid;
        this.cid = cid;


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

    @Override
    public String toString() {
        return "Book{" +
                "bookname='" + bookname + '\'' +
                ", bookid='" + bookid + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }



}
