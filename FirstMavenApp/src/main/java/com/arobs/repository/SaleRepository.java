package com.arobs.repository;

import com.arobs.connection.DataSource;
import com.arobs.domain.Product;
import com.arobs.domain.Sale;
import com.arobs.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleRepository {
    public List<Sale> getAll(){
        System.out.println("Sale repo: getAll");
        List<Sale> allSales=new ArrayList<>();
//        String queryFind="select p.ID, p.name, p.price, i.quantity \n" +
//                "from products p inner join items i\n" +
//                "on p.ID=i.productID;";
        String queryFind="select * from sales";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(queryFind);
             ResultSet rs = pst.executeQuery();
        ) {
            while (rs.next()) {
                int ID=rs.getInt(1);
                int userID=rs.getInt(2);
                int itemID=rs.getInt(3);
                int quantity=rs.getInt(4);
                Sale sale=new Sale(ID,userID,itemID,quantity);
                allSales.add(sale);
                System.out.println(sale.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSales;
    }

    public void insertSale(Sale sale){
        System.out.println("Sale repo: insert");
        System.out.println("INSERT");
        String queryFind="insert into sales (userID, itemID, quantity) value (?,?,?)";
        try (Connection con = DataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(queryFind);
        ) {
            preparedStatement.setInt(1,sale.getUserID());
            preparedStatement.setInt(2,sale.getItemID());
            preparedStatement.setInt(3,sale.getQuantity());
            int count=preparedStatement.executeUpdate();
            System.out.println("Rows affected: "+count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
