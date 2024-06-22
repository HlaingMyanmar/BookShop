package sspd.bookshop.models;


import java.sql.Date;

public class Order {

    private String orderid;
    private Date orderdate;
    private String culname;
    private String cuphone;
    private int total;

    public Order(String orderid, Date orderdate, String culname, String cuphone) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.culname = culname;
        this.cuphone = cuphone;
    }

    public Order(String orderid, Date orderdate, String culname, String cuphone, int total) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.culname = culname;
        this.cuphone = cuphone;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getCulname() {
        return culname;
    }

    public void setCulname(String culname) {
        this.culname = culname;
    }

    public String getCuphone() {
        return cuphone;
    }

    public void setCuphone(String cuphone) {
        this.cuphone = cuphone;
    }
}
