package sspd.bookshop.models;


import java.sql.Date;

public class Order {

    private String orderid;
    private Date orderdate;
    private String culname;
    private String cuphone;

    public Order(String orderid, Date orderdate, String culname, String cuphone) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.culname = culname;
        this.cuphone = cuphone;
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
