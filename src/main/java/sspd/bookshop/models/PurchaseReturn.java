package sspd.bookshop.models;

import java.sql.Date;
import java.sql.Timestamp;

public class PurchaseReturn {


    private String puid;
    private Timestamp rdate;

    public PurchaseReturn( String puid, Timestamp rdate) {
        this.puid = puid;
        this.rdate = rdate;
    }




    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public Timestamp getRdate() {
        return rdate;
    }

    public void setRdate(Timestamp rdate) {
        this.rdate = rdate;
    }




}
