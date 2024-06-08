package sspd.bookshop.modules;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.databases.Authordb;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.databases.Categorydb;
import sspd.bookshop.models.Author;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Category;


import javax.swing.text.TableView;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public  class Deliver implements GenerateResult,GenerateResult.Filter {


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
    public ObservableList<Book> getAuthorFilter(String categoryName) {

        Bookdb db = new Bookdb();

        ObservableList<Book> list = FXCollections.observableArrayList();

        List<Book> bookList = db.getFindByCategory(getCategoryCode(categoryName));



        for(Book b :bookList){

           list.add(new Book(b.getBookid(),b.getBookname(),b.getQuantity(),b.getPrice(),getAuthorName(b.getAid()),getCategoryName(b.getCid())));

        }


        return list;


    }
}
