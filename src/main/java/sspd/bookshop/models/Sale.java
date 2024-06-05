package sspd.bookshop.models;

import java.security.Timestamp;
import java.time.DateTimeException;

public class Sale {

    private int saleid;

    private Timestamp date;
    private String cuname;
    private String cuphone;
    private int total;

    public Sale(int saleid,String cuname,Timestamp date,String cuphone,int total){
        this.saleid = saleid;
        this.cuname =cuname;
        this.date = date;
        this.cuphone=cuphone;
        this.total = total;
    }

    public int getSaleid() {
        return saleid;
    }

    public void setOrderid(int orderid) {
        this.saleid = orderid;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getCuname() {
        return cuname;
    }

    public void setCuname(String cuname) {
        this.cuname = cuname;
    }

    public String getCuphone() {
        return cuphone;
    }

    public void setCuphone(String cuphone) {
        this.cuphone = cuphone;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
