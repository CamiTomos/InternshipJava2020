package com.arobs.domain;

public class Sale {
    private int ID;
    private int userID;
    private int itemID;
    private int quantity;

    public Sale(Integer ID, int userID, int itemID, int quantity) {
        this.ID = ID;
        this.userID = userID;
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public Sale(int userID, int itemID, int quantity) {
        this(null,userID,itemID,quantity);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "ID=" + ID +
                ", userID=" + userID +
                ", itemID=" + itemID +
                ", quantity=" + quantity +
                '}';
    }
}
