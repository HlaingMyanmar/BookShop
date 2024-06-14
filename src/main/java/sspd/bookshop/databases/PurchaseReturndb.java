package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.PurchaseReturn;


import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseReturndb implements DataAccessObject<PurchaseReturn> {

    private final Connection con = DatabaseConnector.getConn();



    @Override
    public List<PurchaseReturn> getList() {

        String sql = "SELECT * FROM `purchasereturn` order by rid desc";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            List<PurchaseReturn> prList = new ArrayList<>();

            while(rs.next()){

                int code = rs.getInt("rid");
                String pid = rs.getString("puid");
                Date date = rs.getDate("rdate");

                PurchaseReturn pr = new PurchaseReturn(code,pid,date);

                prList.add(pr);


            }

            return  prList;


        } catch (SQLException e) {


            throw new RuntimeException(e);

        }



    }

    @Override
    public void update(PurchaseReturn purchaseReturn) {

        String sql = "UPDATE `purchasereturn` SET `puid`=?,`rdate`=? WHERE `rid`=?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,purchaseReturn.getPuid());
            pst.setDate(2,purchaseReturn.getRdate());
            pst.setInt(3,purchaseReturn.getRid());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"update");



        } catch (SQLException e) {


            throw new RuntimeException(e);
        }

    }

    @Override
    public void create(PurchaseReturn purchaseReturn) {

        String sql = "INSERT INTO `purchasereturn`(`rid`, `puid`, `rdate`) VALUES (?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,purchaseReturn.getRid());
            pst.setString(2,purchaseReturn.getPuid());
            pst.setDate(3,purchaseReturn.getRdate());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Save");


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(PurchaseReturn purchaseReturn) {

    }
}
