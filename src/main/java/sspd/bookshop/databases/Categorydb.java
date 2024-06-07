package sspd.bookshop.databases;

import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Category;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Categorydb implements DataAccessObject<Category> {

    Connection con = DatabaseConnector.getInstance().getConn();

    @Override
    public List<Category> getList() {

        String sql = "SELECT * FROM category ORDER by cid desc ";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            List<Category> categoryList = new ArrayList<>();

            while (rs.next()){

                String cid = rs.getString("cid");
                String cname= rs.getString("cname");

                Category category = new Category(cid,cname);

                categoryList.add(category);

            }

        return categoryList;


        } catch (SQLException e) {


            throw new RuntimeException(e);


        }


    }

    @Override
    public void update(Category category) {


        String sql = "UPDATE category SET cname=? WHERE cid=?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,category.getCategory_name());
            pst.setString(2,category.getCategory_id());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Update Category Successful");


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }


    }

    @Override
    public void create(Category category) {

        String sql = "INSERT INTO category(cid, cname) VALUES (?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,category.getCategory_id());
            pst.setString(2,category.getCategory_name());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Add Category Successful");



        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(Category category) {

    }
}
