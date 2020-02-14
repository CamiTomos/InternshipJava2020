package com.arobs.domain;

public class Product {
    private int ID;
    private String name;
    private double price;

    public Product(String name, double price) {
        this(null,name,price);
    }

    public Product(Integer ID, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return ((Product)obj).getName().equals(this.name);
    }
}
