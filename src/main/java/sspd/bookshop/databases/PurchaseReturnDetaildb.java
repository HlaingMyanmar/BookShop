package sspd.bookshop.databases;

import sspd.bookshop.DAO.DatabaseConnector;

import sspd.bookshop.models.PurchaseReturnDetail;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseReturnDetaildb  extends PurchaseReturndb  {

    Connection con = DatabaseConnector.getInstance().getConn();

    public List<PurchaseReturnDetail> getdetailList() {

        String sql = """
                
                select pr.rid,prd.rdid,pr.rdate,p.puid,b.name,s.suname,prd.qty,prd.amount,prd.returnReason from purchasereturndetails prd
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

                int  rid = rs.getInt("rid");
                String rdid = rs.getString("rdid");
                Date rdate = rs.getDate("rdate");
                String puid = rs.getString("puid");
                String bname = rs.getString("name");
                String sname = rs.getString("suname");
                int qty = rs.getInt("qty");
                int amount = rs.getInt("amount");
                String reason = rs.getString("returnReason");

                PurchaseReturnDetail returnDetail = new PurchaseReturnDetail(rid,puid,rdate,rdid,bname,sname, qty,amount,reason);

                purchaseReturnDetails.add(returnDetail);



            }

            return purchaseReturnDetails;



        } catch (SQLException e) {



            throw new RuntimeException(e);
        }




    }


    public void update(PurchaseReturnDetail purchaseReturnDetail) {


        String sql = "UPDATE `purchasereturndetails` SET `rid`=?,`bcode`=?,`qty`=?,`amount`=?,`returnReason`=? WHERE `rdid`=?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,purchaseReturnDetail.getRid());
            pst.setString(2,purchaseReturnDetail.getBcode());
            pst.setInt(3,purchaseReturnDetail.getQty());
            pst.setInt(4,purchaseReturnDetail.getAmount());
            pst.setString(5,purchaseReturnDetail.getReturnReason());

            JOptionPane.showMessageDialog(null,"Return Item Update Successful");


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }

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
