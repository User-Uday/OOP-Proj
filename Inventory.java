package com.inven;

import com.Errors.InsufficientStockException;
import com.Errors.ProductNotFoundException;
import com.Errors.InventoryFullException;
import com.prod.Product;

public class Inventory {
    private Product[] shelf;
    private int productCount;

    public Inventory(int maxProducts) {
        shelf = new Product[maxProducts];
        productCount = 0;
    }

    public synchronized void addProduct(Product p) throws InventoryFullException {
        for (int i = 0; i < productCount; i++) {
            if (shelf[i].getId() == p.getId()) {
                shelf[i].setStock(shelf[i].getStock() + p.getStock());
                return;
            }
        }
        if (productCount < shelf.length) {
            shelf[productCount] = p;
            productCount++;
        } else {
            throw new InventoryFullException("Inventory full. Cannot add more products.");
        }
    }

    public synchronized Product getProduct(int id) throws ProductNotFoundException {
        for (int i = 0; i < productCount; i++) {
            if (shelf[i].getId() == id) {
                return shelf[i];
            }
        }
        throw new ProductNotFoundException("Product with ID " + id + " not found.");
    }

    public synchronized void displayInventory() {
        if (productCount == 0) {
            System.out.println("No products in the inventory.");
        } else {
            System.out.println("Available Products:");
            for (int i = 0; i < productCount; i++) {
                shelf[i].displayProduct();
            }
        }
    }

    public synchronized int totalStockByType(String type) {
        int total = 0;
        for (int i = 0; i < productCount; i++) {
            if (shelf[i].getType().equalsIgnoreCase(type)) {
                total += shelf[i].getStock();
            }
        }
        return total;
    }
}