package sspd.bookshop.modules;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.databases.*;
import sspd.bookshop.models.*;



import java.lang.reflect.InvocationTargetException;

import java.util.List;

public  class Deliver implements GenerateResult {


    protected static Book _book =null;


    @Override
    public ObservableList<String> getDataList(Class<? extends DataAccessObject> dbClass) {

        ObservableList<String> list = FXCollections.observableArrayList();
        try {



            DataAccessObject db = dbClass.getDeclaredConstructor().newInstance();

            List<Category> cList = db.getList();

            for(Category c:cList){

                list.add(c.getCategory_name());

            }

            return list;




        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }

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
    public String getCategoryName(String code) {

        Categorydb categorydb = new Categorydb();

        List<Category> cList = categorydb.getList();


        return cList.stream()
                .filter(c -> c.getCategory_id().equals(code))
                .map(Category::getCategory_name)
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

    @Override
    public String getAuthorName(String code) {


        Authordb db = new Authordb();

        List<Author> cList = db.getList();


        return cList.stream()
                .filter(c -> c.getAuthor_id().equals(code))
                .map(Author::getAuthor_name)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getSupplierCode(String code) {


        Supplierdb supplierdb = new Supplierdb();

        List<Supplier> cList =supplierdb.getList();


        return cList.stream()
                .filter(c -> c.getS_name().equals(code))
                .map(Supplier::getS_id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ObservableList<String> getSupplierNameList() {

        ObservableList<String> list = FXCollections.observableArrayList();

        Supplierdb supplierdb = new Supplierdb();

        List<Supplier> cList = supplierdb.getList();

        for(Supplier c:cList){

            list.add(c.getS_name());

        }

        return list;
    }

    @Override
    public Book getDataList(String bookCode) {


        Bookdb bookdb = new Bookdb();

        List<Book> bookList = bookdb.getList();

        return bookList.stream()
                .filter(c -> c.getBookid().equals(bookCode))
                .findFirst()
                .orElse(null);
    }


}
