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
                
                select pr.rdate,p.puid,b.name,s.suname,prd.qty,prd.amount,prd.returnReason from purchasereturndetails prd
                inner join purchasereturn pr on pr.rid = prd.rid
                inner join purchase p on p.puid = pr.puid
                LEFT join book b on b.bcode = p.bcode
                inner join supplier s on s.suid = p.sid
                ORDER BY cast(SubString(prd.rdid,5) as UNSIGNED) DESC
                 
                """;


        try(PreparedStatement pst = con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            List<PurchaseReturnDetail> purchaseReturnDetails = new ArrayList<>();

            while(rs.next()){


                Timestamp rdate = rs.getTimestamp("rdate");
                String puid = rs.getString("puid");
                String bname = rs.getString("name");
                String sname = rs.getString("suname");
                int qty = rs.getInt("qty");
                int amount = rs.getInt("amount");
                String reason = rs.getString("returnReason");

                PurchaseReturnDetail returnDetail = new PurchaseReturnDetail(puid,rdate,bname,sname, qty,amount,reason);

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
