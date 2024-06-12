package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.PurchaseReturn;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PurchaseReturndb implements DataAccessObject<PurchaseReturn> {

    private final Connection con = DatabaseConnector.getConn();



    @Override
    public List<PurchaseReturn> getList() {
        return List.of();
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
