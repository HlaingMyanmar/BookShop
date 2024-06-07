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

        Connection con = DatabaseConnector.getInstance().getConn();

        String sql = "SELECT * FROM author ORDER by aid desc ";

        ResultSet rs = null;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

             rs = pst.executeQuery();

             List<Author> authorList = new ArrayList<>();

             while(rs.next()){

                 String aid = rs.getString("aid");
                 String aname = rs.getString("aname");

                 Author author = new Author(aid,aname);

                 authorList.add(author);

             }



        return authorList;




        } catch (SQLException e) {


            throw new RuntimeException(e);
        }




    }

    @Override
    public void update(Author author) {

        String sql = "UPDATE author SET aname=? WHERE aid=?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,author.getAuthor_name());
            pst.setString(2,author.getAuthor_id());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Update Successful");


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }

    }

    @Override
    public void create(Author author) {

        Connection con = DatabaseConnector.getInstance().getConn();


        String sql = "INSERT INTO author(aid, aname) VALUES (?,?)";

        try(PreparedStatement pst  =con.prepareStatement(sql)) {

            pst.setString(1,author.getAuthor_id());
            pst.setString(2,author.getAuthor_name());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Insert Successful");


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(Author author) {

    }
}
