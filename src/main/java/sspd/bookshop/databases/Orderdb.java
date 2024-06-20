package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Orderdb implements DataAccessObject<Order> {

    Connection con = DatabaseConnector.getConn();
    @Override
    public List<Order> getList() {

        String sql = "SELECT * FROM `cuorder` ORDER BY cast(Substring(orid,4)AS unsigned) DESC";



        try(PreparedStatement pst =con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            List<Order> orderlist = new ArrayList<>();

            while(rs.next()){

                String orderid = rs.getString("orid");
                Date ordate  = rs.getDate("ordate");
                String cuname = rs.getString("cuname");
                String cuphone = rs.getString("cuphone");

                Order order = new Order(orderid,ordate,cuname,cuphone);

                orderlist.add(order);

            }
              return orderlist;

        } catch (SQLException e) {



            throw new RuntimeException(e);
        }



    }

    @Override
    public void update(Order order) {

    String sql = "UPDATE `cuorder` SET `ordate`=?,`cuname`=?,`cuphone`=? WHERE `orid`=?";

    try(PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setDate(1,order.getOrderdate());
        pst.setString(2,order.getCulname());
        pst.setString(3,order.getCuphone());
        pst.setString(4,order.getOrderid());

        pst.executeUpdate();


    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


    }

    @Override
    public void create(Order order) {

        String sql = "INSERT INTO `cuorder`(`orid`, `ordate`, `cuname`, `cuphone`) VALUES (?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,order.getOrderid());
            pst.setDate(2,order.getOrderdate());
            pst.setString(3,order.getCulname());
            pst.setString(4, order.getCuphone());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Order order) {

    }
}
