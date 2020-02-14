package com.arobs.manager;

import com.arobs.domain.*;
import com.arobs.repository.ItemRepository;
import com.arobs.repository.ProductRepository;
import com.arobs.repository.SaleRepository;
import com.arobs.repository.UserRepository;

import java.util.List;

public class SaleManager {
    private SaleRepository saleRepository = new SaleRepository();
    private ItemRepository itemRepository = new ItemRepository();
    private UserRepository userRepository = new UserRepository();
    private ProductRepository productRepository = new ProductRepository();

    public User getAllUsers(String username) {
        List<User> users = userRepository.getAll();

        User userInsert = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {

                userInsert = user;
                break;
            }
            System.out.println(user.toString()+username);
        }
        System.out.println("Final metoda");
        return userInsert;
    }

    public void insertSaleForUser(String username, String productName, int buyQuantity) {
        System.out.println("RECEIVED: " + username + productName + buyQuantity);
        User userInsert = this.getAllUsers(username);
        System.out.println(userInsert.toString());
        List<Product> products = productRepository.getAll();
        Product productInsert = null;
        for (Product product : products
        ) {
            if (product.getName().equals(productName)) {
                productInsert = product;
                break;
            }
        }
        System.out.println(productInsert.toString());
        List<Item> items = itemRepository.getAll();
        Item itemInsert = null;
        for (Item item : items
        ) {
            if (item.getProductID() == productInsert.getID()) {
                itemInsert = item;
                break;
            }
        }
        System.out.println(itemInsert.toString());
        System.out.println("Before insert");
        System.out.println(userInsert.getID() + " " + itemInsert.getID() + " " + buyQuantity);
        Sale saleInsert = new Sale(1,userInsert.getID(), itemInsert.getID(), buyQuantity);
        System.out.println(saleInsert.toString());
        saleRepository.insertSale(saleInsert);
        System.out.println("HERE");
    }
}
