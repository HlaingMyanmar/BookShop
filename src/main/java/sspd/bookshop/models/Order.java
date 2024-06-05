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
}
