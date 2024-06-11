package sspd.bookshop.models;

import java.sql.Date;

public class PurchaseReport {
    private String sid;
    private String puid;
    private Date pudate;
    private String bname;
    private String cname;
    private String aname;
    private int qty;
    private int price;
    private int total;

    public PurchaseReport(String sid, String puid, Date pudate, String bname, String cname, String aname, int qty, int price, int total) {
        this.sid = sid;
        this.puid = puid;
        this.pudate = pudate;
        this.bname = bname;
        this.cname = cname;
        this.aname = aname;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public Date getPudate() {
        return pudate;
    }

    public void setPudate(Date pudate) {
        this.pudate = pudate;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
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

    @Override
    public String toString() {
        return "PurchaseReport{" +
                "sid='" + sid + '\'' +
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
}
