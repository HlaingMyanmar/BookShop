package sspd.bookshop.models;

import java.sql.Date;

public class NetPurchaseprice {

    private String puid;
    private Date pudate;
    private String bcode;
    private String currency;
    private double currency_amount;
    private double amount;
    private int tran;
    private  int expen;
    private int qty;
    private double percen;
    private double netprofit;

    public NetPurchaseprice(String puid, Date pudate, String bcode, String currency, double currency_amount, double amount, int tran, int expen, int qty, double percen, double netprofit) {
        this.puid = puid;
        this.pudate = pudate;
        this.bcode = bcode;
        this.currency = currency;
        this.currency_amount = currency_amount;
        this.amount = amount;
        this.tran = tran;
        this.expen = expen;
        this.qty = qty;
        this.percen = percen;
        this.netprofit = netprofit;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getCurrency_amount() {
        return currency_amount;
    }

    public void setCurrency_amount(double currency_amount) {
        this.currency_amount = currency_amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTran() {
        return tran;
    }

    public void setTran(int tran) {
        this.tran = tran;
    }

    public int getExpen() {
        return expen;
    }

    public void setExpen(int expen) {
        this.expen = expen;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPercen() {
        return percen;
    }

    public void setPercen(double percen) {
        this.percen = percen;
    }

    public double getNetprofit() {
        return netprofit;
    }

    public void setNetprofit(double netprofit) {
        this.netprofit = netprofit;
    }
}
