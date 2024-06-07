package sspd.bookshop.modules;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sspd.bookshop.databases.Authordb;
import sspd.bookshop.databases.Categorydb;
import sspd.bookshop.models.Author;
import sspd.bookshop.models.Category;

import java.util.List;

public  class Deliver implements GenerateResult {

    @Override
    public ObservableList<String> getCategoryNameList(){

        ObservableList<String> list = FXCollections.observableArrayList();

        Categorydb db = new Categorydb();

        List<Category> cList = db.getList();

        for(Category c:cList){

            list.add(c.getCategory_name());

        }

        return list;


    }

    @Override
    public String getCategoryCode(String itemname) {


        Categorydb categorydb = new Categorydb();

        List<Category> cList = categorydb.getList();


        return cList.stream()
                .filter(c -> c.getCategory_name().equals(itemname))
                .map(Category::getCategory_id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ObservableList<String> getAuthorNameList(){

        ObservableList<String> list = FXCollections.observableArrayList();

        Authordb db = new Authordb();

        List<Author> cList = db.getList();

        for(Author c:cList){

            list.add(c.getAuthor_name());

        }

        return list;


    }

    @Override
    public String getAuthorCode(String authorname) {

        Authordb db = new Authordb();

        List<Author> cList = db.getList();


        return cList.stream()
                .filter(c -> c.getAuthor_name().equals(authorname))
                .map(Author::getAuthor_id)
                .findFirst()
                .orElse(null);
    }


}
