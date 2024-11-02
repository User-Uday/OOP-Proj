package com.ords;

import com.prod.Product;

public class Order {
    private int orderId;
    private int customerId;
    private Product product;
    private int quantity;
    private double totalAmount;

    public Order(int orderId, int customerId, Product product, int quantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.product = product;
        this.quantity = quantity;
        this.totalAmount = product.getPrice() * quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderId + 
                           "\nCustomer ID: " + customerId + 
                           "\nProduct: " + product.getName() + 
                           "\nQuantity: " + quantity + 
                           "\nTotal Amount: $" + totalAmount);
    }
}
