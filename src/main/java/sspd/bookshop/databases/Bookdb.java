package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;

import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bookdb implements DataAccessObject<Book> {

    private final Connection con = DatabaseConnector.getConn();

    @Override
    public List<Book> getList() {

       String sql = """
                
                select b.bcode,b.name,c.cname,a.aname,b.qty,b.price from book b
                inner join  category c on c.cid = b.cid
                inner join author a on a.aid = b.aid
                ORDER by cast(SubString(bcode,4) as UNSIGNED) DESC
                
                
                """;

        ResultSet rs = null;

        try(PreparedStatement pst = con.prepareStatement(sql)){

            List<Book> booklist  = new ArrayList<>();

            rs = pst.executeQuery();

            while (rs.next()){

                String bcode = rs.getString("bcode");
                String bname = rs.getString("name");
                int qty = rs.getInt("qty");
                int price = rs.getInt("price");
                String aid = rs.getString("aname");
                String cid = rs.getString("cname");

                Book b =new Book(bcode,bname,qty,price,aid,cid);

                booklist.add(b);

            }
            return booklist;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void update(Book b) {
        String sql = "update book set name = ?,qty = ?,price = ?,aid = ?,cid = ? where bcode =?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,b.getBookname());
            pst.setInt(2,b.getQuantity());
            pst.setInt(3,b.getPrice());
            pst.setString(4,b.getAid());
            pst.setString(5,b.getCid());
            pst.setString(6,b.getBookid());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Update Successful");

        } catch (SQLException e) {


            JOptionPane.showMessageDialog(null,"Error");


        }
    }

    @Override
    public void create(Book b) {
        String sql = "INSERT INTO book(bcode, name, qty, price, cid, aid) VALUES (?,?,?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,b.getBookid());
            pst.setString(2,b.getBookname());
            pst.setInt(3,b.getQuantity());
            pst.setInt(4,b.getPrice());
            pst.setString(5,b.getCid());
            pst.setString(6,b.getAid());


            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Book Insertion Successful");




        } catch (SQLException e) {



            throw new RuntimeException(e);


        }

    }

    @Override
    public void delete(Book book) {

    }




}
