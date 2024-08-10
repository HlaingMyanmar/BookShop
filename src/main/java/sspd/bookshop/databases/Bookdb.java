package sspd.bookshop.databases;

import sspd.bookshop.Alerts.AlertBox;
import sspd.bookshop.DAO.DataAccessObject;

import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Book;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bookdb implements DataAccessObject<Book> {

    private  Connection con = DatabaseConnector.getInstance().getConn();

    @Override
    public List<Book> getList() {

       String sql = """
                
               SELECT b.bcode, b.name, c.cname, a.aname, b.qty, b.price
               FROM book b 
               INNER JOIN category c ON c.cid = b.cid
               INNER JOIN author a ON a.aid = b.aid
               ORDER BY b.qty ASC, CAST(SUBSTRING(b.bcode, 11) AS UNSIGNED) DESC;
                 
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


                Book b =new Book(bcode,bname,qty,price,aid,cid,(qty*price));

                booklist.add(b);

            }
            return booklist;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Book> getList2() {

        String sql = """
                
               SELECT b.bcode, b.name, c.cname, a.aname, b.qty, b.price
               FROM book b 
               INNER JOIN category c ON c.cid = b.cid
               INNER JOIN author a ON a.aid = b.aid
               ORDER BY CAST(SUBSTRING(b.bcode, 13) AS UNSIGNED) DESC;
                 
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


                Book b =new Book(bcode,bname,qty,price,aid,cid,(qty*price));

                booklist.add(b);

            }
            return booklist;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public List<Book> getListgenerateID() {

        String sql = """
                
              SELECT * FROM `book` WHERE 1
                 
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
                String aid = rs.getString("aid");
                String cid = rs.getString("cid");


                Book b =new Book(bcode,bname,qty,price,aid,cid,(qty*price));

                booklist.add(b);

            }
            return booklist;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public int  update(Book b) {
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

            e.printStackTrace();


            JOptionPane.showMessageDialog(null,"Error");


        }

        return  0 ;
    }

    @Override
    public int create(Book b) {


        String sql = "INSERT INTO book(bcode, name, qty, price, cid, aid) VALUES (?,?,?,?,?,?)";

        int i = 0;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,b.getBookid());
            pst.setString(2,b.getBookname());
            pst.setInt(3,b.getQuantity());
            pst.setInt(4,b.getPrice());
            pst.setString(5,b.getCid());
            pst.setString(6,b.getAid());


            i =  pst.executeUpdate();





        }
        
    catch (SQLIntegrityConstraintViolationException e) {
        AlertBox.showError("သတိပေးချက်", "သင့်၏ ပစ္စည်းအိုင်ဒီ ထပ်နေပါသည်။");
    } 
    catch (SQLException e) {
     
        AlertBox.showError("အမှား", "ပျက်စီးမှုများဖြစ်ပွားခဲ့ပါသည်။");
    }

        return i;  
    }

    @Override
    public int delete(Book book) {

        return 0;

    }

    public List<Book> getFindByCategory(String category){

        String sql = "SELECT * FROM `book` WHERE cid=?ORDER by cast(SubString(bcode,4) as UNSIGNED) asc";

        ResultSet rs = null;

        try(PreparedStatement pst = con.prepareStatement(sql)){

            pst.setString(1,category);

            List<Book> booklist  = new ArrayList<>();

            rs = pst.executeQuery();

            while (rs.next()){

                String bcode = rs.getString("bcode");
                String bname = rs.getString("name");
                int qty = rs.getInt("qty");
                int price = rs.getInt("price");
                String aid = rs.getString("aid");
                String cid = rs.getString("cid");

                Book b =new Book(bcode,bname,qty,price,aid,cid);

                booklist.add(b);

            }
            return booklist;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public int  sumQty(Book book){
        int i =0;


        String sql = " UPDATE book SET qty = qty + ?  WHERE bcode = ?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,book.getQuantity());
            pst.setString(2,book.getBookid());
            i = pst.executeUpdate();

            System.out.println("sumQty"+i);





        } catch (SQLException e) {



            throw new RuntimeException(e);
        }
        return  i;


    }

    public int subQty(Book book){

        int i =0;

        String sql = " UPDATE book SET qty = qty - ? WHERE bcode = ?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,book.getQuantity());
            pst.setString(2,book.getBookid());
          i=   pst.executeUpdate();

            System.out.println("subQty"+i);



        } catch (SQLException e) {



            JOptionPane.showMessageDialog(null,"Please check Book SubQty Create Error","Notice",0);
        }

        return i ;


    }




}
