package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.PurchaseReturn;


import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseReturndb implements DataAccessObject<PurchaseReturn> {

    private final Connection con = DatabaseConnector.getInstance().getConn();



    @Override
    public List<PurchaseReturn> getList() {

        String sql = "SELECT * FROM `purchasereturn` order by rdate desc";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            List<PurchaseReturn> prList = new ArrayList<>();

            while(rs.next()){


                String pid = rs.getString("puid");
                Timestamp date = rs.getTimestamp("rdate");

                PurchaseReturn pr = new PurchaseReturn(pid,date);

                prList.add(pr);


            }

            return  prList;


        } catch (SQLException e) {


            throw new RuntimeException(e);

        }



    }

    @Override
    public void update(PurchaseReturn purchaseReturn) {

        String sql = "UPDATE `purchasereturn` SET `puid`=? WHERE `rdate`=?";


        try(PreparedStatement pst = con.prepareStatement(sql)) {


            pst.setTimestamp(1,purchaseReturn.getRdate());


            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"update");



        } catch (SQLException e) {


            throw new RuntimeException(e);
        }

    }

    @Override
    public void create(PurchaseReturn purchaseReturn) {

        String sql = "INSERT INTO `purchasereturn`( `rdate`, `puid`) VALUES (?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setTimestamp(1,purchaseReturn.getRdate());
            pst.setString(2,purchaseReturn.getPuid());


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
