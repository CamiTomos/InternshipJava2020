package com.arobs.domain;

import java.util.Objects;

public class ProductDTO {
    private int itemID;
    private String name;
    private double price;
    private int quantity;

    public ProductDTO(int itemID, String name, double price, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return itemID == that.itemID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "itemID=" + itemID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
