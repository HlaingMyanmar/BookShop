package sspd.bookshop.models;

import java.sql.Date;

public class PurchaseReturn {

    private int rid;
    private String puid;
    private Date rdate;

    public PurchaseReturn(int rid, String puid, Date rdate) {
        this.rid = rid;
        this.puid = puid;
        this.rdate = rdate;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }




}
