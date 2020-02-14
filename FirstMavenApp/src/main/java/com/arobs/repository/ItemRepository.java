package com.arobs.repository;

import com.arobs.connection.DataSource;
import com.arobs.domain.Item;
import com.arobs.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    public List<Item> getAll(){
        System.out.println("Item repo: getAll");
        List<Item> allItems=new ArrayList<>();
//        String queryFind="select p.ID, p.name, p.price, i.quantity \n" +
//                "from products p inner join items i\n" +
//                "on p.ID=i.productID;";
        String queryFind="select * from items";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(queryFind);
             ResultSet resultSet = pst.executeQuery();
        ) {

            while (resultSet.next()) {
                int ID=resultSet.getInt(1);
                int productID = resultSet.getInt(2);
                int quantity = resultSet.getInt(3);
                Item item=new Item(ID,productID,quantity);
                allItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allItems;
    }
}
