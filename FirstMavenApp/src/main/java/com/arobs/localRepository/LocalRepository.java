package com.arobs.localRepository;

import com.arobs.domain.Product;
import com.arobs.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalRepository {
    List<User> users=new ArrayList<>();
    List<Product> products=new ArrayList<>();
    Map<String, List<Product>> productsForUser=new HashMap<>();

    public LocalRepository() {
        users.add(new User("ana","ana"));
        users.add(new User("kate","kate"));
        users.add(new User("paul","paul"));
        users.add(new User("roxi","roxi"));
        products.add(new Product("milk", 100));
        products.add(new Product("beef",50.50));
        products.add(new Product("salad",39.20));
        products.add(new Product("carrot",24.60));
        products.add(new Product("water",10));
    }

    public void addProductForUser(String username,List<Product> productList){
        productsForUser.put(username,productList);
    }
    public List<Product> getProductsForUser(String username){
        return productsForUser.get(username);
    }

    public List<User> getUsers() {
        return users;
    }

    public User findUser(User user){
        for (User currentUser:users
             ) {
            if (currentUser.equals(user)){
                return currentUser;
            }
        }
        return null;
    }

    public Product findProduct(Product product){
        for (Product currentProduct:products) {
            if(currentProduct.equals(product)){
                return currentProduct;
            }
        }
        return null;
    }

    public Product findProductByName(String product){
        for (Product currentProduct:products) {
            if(currentProduct.getName().equals(product)){
                return currentProduct;
            }
        }
        return null;
    }

    public User updateUser(User user){
        for (int i=0;i<users.size();i++){
            if(users.get(i).getUsername().equals(user.getUsername())){
                users.set(i,user);
                return user;
            }
        }
        return null;
    }

    public List<Product> getProducts() {
        return products;
    }
}
