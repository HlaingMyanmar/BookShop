package sspd.bookshop.DAO;

import sspd.bookshop.models.Author;

import java.sql.Connection;
import java.util.List;



public class Authordb implements DataAccessObject<Author> {

    Connection con = DatabaseConnector.getInstance().getConn();



    @Override
    public List<Author> getList() {






        return List.of();
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void create(Author author) {

    }

    @Override
    public void delete(Author author) {

    }
}
