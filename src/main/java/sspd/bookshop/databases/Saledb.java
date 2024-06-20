package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Saledb implements DataAccessObject<Sale> {

    Connection con = DatabaseConnector.getConn();


    @Override
    public List<Sale> getList()
    {
        return List.of();
    }

    @Override
    public void update(Sale sale) {

     String sql = "UPDATE `sale` SET `bcode`=?,`cid`=?,`aid`=?,`qty`=?,`price`=? WHERE `orid`=?";

     try(PreparedStatement pst = con.prepareStatement(sql)) {

         pst.setString(1, sale.getBcode());
         pst.setString(2,sale.getCcode());
         pst.setString(3,sale.getAcode());
         pst.setInt(4,sale.getQty());
         pst.setInt(5,sale.getPrice());
         pst.setString(6,sale.getOrderid());

         pst.executeUpdate();


     } catch (SQLException e) {
         throw new RuntimeException(e);
     }


    }

    @Override
    public void create(Sale sale) {

        String sql = "INSERT INTO `sale`(`orid`, `bcode`, `cid`, `aid`, `qty`, `price`) VALUES (?,?,?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,sale.getOrderid());
            pst.setString(2,sale.getBcode());
            pst.setString(3, sale.getCcode());
            pst.setString(4, sale.getAcode());
            pst.setInt(5,sale.getQty());
            pst.setInt(6,sale.getPrice());

            pst.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Sale sale) {

    }
}
