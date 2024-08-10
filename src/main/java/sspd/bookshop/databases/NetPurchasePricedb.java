package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.NetPurchaseprice;

import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class NetPurchasePricedb implements DataAccessObject<NetPurchaseprice> {


    Connection con = DatabaseConnector.getInstance().getConn();

    @Override
    public List<NetPurchaseprice> getList() {


    String sql = "SELECT * FROM `netprofit`";

    try(PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        List<NetPurchaseprice> netPurchaseprices = new LinkedList<>();

        while (rs.next()){

            String id = rs.getString("puid");
            Date  date = rs.getDate("pudate");
            String bcode = rs.getString("bcode");
            String currency = rs.getString("currency");
            double currency_ammount = rs.getDouble("currency_amount");
            double amount = rs.getDouble("amount");
            int tran = rs.getInt("transportation");
            int expen = rs.getInt("expense");
            int qty = rs.getInt("qty");
            double pre = rs.getDouble("precentage");
            double netprofit = rs.getDouble("netprofit");

            NetPurchaseprice n = new NetPurchaseprice(id,date,bcode,currency,currency_ammount,amount,tran,expen,qty,pre,netprofit);

            netPurchaseprices.add(n);


        }

        return netPurchaseprices;



    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


    }

    @Override
    public int update(NetPurchaseprice netPurchaseprice) {

        int i = 0;



        String sql = "UPDATE `netprofit` SET `currency`= ?,`currency_amount`=?,`amount`=?,`transportation`=?,`expense`=?,`qty`=?,`precentage`=?,`netprofit`=? WHERE puid = ? AND pudate = ? AND bcode =?";


        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,netPurchaseprice.getCurrency());
            pst.setDouble(2,netPurchaseprice.getCurrency_amount());
            pst.setDouble(3,netPurchaseprice.getAmount());
            pst.setInt(4,netPurchaseprice.getTran());
            pst.setInt(5,netPurchaseprice.getExpen());
            pst.setInt(6,netPurchaseprice.getQty());
            pst.setDouble(7,netPurchaseprice.getPercen());
            pst.setDouble(8,netPurchaseprice.getNetprofit());
            pst.setString(9,netPurchaseprice.getPuid());
            pst.setDate(10,netPurchaseprice.getPudate());
            pst.setString(11,netPurchaseprice.getBcode());

             i = pst.executeUpdate();





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return i;



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
    public int delete(NetPurchaseprice netPurchaseprice) {

        return 0;

    }
}
