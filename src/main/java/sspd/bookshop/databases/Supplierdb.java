package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Supplier;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Supplierdb implements DataAccessObject<Supplier> {

    Connection con = DatabaseConnector.getInstance().getConn();

    @Override
    public List<Supplier> getList() {


        String sql = "SELECT * FROM supplier ORDER by cast(SubString(suid,4) as UNSIGNED) DESC";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            List<Supplier> supplierList =  new ArrayList<>();

            while(rs.next()){

                String suid = rs.getString("suid");
                String suname = rs.getString("suname");
                String suphone = rs.getString("suphone");
                String suaddress = rs.getString("suaddress");

                Supplier supplier = new Supplier(suid,suname,suphone,suaddress);

                supplierList.add(supplier);

            }

            return  supplierList;

        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }

    @Override
    public void update(Supplier supplier) {

        String sql = "UPDATE supplier SET suname=?,suphone=?,suaddress=? WHERE suid=?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,supplier.getS_name());
            pst.setString(2,supplier.getS_phone());
            pst.setString(3,supplier.getS_address());
            pst.setString(4,supplier.getS_id());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Update Supplier Successful");


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }

    }

    @Override
    public void create(Supplier supplier) {

        String sql ="INSERT INTO supplier(suid, suname, suphone, suaddress) VALUES (?,?,?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,supplier.getS_id());
            pst.setString(2,supplier.getS_name());
            pst.setString(3,supplier.getS_phone());
            pst.setString(4,supplier.getS_address().toString());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Insert Supplier Successful");


        } catch (SQLException e) {


            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Supplier supplier) {

    }
}
