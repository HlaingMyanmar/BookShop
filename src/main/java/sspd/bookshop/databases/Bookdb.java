package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Book;
import java.sql.Connection;
import java.util.List;

public class Bookdb implements DataAccessObject<Book> {


    Connection con = DatabaseConnector.getConnect();


    @Override
    public List<Book> getList() {






        return null;

    }

    @Override
    public void update(Book o) {

    }

    @Override
    public void create(Book book) {

    }

    @Override
    public void delete(Book book) {

    }


}
