package sspd.bookshop.models;

public class Supplier {

    private String s_id;
    private String s_name;
    private String s_phone;
    private String s_address;

    public Supplier(String s_id, String s_name, String s_phone, String s_address) {
        this.s_id = s_id;
        this.s_name = s_name;
        this.s_phone = s_phone;
        this.s_address = s_address;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }


}
