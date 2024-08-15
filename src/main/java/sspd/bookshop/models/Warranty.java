package sspd.bookshop.models;

public class Warranty {


    private int id;
    private String name;

    public Warranty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Warranty(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
