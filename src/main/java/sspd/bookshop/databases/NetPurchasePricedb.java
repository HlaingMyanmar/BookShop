package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.NetPurchaseprice;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class NetPurchasePricedb implements DataAccessObject<NetPurchaseprice> {


    Connection con = DatabaseConnector.getInstance().getConn();

    @Override
    public List<NetPurchaseprice> getList() {
        return List.of();
    }

    @Override
    public void update(NetPurchaseprice netPurchaseprice) {



    }

    @Override
    public int create(NetPurchaseprice netPurchaseprice) {

        int i = 0;
        String sql = "INSERT INTO `netprofit`(`puid`, `pudate`, `bcode`, `currency`, `currency_amount`, `amount`, `transportation`, `expense`, `qty`, `precentage`, `netprofit`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,netPurchaseprice.getPuid());
            pst.setDate(2,netPurchaseprice.getPudate());
            pst.setString(3,netPurchaseprice.getBcode());
            pst.setString(4,netPurchaseprice.getCurrency());
            pst.setDouble(5,netPurchaseprice.getCurrency_amount());
            pst.setDouble(6,netPurchaseprice.getAmount());
            pst.setInt(7,netPurchaseprice.getTran());
            pst.setInt(8,netPurchaseprice.getExpen());
            pst.setInt(9,netPurchaseprice.getQty());
            pst.setDouble(10,netPurchaseprice.getPercen());
            pst.setDouble(11,netPurchaseprice.getNetprofit());

            i = pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Please check NetPurchase Price Create Error","Notice",0);
        }

        return i;
    }

    @Override
    public void delete(NetPurchaseprice netPurchaseprice) {

    }
}
