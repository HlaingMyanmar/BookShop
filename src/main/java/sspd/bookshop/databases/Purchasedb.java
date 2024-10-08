package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Book;
import sspd.bookshop.models.Purchase;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Purchasedb implements DataAccessObject<Purchase> {

    Connection con = DatabaseConnector.getInstance().getConn();


    @Override
    public List<Purchase> getList() {


        String sql = """
                
                SELECT p.pudate,s.suname,b.name,c.cname,a.aname,p.puid,p.qty,p.price
                FROM purchase p
                inner join book b on b.bcode=p.bcode
                inner join category c on c.cid = p.bcategory
                inner join author a on a.aid = p.bauthor
                inner join supplier s on s.suid = p.sid
                ORDER BY cast(SubString(p.puid,13) as UNSIGNED) DESC
         
                """;

       try(PreparedStatement pst = con.prepareStatement(sql)) {

           ResultSet rs = pst.executeQuery();

           List<Purchase> pList = new ArrayList<>();

           while(rs.next()){

                String puid = rs.getString("puid");
                Date pudate = rs.getDate("pudate");
                String bcode = rs.getString("name");
                String bcategory = rs.getString("cname");
                String bauthor = rs.getString("aname");
                String sid = rs.getString("suname");
                int qty = rs.getInt("qty");
                int price  = rs.getInt("price");

                Purchase purchase = new Purchase(puid,pudate,bcode,bcategory,bauthor,sid,qty,price,(qty*price));

                pList.add(purchase);


           }

           return pList;


       } catch (SQLException e) {


           throw new RuntimeException(e);
       }


    }
    public List<Purchase> getListGenerateID() {


        String sql = """
                
               SELECT * FROM `purchase` WHERE 1
         
                """;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            List<Purchase> pList = new ArrayList<>();

            while(rs.next()){

                String puid = rs.getString("puid");
                Date pudate = rs.getDate("pudate");
                String bcode = rs.getString("bcode");
                String bcategory = rs.getString("bcategory");
                String bauthor = rs.getString("bauthor");
                String sid = rs.getString("sid");
                int qty = rs.getInt("qty");
                int price  = rs.getInt("price");

                Purchase purchase = new Purchase(puid,pudate,bcode,bcategory,bauthor,sid,qty,price,(qty*price));

                pList.add(purchase);


            }

            return pList;


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }

    public List<Purchase> getList2() {


        String sql = """
                
                WITH RankedPurchases AS (
                SELECT
                p.pudate,
                s.suname,
                b.name,
                c.cname,
                a.aname,
                p.puid,
                p.qty,
                p.price,
                ROW_NUMBER() OVER (PARTITION BY p.puid ORDER BY p.pudate ASC) as row_num
                FROM purchase p
                INNER JOIN book b ON b.bcode = p.bcode
                INNER JOIN category c ON c.cid = p.bcategory
                INNER JOIN author a ON a.aid = p.bauthor
                INNER JOIN supplier s ON s.suid = p.sid)
                SELECT
                pudate,
                suname,
                name,
                cname,
                aname,
                puid,
                qty,
                price
                FROM RankedPurchases
                WHERE row_num = 1
                ORDER BY CAST(SUBSTRING(puid, 4) AS UNSIGNED) DESC;
                                                                   
         
                """;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            List<Purchase> pList = new ArrayList<>();

            while(rs.next()){

                String puid = rs.getString("puid");
                Date pudate = rs.getDate("pudate");
                String bcode = rs.getString("name");
                String bcategory = rs.getString("cname");
                String bauthor = rs.getString("aname");
                String sid = rs.getString("suname");
                int qty = rs.getInt("qty");
                int price  = rs.getInt("price");

                Purchase purchase = new Purchase(puid,pudate,bcode,bcategory,bauthor,sid,qty,price,(qty*price));

                pList.add(purchase);


            }

            return pList;


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }

    @Override
    public int update(Purchase purchase) {

        int i = 0;


        String sql = "UPDATE `purchase` SET ,`bcode`=?,`bcategory`=?,`bauthor`=?,`sid`=?,`qty`=?,`price`=? WHERE `puid`=?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {


            pst.setString(1,purchase.getBcode());
            pst.setString(2,purchase.getCid());
            pst.setString(3,purchase.getAid());
            pst.setString(4,purchase.getSid());
            pst.setInt(5,purchase.getQty());
            pst.setInt(6,purchase.getPrice());
            pst.setString(7,purchase.getPuid());

           i =  pst.executeUpdate();






        } catch (SQLException e) {



            throw new RuntimeException(e);
        }
return i;

    }
    public int getupdate(String purchaseid , Date purchaseDate , String bookcode,double price) {

        int i = 0;

        String sql = "UPDATE `purchase` SET `price`=? WHERE `puid`=? AND pudate=? AND bcode = ?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {


            pst.setDouble(1,price);
            pst.setString(2,purchaseid);
            pst.setDate(3,purchaseDate);
            pst.setString(4,bookcode);

            i =  pst.executeUpdate();



        } catch (SQLException e) {



            throw new RuntimeException(e);
        }
        return i;

    }
    public int updateQty(Purchase purchase) {

        int i  =0;


        String sql =  "UPDATE `purchase` SET `qty`=? WHERE `puid`=? AND `bcode`=?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {



            pst.setInt(1,purchase.getQty());
            pst.setString(2,purchase.getPuid());
            pst.setString(3,purchase.getBcode());

           i = pst.executeUpdate();
            System.out.println("pQty"+i);






        } catch (SQLException e) {



            throw new RuntimeException(e);
        }

        return i;


    }

    @Override
    public int create(Purchase purchase) {

        int i = 0;



        String sql = "INSERT INTO `purchase`(`puid`, `pudate`, `bcode`, `bcategory`, `bauthor`, `sid`, `qty`, `price`,`remark`) VALUES (?,?,?,?,?,?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,purchase.getPuid());
            pst.setDate(2,purchase.getPudate());
            pst.setString(3,purchase.getBcode());
            pst.setString(4,purchase.getCid());
            pst.setString(5,purchase.getAid());
            pst.setString(6,purchase.getSid());
            pst.setInt(7,purchase.getQty());
            pst.setInt(8,purchase.getPrice());
            pst.setString(9,purchase.getRemark());


            i =  pst.executeUpdate();






        } catch (SQLException e) {

            e.printStackTrace();
            e.getErrorCode();



            JOptionPane.showMessageDialog(null,"Insert Purchase DB Error");
        }

        return i;
    }

    @Override
    public int delete(Purchase purchase) {

        return 0;

    }

    public void createBook(Book b) {


        String sql = "INSERT INTO book(bcode, name, qty, price, cid, aid) VALUES (?,?,?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,b.getBookid());
            pst.setString(2,b.getBookname());
            pst.setInt(3,b.getQuantity());
            pst.setInt(4,b.getPrice());
            pst.setString(5,b.getCid());
            pst.setString(6,b.getAid());


            pst.executeUpdate();








        } catch (SQLException e) {



            throw new RuntimeException(e);


        }

    }

    public void sumBookQty(Book book){

        String sql = " UPDATE book SET qty = qty + ?, price = ?  WHERE bcode = ?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,book.getQuantity());
            pst.setInt(2,book.getPrice());
            pst.setString(3,book.getBookid());
            pst.executeUpdate();





        } catch (SQLException e) {



            throw new RuntimeException(e);
        }


    }

    public List<Purchase> getFindId(String id){

        String sql = "SELECT bcode ,price FROM `purchase` WHERE puid=?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,id);

            ResultSet rs = pst.executeQuery();

            List<Purchase> purchaseList = new ArrayList<>();

            while (rs.next()){

                String bcode = rs.getString("bcode");

                int price = rs.getInt("price");

                Purchase p = new Purchase(price,bcode);

                purchaseList.add(p);


            }

            return purchaseList;




        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }



    public List<Purchase> getMonth (int month , int year){


        String sql = """
                
                WITH RankedPurchases AS (
                    SELECT
                        p.pudate,\s
                        s.suname, \s
                        b.name, \s
                        c.cname, \s
                        a.aname,
                        p.puid,\s
                        p.qty,\s
                        p.price,
                        ROW_NUMBER() OVER (PARTITION BY p.puid ORDER BY p.pudate ASC) AS row_num
                    FROM purchase p
                    INNER JOIN book b ON b.bcode = p.bcode
                    INNER JOIN category c ON c.cid = p.bcategory
                    INNER JOIN author a ON a.aid = p.bauthor
                    INNER JOIN supplier s ON s.suid = p.sid
                )
                SELECT
                    pudate,
                    suname,
                    name,
                    cname,
                    aname,
                    puid,
                    qty,
                    price
                FROM RankedPurchases
                WHERE row_num = 1
                    AND MONTH(pudate) = ?  -- Replace ? with the desired month
                    AND YEAR(pudate) = ?  -- Replace ? with the desired year
                ORDER BY CAST(SUBSTRING(puid, 4) AS UNSIGNED) DESC;
                """;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(2,year);
            pst.setInt(1,month);

            ResultSet rs = pst.executeQuery();

            List<Purchase> pList = new ArrayList<>();

            while(rs.next()){

                String puid = rs.getString("puid");
                Date pudate = rs.getDate("pudate");
                String bcode = rs.getString("name");
                String bcategory = rs.getString("cname");
                String bauthor = rs.getString("aname");
                String sid = rs.getString("suname");
                int qty = rs.getInt("qty");
                int price  = rs.getInt("price");

                Purchase purchase = new Purchase(puid,pudate,bcode,bcategory,bauthor,sid,qty,price,(qty*price));

                pList.add(purchase);


            }

            return pList;


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }

    public List<Purchase> getMonthTotal (int month , int year){


        String sql = """
                
 
                SELECT p.pudate,\s
                       s.suname,\s
                       b.name,\s
                       c.cname,\s
                       a.aname,\s
                       p.puid,\s
                       p.qty,\s
                       p.price\s
                FROM purchase p
                INNER JOIN book b ON b.bcode = p.bcode
                INNER JOIN category c ON c.cid = p.bcategory
                INNER JOIN author a ON a.aid = p.bauthor
                INNER JOIN supplier s ON s.suid = p.sid
                WHERE YEAR(p.pudate) = ?
                  AND MONTH(p.pudate) =?
                ORDER BY CAST(SUBSTRING(p.puid, 4) AS UNSIGNED) DESC;              """;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(2,year);
            pst.setInt(1,month);

            ResultSet rs = pst.executeQuery();

            List<Purchase> pList = new ArrayList<>();

            while(rs.next()){

                String puid = rs.getString("puid");
                Date pudate = rs.getDate("pudate");
                String bcode = rs.getString("name");
                String bcategory = rs.getString("cname");
                String bauthor = rs.getString("aname");
                String sid = rs.getString("suname");
                int qty = rs.getInt("qty");
                int price  = rs.getInt("price");

                Purchase purchase = new Purchase(puid,pudate,bcode,bcategory,bauthor,sid,qty,price,(qty*price));

                pList.add(purchase);


            }

            return pList;


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }

    public List<Purchase> getYear( int year){


        String sql = """
                WITH RankedPurchases AS (
                      SELECT
                          p.pudate,
                          s.suname,
                          b.name,
                          c.cname,
                          a.aname,
                          p.puid,
                          p.qty,
                          p.price,
                          ROW_NUMBER() OVER (PARTITION BY p.puid ORDER BY p.pudate ASC) AS row_num
                      FROM purchase p
                      INNER JOIN book b ON b.bcode = p.bcode
                      INNER JOIN category c ON c.cid = p.bcategory
                      INNER JOIN author a ON a.aid = p.bauthor
                      INNER JOIN supplier s ON s.suid = p.sid
                  )
                  SELECT
                      pudate,
                      suname,
                      name,
                      cname,
                      aname,
                      puid,
                      qty,
                      price
                  FROM RankedPurchases
                  WHERE row_num = 1
                      AND YEAR(pudate) = ? \s
                  ORDER BY CAST(SUBSTRING(puid, 4) AS UNSIGNED) DESC;
                                                                          
                """;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,year);


            ResultSet rs = pst.executeQuery();

            List<Purchase> pList = new ArrayList<>();

            while(rs.next()){

                String puid = rs.getString("puid");
                Date pudate = rs.getDate("pudate");
                String bcode = rs.getString("name");
                String bcategory = rs.getString("cname");
                String bauthor = rs.getString("aname");
                String sid = rs.getString("suname");
                int qty = rs.getInt("qty");
                int price  = rs.getInt("price");

                Purchase purchase = new Purchase(puid,pudate,bcode,bcategory,bauthor,sid,qty,price,(qty*price));

                pList.add(purchase);


            }

            return pList;


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }

    public List<Purchase> getYearTotal( int year){


        String sql = """
                SELECT p.pudate,\s
                       s.suname,\s
                       b.name,\s
                       c.cname,\s
                       a.aname,\s
                       p.puid,\s
                       p.qty,\s
                       p.price\s
                FROM purchase p
                INNER JOIN book b ON b.bcode = p.bcode
                INNER JOIN category c ON c.cid = p.bcategory
                INNER JOIN author a ON a.aid = p.bauthor
                INNER JOIN supplier s ON s.suid = p.sid
                WHERE YEAR(p.pudate) = ?
                ORDER BY CAST(SUBSTRING(p.puid, 4) AS UNSIGNED) DESC;
                                                                          
                """;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,year);


            ResultSet rs = pst.executeQuery();

            List<Purchase> pList = new ArrayList<>();

            while(rs.next()){

                String puid = rs.getString("puid");
                Date pudate = rs.getDate("pudate");
                String bcode = rs.getString("name");
                String bcategory = rs.getString("cname");
                String bauthor = rs.getString("aname");
                String sid = rs.getString("suname");
                int qty = rs.getInt("qty");
                int price  = rs.getInt("price");

                Purchase purchase = new Purchase(puid,pudate,bcode,bcategory,bauthor,sid,qty,price,(qty*price));

                pList.add(purchase);


            }

            return pList;


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }

    public List<Purchase> getDay( int day,int month,int year){


        String sql = """
                
                WITH RankedPurchases AS (
                    SELECT
                        p.pudate,
                        s.suname,
                        b.name,
                        c.cname,
                        a.aname,
                        p.puid,
                        p.qty,
                        p.price,
                        ROW_NUMBER() OVER (PARTITION BY p.puid ORDER BY p.pudate ASC) AS row_num
                    FROM purchase p
                    INNER JOIN book b ON b.bcode = p.bcode
                    INNER JOIN category c ON c.cid = p.bcategory
                    INNER JOIN author a ON a.aid = p.bauthor
                    INNER JOIN supplier s ON s.suid = p.sid
                )
                SELECT
                    pudate,
                    suname,
                    name,
                    cname,
                    aname,
                    puid,
                    qty,
                    price
                FROM RankedPurchases
                WHERE row_num = 1
                    AND DAY(pudate) = ?
                    AND MONTH(pudate) = ?
                    AND YEAR(pudate) = ?
                ORDER BY CAST(SUBSTRING(puid, 4) AS UNSIGNED) DESC;
                                
                                                                     
                                                                     
         
                """;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,day);
            pst.setInt(2,month);
            pst.setInt(3,year);


            ResultSet rs = pst.executeQuery();

            List<Purchase> pList = new ArrayList<>();

            while(rs.next()){

                String puid = rs.getString("puid");
                Date pudate = rs.getDate("pudate");
                String bcode = rs.getString("name");
                String bcategory = rs.getString("cname");
                String bauthor = rs.getString("aname");
                String sid = rs.getString("suname");
                int qty = rs.getInt("qty");
                int price  = rs.getInt("price");

                Purchase purchase = new Purchase(puid,pudate,bcode,bcategory,bauthor,sid,qty,price,(qty*price));

                pList.add(purchase);


            }

            return pList;


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }

    public List<Purchase> getDayTotal( int day,int month,int year){


        String sql = """
                SELECT p.pudate,\s
                       s.suname,\s
                       b.name,\s
                       c.cname,\s
                       a.aname,\s
                       p.puid,\s
                       p.qty,\s
                       p.price\s
                FROM purchase p
                INNER JOIN book b ON b.bcode = p.bcode
                INNER JOIN category c ON c.cid = p.bcategory
                INNER JOIN author a ON a.aid = p.bauthor
                INNER JOIN supplier s ON s.suid = p.sid
                AND DAY(pudate) = ?
                AND MONTH(pudate) = ?
                AND YEAR(pudate) = ?
                ORDER BY CAST(SUBSTRING(p.puid, 4) AS UNSIGNED) DESC;        
                """;

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,day);
            pst.setInt(2,month);
            pst.setInt(3,year);


            ResultSet rs = pst.executeQuery();

            List<Purchase> pList = new ArrayList<>();

            while(rs.next()){

                String puid = rs.getString("puid");
                Date pudate = rs.getDate("pudate");
                String bcode = rs.getString("name");
                String bcategory = rs.getString("cname");
                String bauthor = rs.getString("aname");
                String sid = rs.getString("suname");
                int qty = rs.getInt("qty");
                int price  = rs.getInt("price");

                Purchase purchase = new Purchase(puid,pudate,bcode,bcategory,bauthor,sid,qty,price,(qty*price));

                pList.add(purchase);


            }

            return pList;


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }







}
