package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Sale;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Saledb implements DataAccessObject<Sale> {

    Connection con = DatabaseConnector.getInstance().getConn();


    @Override
    public List<Sale> getList()
    {






        return List.of();
    }

    @Override
    public int update(Sale sale) {

     String sql = "UPDATE `sale` SET `bcode`=?,`cid`=?,`aid`=?,`qty`=?,`price`=? WHERE `orid`=?";

     try(PreparedStatement pst = con.prepareStatement(sql)) {

         pst.setString(1, sale.getBcode());
         pst.setString(2,sale.getCcode());
         pst.setString(3,sale.getAcode());
         pst.setInt(4,sale.getQty());
         pst.setInt(5,sale.getPrice());
         pst.setString(6,sale.getOrderid());

         pst.executeUpdate();

         JOptionPane.showMessageDialog(null,"Update Successful");


     } catch (SQLException e) {
         throw new RuntimeException(e);
     }

return  0;
    }

    public void getOrderIDupdate(Sale sale) {

        String sql = """
                
                UPDATE `sale`
                SET `qty` = ?
                WHERE `orid` = ? AND `bcode` = ?;
                
                
                
                
                """;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,sale.getQty());
            pst.setString(2,sale.getOrderid());
            pst.setString(3,sale.getBcode());


            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Update Successful");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



    @Override
    public int create(Sale sale) {
        
        int i = 0;

        String sql = "INSERT INTO `sale`(`orid`, `bcode`, `cid`, `aid`, `qty`, `price`) VALUES (?,?,?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,sale.getOrderid());
            pst.setString(2,sale.getBcode());
            pst.setString(3, sale.getCcode());
            pst.setString(4, sale.getAcode());
            pst.setInt(5,sale.getQty());
            pst.setInt(6,sale.getPrice());

            i = pst.executeUpdate();



        } catch (SQLException e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Please check Sale Create Error","Notice",0);
        }

        return i;
    }

    @Override
    public int delete(Sale sale) {

        String sql = "DELETE FROM `sale` WHERE `orid` = ? AND `bcode` = ?;";

        try(PreparedStatement pst = con.prepareStatement(sql)) {


            pst.setString(1,sale.getOrderid());
            pst.setString(2,sale.getBcode());


            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Delete Successful");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;


    }

    public List<Book> findByOrderID(String orderid){



        String sql = """
                
                SELECT
                    sa.bcode,
                    sa.cid,
                    sa.aid,
                    sa.qty,
                    sa.price,
                    SUM(sa.qty * sa.price) AS amount
                FROM
                    cuorder o
                INNER JOIN
                    sale sa
                ON
                    o.orid = sa.orid
                WHERE
                    o.orid = ?
                GROUP BY
                    sa.bcode,
                    sa.cid,
                    sa.aid,
                    sa.qty,
                    sa.price
                ORDER BY
                    CAST(SUBSTRING(o.orid, 4) AS UNSIGNED) DESC;
                                
             
                """;


        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,orderid);


            ResultSet rs = pst.executeQuery();

            List<Book> saleList = new ArrayList<>();


            while (rs.next()){


                String bcode = rs.getString("bcode");
                String cid = rs.getString("cid");
                String aid = rs.getString("aid");
                int qty = rs.getInt("qty");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");

                Book book = new Book(bcode,qty,price,aid,cid,amount);

                saleList.add(book);

            }



            return saleList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Sale> findByOrderCode(String orderid){



        String sql = """
                
                SELECT				
                                                                					o.orid,
                                                                                    o.ordate,
                                                                                    o.cuname,
                                                                                    o.cuphone,
                                                                                    sa.bcode,
                                                                                    bo.name,
                                                                                    sa.cid,
                                                                                    sa.aid,
                                                                                    sa.qty,
                                                                                    sa.price,
                                                                                    SUM(sa.qty * sa.price) AS amount
                                                                                FROM
                                                                                    cuorder o
                                                                                INNER JOIN
                                                                                    sale sa
                                                                                ON
                                                                                    o.orid = sa.orid
                                                                                INNER JOIN
                                                                                    book bo
                                                                                ON
                                                                                    bo.bcode = sa.bcode
                                                                                WHERE
                                                                                    o.orid = ?
                                                                                GROUP BY
                                                                                    o.orid,
                                                                                    o.ordate,
                                                                                    o.cuname,
                                                                                    o.cuphone,
                                                                                    sa.bcode,
                                                                                    sa.cid,
                                                                                    sa.aid,
                                                                                    sa.qty,
                                                                                    sa.price
                                                                                ORDER BY
                                                                                    CAST(SUBSTRING(o.orid, 4) AS UNSIGNED) DESC;
                                
             
                """;


        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,orderid);


            ResultSet rs = pst.executeQuery();

            List<Sale> saleList = new ArrayList<>();


            while (rs.next()){

                String orid = rs.getString("orid");
                Date odate = rs.getDate("ordate");
                String cuname = rs.getString("cuname");
                String cuphone = rs.getString("cuphone");
                String bcode = rs.getString("bcode");
                String bname = rs.getString("name");
                String cid = rs.getString("cid");
                String aid = rs.getString("aid");
                int qty = rs.getInt("qty");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");

                Sale sale = new Sale(orderid,odate,cuname,cuphone,bcode,bname,cid,aid,qty,price,amount);

                saleList.add(sale);

            }



            return saleList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public  int getQty(String orderid , String bookcode){


        String sql = """
                SELECT `qty`
                FROM `sale`
                WHERE `orid` = ? AND `bcode` = ?;
                
                """;


        try(PreparedStatement pst = con.prepareStatement(sql)) {



            pst.setString(1,orderid);
            pst.setString(2,bookcode);
            ResultSet rs = pst.executeQuery();

            int qty = 0;

            while(rs.next()){

                qty = rs.getInt("qty");

            }


            return qty;




        } catch (SQLException e) {



            throw new RuntimeException(e);
        }
    }
}
