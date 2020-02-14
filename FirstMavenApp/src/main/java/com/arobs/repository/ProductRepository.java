package com.arobs.repository;

import com.arobs.connection.DataSource;
import com.arobs.domain.Product;
import com.arobs.domain.Sale;
import com.arobs.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public ProductRepository() {
    }

    public List<Product> getAll(){
        System.out.println("Product repo: getAll");
        List<Product> allProducts=new ArrayList<>();
//        String queryFind="select p.ID, p.name, p.price, i.quantity \n" +
//                "from products p inner join items i\n" +
//                "on p.ID=i.productID;";
        String queryFind="select * from products";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(queryFind);
             ResultSet resultSet = pst.executeQuery();
        ) {
            while (resultSet.next()) {
                int ID=resultSet.getInt(1);
                String name = resultSet.getString(2);
                Double price = resultSet.getDouble(3);
                Product product=new Product(ID,name,price);
                allProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allProducts;
    }
}
