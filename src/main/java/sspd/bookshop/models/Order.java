package sspd.bookshop.models;

public class Order {
    private String orderid;
    private String bookcode;

    private int quanity;

    private int price;

    public Order(String orderid, String bookcode, int quanity, int price) {
        this.orderid = orderid;
        this.bookcode = bookcode;
        this.quanity = quanity;
        this.price = price;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getBookcode() {
        return bookcode;
    }

    public void setBookcode(String bookcode) {
        this.bookcode = bookcode;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
