package sspd.bookshop.models;

import java.sql.Date;

public class PurchaseReturn {


    private String puid;
    private Date rdate;

    public PurchaseReturn( String puid, Date rdate) {
        this.puid = puid;
        this.rdate = rdate;
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
