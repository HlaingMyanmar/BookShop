package sspd.bookshop.models;

import java.sql.Date;

public class PurchaseReturnDetail extends  PurchaseReturn{




    private String bcode;
    private String sid;
    private int qty;
    private int amount;
    private String returnReason;


    public PurchaseReturnDetail( String puid, Date rdate) {

        super( puid, rdate);

    }

    public PurchaseReturnDetail( String puid, Date rdate, String bcode, String sid, int qty, int amount, String returnReason) {
        super( puid, rdate);
        this.bcode = bcode;
        this.sid = sid;
        this.qty = qty;
        this.amount = amount;
        this.returnReason = returnReason;
    }

    public PurchaseReturnDetail( String puid, Date rdate, String bcode, int qty, int amount, String returnReason) {
        super( puid, rdate);
        this.bcode = bcode;
        this.qty = qty;
        this.amount = amount;
        this.returnReason = returnReason;
    }





    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }
}
