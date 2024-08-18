package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Order;
import sspd.bookshop.models.Purchase;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Orderdb implements DataAccessObject<Order> {

    Connection con = DatabaseConnector.getInstance().getConn();

    @Override
    public List<Order> getList() {

        String sql = """
                
                SELECT
                    o.orid,
                    o.ordate,
                    o.cuname,
                    o.cuphone,
                    SUM(sa.qty * sa.price)-sa.discount AS amount
                FROM
                    cuorder o
                INNER JOIN
                    sale sa
                ON
                    o.orid = sa.orid
                GROUP BY
                    o.orid, o.ordate, o.cuname, o.cuphone
                ORDER BY
                    CAST(SUBSTRING(o.orid, 13) AS UNSIGNED) DESC;
                
    
                
                """;



        try(PreparedStatement pst =con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            List<Order> orderlist = new ArrayList<>();

            while(rs.next()){

                String orderid = rs.getString("orid");
                Date ordate  = rs.getDate("ordate");
                String cuname = rs.getString("cuname");
                String cuphone = rs.getString("cuphone");
                int oramount = rs.getInt("amount");

                Order order = new Order(orderid,ordate,cuname,cuphone,oramount);

                orderlist.add(order);

            }
              return orderlist;

        } catch (SQLException e) {



            throw new RuntimeException(e);
        }



    }

    public List<Order> getOrderList() {

        String sql = "SELECT * FROM `cuorder` WHERE 1";



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
    public int update(Order order) {

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

    return 0;


    }

    @Override
    public int create(Order order) {
        int i = 0;
        String sql = "INSERT INTO `cuorder`(`orid`, `ordate`, `cuname`, `cuphone`) VALUES (?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,order.getOrderid());
            pst.setDate(2,order.getOrderdate());
            pst.setString(3,order.getCulname());
            pst.setString(4, order.getCuphone());

            i = pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Please check Order Create Error","Notice",0);
        }

        return i;
    }

    @Override
    public int  delete(Order order) {

        return 0;

    }

    public List<Order> getYear(int year){

        String sql = """
                
                SELECT
                    o.orid,
                    o.ordate,
                    o.cuname,
                    o.cuphone,
                    SUM(sa.qty * sa.price) - sa.discount AS amount
                FROM
                    cuorder o
                INNER JOIN
                    sale sa ON o.orid = sa.orid
                WHERE
                    YEAR(o.ordate) = ?
                GROUP BY
                    o.orid, o.ordate, o.cuname, o.cuphone
                ORDER BY
                    CAST(SUBSTRING(o.orid, 13) AS UNSIGNED) DESC;
                                
                """;
        try(PreparedStatement pst =con.prepareStatement(sql)) {

            pst.setInt(1,year);

            ResultSet rs = pst.executeQuery();

            List<Order> orderlist = new ArrayList<>();

            while(rs.next()){

                String orderid = rs.getString("orid");
                Date ordate  = rs.getDate("ordate");
                String cuname = rs.getString("cuname");
                String cuphone = rs.getString("cuphone");
                int oramount = rs.getInt("amount");

                Order order = new Order(orderid,ordate,cuname,cuphone,oramount);

                orderlist.add(order);

            }
            return orderlist;

        } catch (SQLException e) {



            throw new RuntimeException(e);
        }



    }

    public List<Order> getDay(int day, int month, int year){


        String sql = """
        SELECT
            o.orid,
            o.ordate,
            o.cuname,
            o.cuphone,
            SUM(sa.qty * sa.price) - sa.discount AS amount
        FROM
            cuorder o
        INNER JOIN
            sale sa ON o.orid = sa.orid
        WHERE
            YEAR(o.ordate) = ?
            AND DAY(o.ordate) = ?
        GROUP BY
            o.orid, o.ordate, o.cuname, o.cuphone
        ORDER BY
            CAST(SUBSTRING(o.orid, 13) AS UNSIGNED) DESC;

                                
                """;
        try(PreparedStatement pst =con.prepareStatement(sql)) {

            pst.setInt(1,year);
            pst.setInt(2,day);

            ResultSet rs = pst.executeQuery();

            List<Order> orderlist = new ArrayList<>();

            while(rs.next()){

                String orderid = rs.getString("orid");
                Date ordate  = rs.getDate("ordate");
                String cuname = rs.getString("cuname");
                String cuphone = rs.getString("cuphone");
                int oramount = rs.getInt("amount");

                Order order = new Order(orderid,ordate,cuname,cuphone,oramount);

                orderlist.add(order);

            }
            return orderlist;

        } catch (SQLException e) {



            throw new RuntimeException(e);
        }


    }

    public List<Order> getMonth (int month , int year){


        String sql = """
        SELECT
            o.orid,
            o.ordate,
            o.cuname,
            o.cuphone,
            SUM(sa.qty * sa.price) - sa.discount AS amount
        FROM
            cuorder o
        INNER JOIN
            sale sa ON o.orid = sa.orid
        WHERE
            YEAR(o.ordate) = ?
            AND MONTH(o.ordate) = ?
        GROUP BY
            o.orid, o.ordate, o.cuname, o.cuphone
        ORDER BY
            CAST(SUBSTRING(o.orid, 13) AS UNSIGNED) DESC;

                                
                """;
        try(PreparedStatement pst =con.prepareStatement(sql)) {

            pst.setInt(1,year);
            pst.setInt(2,month);

            ResultSet rs = pst.executeQuery();

            List<Order> orderlist = new ArrayList<>();

            while(rs.next()){

                String orderid = rs.getString("orid");
                Date ordate  = rs.getDate("ordate");
                String cuname = rs.getString("cuname");
                String cuphone = rs.getString("cuphone");
                int oramount = rs.getInt("amount");

                Order order = new Order(orderid,ordate,cuname,cuphone,oramount);

                orderlist.add(order);

            }
            return orderlist;

        } catch (SQLException e) {



            throw new RuntimeException(e);
        }



    }

}
