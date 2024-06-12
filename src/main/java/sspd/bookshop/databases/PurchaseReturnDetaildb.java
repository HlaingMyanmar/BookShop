package sspd.bookshop.databases;

import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.PurchaseReturn;
import sspd.bookshop.models.PurchaseReturnDetail;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PurchaseReturnDetaildb  extends PurchaseReturndb  {

    Connection con = DatabaseConnector.getInstance().getConn();

    public List<PurchaseReturn> getdetailList() {

        return List.of();


    }


    public void update(PurchaseReturnDetail purchaseReturnDetail) {


        String sql = "UPDATE `purchasereturndetails` SET `rid`=?,`bcode`=?,`qty`=?,`amount`=?,`returnReason`=? WHERE `rdid`=?";

    }


    public void insert(PurchaseReturnDetail purchaseReturnDetail) {

        String sql = "INSERT INTO `purchasereturndetails`(`rdid`, `rid`, `bcode`, `qty`, `amount`, `returnReason`) VALUES (?,?,?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,purchaseReturnDetail.getRdid());
            pst.setInt(2,purchaseReturnDetail.getRid());
            pst.setString(3,purchaseReturnDetail.getBcode());
            pst.setInt(4,purchaseReturnDetail.getQty());
            pst.setInt(5,purchaseReturnDetail.getAmount());
            pst.setString(6,purchaseReturnDetail.getReturnReason());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Return Item Insert Successful!");




        } catch (SQLException e) {


            throw new RuntimeException(e);
        }

    }


    public void delete(PurchaseReturnDetail purchaseReturnDetail) {



    }
}
