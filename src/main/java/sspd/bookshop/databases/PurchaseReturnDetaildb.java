package sspd.bookshop.databases;

import sspd.bookshop.DAO.DatabaseConnector;

import sspd.bookshop.models.Book;
import sspd.bookshop.models.PurchaseReturnDetail;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseReturnDetaildb  extends PurchaseReturndb  {

    Connection con = DatabaseConnector.getInstance().getConn();

    public List<PurchaseReturnDetail> getdetailList() {

        String sql = """
                
                    SELECT prd.rdate, pr.puid, b.name, prd.qty, prd.amount, prd.returnReason
                                         FROM purchasereturndetails prd
                                         INNER JOIN purchasereturn pr ON pr.rdate = prd.rdate
                                         INNER JOIN book b ON b.bcode = prd.bcode
                                         ORDER BY prd.rdate DESC;
                 
                """;


        try(PreparedStatement pst = con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            List<PurchaseReturnDetail> purchaseReturnDetails = new ArrayList<>();

            while(rs.next()){


                Timestamp rdate = rs.getTimestamp("rdate");
                String puid = rs.getString("puid");
                String bname = rs.getString("name");

                int qty = rs.getInt("qty");
                int amount = rs.getInt("amount");
                String reason = rs.getString("returnReason");

                PurchaseReturnDetail returnDetail = new PurchaseReturnDetail(puid,rdate,bname, qty,amount,reason);



                purchaseReturnDetails.add(returnDetail);



            }

            return purchaseReturnDetails;



        } catch (SQLException e) {



            throw new RuntimeException(e);
        }




    }


    public void update(PurchaseReturnDetail purchaseReturnDetail) {



        String sql = "UPDATE `purchasereturndetails` SET `bcode`=?,`qty`=?,`amount`=?,`returnReason`=? WHERE `rdate`=?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {


            pst.setString(1,purchaseReturnDetail.getBcode());
            pst.setInt(2,purchaseReturnDetail.getQty());
            pst.setInt(3,purchaseReturnDetail.getAmount());
            pst.setString(4,purchaseReturnDetail.getReturnReason());
            pst.setTimestamp(5,purchaseReturnDetail.getRdate());

            JOptionPane.showMessageDialog(null,"Return Item Update Successful");


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }

    }


    public void insert(PurchaseReturnDetail purchaseReturnDetail) {



        String sql = "INSERT INTO `purchasereturndetails`(`rdate`, `bcode`, `qty`, `amount`, `returnReason`) VALUES (?,?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setTimestamp(1,purchaseReturnDetail.getRdate());
            pst.setString(2,purchaseReturnDetail.getBcode());
            pst.setInt(3,purchaseReturnDetail.getQty());
            pst.setInt(4,purchaseReturnDetail.getAmount());
            pst.setString(5,purchaseReturnDetail.getReturnReason());

            pst.executeUpdate();

            //JOptionPane.showMessageDialog(null,"Return Item Insert Successful!");




        } catch (SQLException e) {


            throw new RuntimeException(e);
        }

    }


    public void delete(PurchaseReturnDetail purchaseReturnDetail) {


        String updateSql = """
        UPDATE book AS b
        JOIN purchasereturndetails AS b2 ON b.bcode = b2.bcode
        SET b.qty = b.qty + b2.qty
        WHERE b2.rdate = ?;
        """;

        String deleteDetailsSql = """
        DELETE prd FROM PurchaseReturnDetails prd
        INNER JOIN PurchaseReturn pr ON prd.rdate = pr.rdate
        WHERE prd.rdate = ?;
        """;

        String deleteReturnSql = """
        DELETE FROM PurchaseReturn
        WHERE rdate = ?;
        """;

        try {
            con.setAutoCommit(false); // Start transaction


            try (PreparedStatement updatePst = con.prepareStatement(updateSql)) {
                updatePst.setTimestamp(1, purchaseReturnDetail.getRdate());
                updatePst.executeUpdate();
            }


            try (PreparedStatement deleteDetailsPst = con.prepareStatement(deleteDetailsSql)) {
                deleteDetailsPst.setTimestamp(1, purchaseReturnDetail.getRdate());
                deleteDetailsPst.executeUpdate();
            }


            try (PreparedStatement deleteReturnPst = con.prepareStatement(deleteReturnSql)) {
                deleteReturnPst.setTimestamp(1, purchaseReturnDetail.getRdate());
                deleteReturnPst.executeUpdate();
            }

            con.commit();

            JOptionPane.showMessageDialog(null, "Delete Successful");

        } catch (SQLException e) {

            try {

                con.rollback(); //


            } catch (SQLException rollbackException) {

                throw new RuntimeException("Failed to rollback transaction", rollbackException);

            }

            throw new RuntimeException("Failed to delete purchase return detail", e);

        } finally {

            try {

                con.setAutoCommit(true);


            } catch (SQLException setAutoCommitException) {

                throw new RuntimeException("Failed to restore auto-commit mode", setAutoCommitException);

            }
        }


    }

    public void subBookQty(Book book){

        String sql = " UPDATE book SET qty = qty - ?  WHERE bcode = ?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,book.getQuantity());
            pst.setString(2,book.getBookid());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Successful Sub Book");





        } catch (SQLException e) {



            throw new RuntimeException(e);
        }


    }
}
