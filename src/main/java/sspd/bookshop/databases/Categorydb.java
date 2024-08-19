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

        String sql = "SELECT * FROM category ORDER by cast(SubString(cid,4) as UNSIGNED) DESC ";

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
    public int update(Category category) {


        String sql = "UPDATE category SET cname=? WHERE cid=?";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,category.getCategory_name());
            pst.setString(2,category.getCategory_id());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Update Category Successful");


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return 0;


    }

    @Override
    public int create(Category category) {

        int i = 0 ;

        String sql = "INSERT INTO category(cid, cname) VALUES (?,?)";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,category.getCategory_id());
            pst.setString(2,category.getCategory_name());

            i = pst.executeUpdate();



        } catch (SQLException e) {


            throw new RuntimeException(e);
        }


        return i;
    }

    @Override
    public int  delete(Category category) {

        return 0;
    }
}
