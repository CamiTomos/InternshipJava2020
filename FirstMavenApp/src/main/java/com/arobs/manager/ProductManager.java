package com.arobs.manager;

import com.arobs.domain.*;
import com.arobs.repository.ItemRepository;
import com.arobs.repository.ProductRepository;
import com.arobs.repository.SaleRepository;
import com.arobs.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductManager {
    private ItemRepository itemRepository = new ItemRepository();
    private UserRepository userRepository=new UserRepository();
    private ProductRepository productRepository = new ProductRepository();
    private SaleRepository saleRepository=new SaleRepository();

    public List<ProductDTO> findAllProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> allProducts = productRepository.getAll();
        List<Item> allItems = itemRepository.getAll();
        for (Product product : allProducts
        ) {
            for (Item item : allItems
            ) {
                if (product.getID() == item.getProductID()) {
                    ProductDTO productDTO = new ProductDTO(item.getID(), product.getName(), product.getPrice(), item.getQuantity());
                    productDTOList.add(productDTO);
//                    System.out.println(productDTO.toString());
                }
            }
        }
        return productDTOList;
    }

    //    public List<Product> getAll() {
//        return productRepository.getAll();
//    }
    public User getAllUsers(String username) {
        List<User> users = userRepository.getAll();

        User userInsert = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {

                userInsert = user;
                break;
            }
            System.out.println(user.toString() + username);
        }
        System.out.println("Final metoda");
        return userInsert;
    }


    public Set<ProductDTO> getAllProductsForUser(String username) {
        Set<ProductDTO> productDTOList=new HashSet<>();
        User user=this.getAllUsers(username);
        int userID=user.getID();
        List<Sale> sales=saleRepository.getAll();
        List<Sale> userSales=new ArrayList<>();
        for (Sale currentSale:sales
             ) {
            if(currentSale.getUserID()==userID){
                userSales.add(currentSale);
            }
        }

        List<Item> items=itemRepository.getAll();
        List<Item> userItems=new ArrayList<>();
        for (Sale currentSale:userSales
             ) {
            for (Item currentItem:items
                 ) {
                if(currentItem.getID()==currentSale.getItemID()){
                    userItems.add(currentItem);
                }
            }
        }
        List<Product> products=productRepository.getAll();
        List<Product> userProducts=new ArrayList<>();

        for (Item currentItem:items
             ) {
            for (Product currentProduct:products
                 ) {
                if(currentItem.getProductID()==currentProduct.getID()){
                    userProducts.add(currentProduct);
                }
            }
        }
        for (Sale currentSale:userSales
             ) {
            for (Item currentItem:userItems
                 ) {
                for (Product product:userProducts
                     ) {
                    if(currentSale.getItemID()==currentItem.getID() && currentItem.getProductID()==product.getID()){
                        ProductDTO productDTO=new ProductDTO(currentItem.getID(),product.getName(),product.getPrice(),currentSale.getQuantity());
                        productDTOList.add(productDTO);
                    }
                }
            }
        }
        return productDTOList;
    }
}
