package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Book;

import java.sql.Connection;
import java.util.List;

public class Bookdb implements DataAccessObject {


    Connection con = DatabaseConnector.getConnect();


    @Override
    public List<Book> getList() {



        return null;

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void create(Object o) {

    }

    @Override
    public void delete(Object o) {

    }
}
