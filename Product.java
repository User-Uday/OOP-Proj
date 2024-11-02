package com.prod;
public class Product {
    private String name;
    private int id;
    private int stock;
    private double price;
    private String type; // "good" or "cargo"

    public Product(String name, int id, int stock, double price, String type) {
        this.name = name;
        this.id = id;
        this.stock = stock;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void displayProduct() {
        System.out.println("Name: " + name + "\tID: " + id + "\tStock: " + stock + "\tPrice: $" + price + "\tType: " + type);
    }
}