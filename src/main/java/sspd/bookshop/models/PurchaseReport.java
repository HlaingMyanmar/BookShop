package sspd.bookshop.models;

import java.sql.Date;

public class PurchaseReport {
    private String sname;
    private String puid;
    private Date pudate;
    private String bname;
    private String cname;
    private String aname;
    private int qty;
    private int price;
    private int total;

//    public PurchaseReport(String sname, String puid, Date pudate, String bname, String cname, String aname, int qty, int price, int total) {
//        this.sname = sname;
//        this.puid = puid;
//        this.pudate = pudate;
//        this.bname = bname;
//        this.cname = cname;
//        this.aname = aname;
//        this.qty = qty;
//        this.price = price;
//        this.total = total;
//    }


    @Override
    public String toString() {
        return "PurchaseReport{" +
                "sname='" + sname + '\'' +
                ", puid='" + puid + '\'' +
                ", pudate=" + pudate +
                ", bname='" + bname + '\'' +
                ", cname='" + cname + '\'' +
                ", aname='" + aname + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", total=" + total +
                '}';
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getPudate() {
        return pudate;
    }

    public void setPudate(Date pudate) {
        this.pudate = pudate;
    }
}
