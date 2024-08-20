package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static sspd.bookshop.DAO.DatabaseConnector.getCloseConnection;


public class Authordb implements DataAccessObject<Author> {



    Connection con = DatabaseConnector.getInstance().getConn();

    @Override
    public List<Author> getList() {



        String sql = "SELECT * FROM `author` ORDER BY cast(SubString(aid,4) as UNSIGNED) DESC ";

        ResultSet rs = null;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

             rs = pst.executeQuery();

             List<Author> authorList = new ArrayList<>();

             while(rs.next()){

                 Author author = new Author(


                         rs.getString("aid"),
                         rs.getString("aname"));


                 authorList.add(author);

             }



        return authorList;




        } catch (SQLException e) {


            throw new RuntimeException(e);
        }




    }

    @Override
    public int update(Author author) {

        int i = 0;

        String sql = "UPDATE author SET aname=? WHERE aid=?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,author.getAuthor_name());
            pst.setString(2,author.getAuthor_id());
           i =  pst.executeUpdate();





        } catch (SQLException e) {


            throw new RuntimeException(e);
        }

        return i;

    }

    @Override
    public int create(Author author) {

        int i = 0;

        Connection con = DatabaseConnector.getInstance().getConn();


        String sql = "INSERT INTO author(aid, aname) VALUES (?,?)";

        try(PreparedStatement pst  =con.prepareStatement(sql)) {

            pst.setString(1,author.getAuthor_id());
            pst.setString(2,author.getAuthor_name());

            i = pst.executeUpdate();




        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


        return i ;
    }

    @Override
    public int delete(Author author) {

        return 0;

    }
}
