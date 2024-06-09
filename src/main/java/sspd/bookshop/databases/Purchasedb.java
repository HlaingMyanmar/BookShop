package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Purchase;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Purchasedb implements DataAccessObject<Purchase> {

    Connection con = DatabaseConnector.getInstance().getConn();


    @Override
    public List<Purchase> getList() {


       String sql = "SELECT * FROM `purchase` ORDER BY cast(SubString(puid,4) as UNSIGNED) DESC";

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

    @Override
    public void update(Purchase purchase) {


        String sql = "UPDATE `purchase` SET ,`bcode`=?,`bcategory`=?,`bauthor`=?,`sid`=?,`qty`=?,`price`=? WHERE `puid`=?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {


            pst.setString(1,purchase.getBcode());
            pst.setString(2,purchase.getCid());
            pst.setString(3,purchase.getAid());
            pst.setString(4,purchase.getSid());
            pst.setInt(5,purchase.getQty());
            pst.setInt(6,purchase.getPrice());
            pst.setString(7,purchase.getPuid());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Update Successful");




        } catch (SQLException e) {



            throw new RuntimeException(e);
        }


    }

    @Override
    public void create(Purchase purchase) {


        String sql = "INSERT INTO `purchase`(`puid`, `pudate`, `bcode`, `bcategory`, `bauthor`, `sid`, `qty`, `price`) VALUES (?,?,?,?,?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,purchase.getPuid());
            pst.setDate(2,purchase.getPudate());
            pst.setString(3,purchase.getBcode());
            pst.setString(4,purchase.getCid());
            pst.setString(5,purchase.getAid());
            pst.setString(6,purchase.getSid());
            pst.setInt(7,purchase.getQty());
            pst.setInt(8,purchase.getPrice());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Insert Successful");




        } catch (SQLException e) {



            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Purchase purchase) {

    }
}
