package sspd.bookshop.models;


import java.sql.Date;

public class Sale extends Order{


    private String bcode;
    private String bname;
    private String ccode;
    private String acode;
    private int qty;
    private int price;
    private int total;

    private String oid;
    private Date odate;
    private String cuname;
    private String cuphone;

    public Date getOdate() {
        return odate;
    }

    public void setOdate(Date odate) {
        this.odate = odate;
    }

    public String getCuname() {
        return cuname;
    }

    public void setCuname(String cuname) {
        this.cuname = cuname;
    }

    @Override
    public String getCuphone() {
        return cuphone;
    }

    @Override
    public void setCuphone(String cuphone) {
        this.cuphone = cuphone;
    }

    public Sale(String orderid, Date orderdate, String culname, String cuphone) {
        super(orderid, orderdate, culname, cuphone);
    }

    public Sale(String orderid, Date orderdate, String culname, String cuphone, String bcode, String bname, String ccode, String acode, int qty, int price, int total) {
        super(orderid, orderdate, culname, cuphone);
        this.bcode = bcode;
        this.bname = bname;
        this.ccode = ccode;
        this.acode = acode;
        this.qty = qty;
        this.price = price;
        this.total = total;
        this.oid = orderid;
        this.odate = orderdate;
        this.cuname = culname;
        this.cuphone = cuphone;
    }


    public Sale(String orderid, Date orderdate, String culname, String cuphone, int price, int qty, String acode, String ccode, String bname, String bcode) {
        super(orderid, orderdate, culname, cuphone);
        this.price = price;
        this.qty = qty;
        this.acode = acode;
        this.ccode = ccode;
        this.bname = bname;
        this.bcode = bcode;
    }

    public Sale(String orderid, int qty,String bcode) {
        super(orderid);
        this.qty = qty;
        this.bcode = bcode;
    }




    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}
