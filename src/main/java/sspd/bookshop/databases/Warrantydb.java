package sspd.bookshop.databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sspd.bookshop.DAO.DatabaseConnector;
import sspd.bookshop.models.Warranty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Warrantydb {


    Connection con = DatabaseConnector.getInstance().getConn();

    public List<Warranty> getWarrantyList(){

        String sql = "SELECT * FROM `warranty` WHERE 1";

        try(PreparedStatement pst = con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            List<Warranty> warrantyList = new ArrayList<>();

            while(rs.next()){

                int id = rs.getInt("id");
                String name = rs.getString("name");

                Warranty warranty = new Warranty(id,name);



            warrantyList.add(warranty);

            }

            return  warrantyList;

        } catch (SQLException e) {


            throw new RuntimeException(e);
        }




    }




}
