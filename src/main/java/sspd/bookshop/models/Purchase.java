package sspd.bookshop.models;

import java.sql.Date;

public class Purchase {

    private String puid;
    private Date pudate;
    private String bcode;
    private String cid;
    private String aid;
    private String sid;
    private int qty;
    private int price;
    private int total;
    private String remark;


    public Purchase(String puid, String bcode, int qty, int total, String remark) {
        this.puid = puid;
        this.bcode = bcode;
        this.qty = qty;
        this.total = total;
        this.remark = remark;
    }

    public Purchase(String puid, Date pudate, String bcode, String cid, String aid, String sid, int qty, int price, int total) {
        this.puid = puid;
        this.pudate = pudate;
        this.bcode = bcode;
        this.cid = cid;
        this.aid = aid;
        this.sid = sid;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }


    public Purchase(int price, String bcode) {
        this.price = price;
        this.bcode = bcode;
    }
    public Purchase(String puid,int qty,String bcode) {
        this.qty = qty;
        this.puid = puid;
        this.bcode = bcode;
    }



    public Purchase(String puid, Date pudate, String bcode, String cid, String aid, String sid, int qty, int price, String remark) {
        this.puid = puid;
        this.price = price;
        this.qty = qty;
        this.sid = sid;
        this.aid = aid;
        this.cid = cid;
        this.bcode = bcode;
        this.pudate = pudate;
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "puid='" + puid + '\'' +
                ", pudate=" + pudate +
                ", bcode='" + bcode + '\'' +
                ", cid='" + cid + '\'' +
                ", aid='" + aid + '\'' +
                ", sid='" + sid + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", total=" + total +
                ", remark='" + remark + '\'' +
                '}';
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

    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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
}
