package com.arobs.domain;

public class Item {
    private int ID;
    private int productID;
    private int quantity;

    public Item(Integer ID, int productID, int quantity) {
        this.ID = ID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public Item(int productID, int quantity) {
        this(null,productID,quantity);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ID=" + ID +
                ", productID=" + productID +
                ", quantity=" + quantity +
                '}';
    }
}
