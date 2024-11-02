import com.inven.Inventory;
import com.Errors.InsufficientStockException;
import com.Errors.ProductNotFoundException;
import com.Errors.InventoryFullException;
import com.prod.Product;

class Manager {
    private Inventory inventory;

    public Manager(Inventory inventory) {
        this.inventory = inventory;
    }

    public void modifyInventory(Product p) {
        try {
            inventory.addProduct(p);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayInventory() {
        inventory.displayInventory();
    }
}

class Customer {
    private int customerId;
    private String name;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public void viewAvailableProducts(Inventory inventory) {
        System.out.println("Customer " + name + " is viewing products:");
        inventory.displayInventory();
    }

    public void purchaseProduct(Inventory inventory, int productId, int quantity) {
        try {
            Product product = inventory.getProduct(productId);
            if (product.getStock() < quantity) {
                throw new InsufficientStockException("Insufficient stock for product ID " + productId);
            }
            // Simulating stock reduction
            product.setStock(product.getStock() - quantity);
            System.out.println(name + " purchased " + quantity + " of " + product.getName());
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientStockException e) {
            System.out.println(e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Inventory sharedInventory = new Inventory(2); // Reduced capacity for demonstration

        // Manager adds products to inventory
        try {
            sharedInventory.addProduct(new Product("Product A", 101, 50, 10.0, "good"));
            sharedInventory.addProduct(new Product("Product B", 102, 30, 15.0, "cargo"));
            // Trying to add a third product to trigger InventoryFullException
            sharedInventory.addProduct(new Product("Product C", 103, 8, 5.0, "good"));
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
        }

        // Create customers
        Customer customer1 = new Customer(1, "Alice");
        Customer customer2 = new Customer(2, "Bob");
        Customer customer3 = new Customer(3, "Charlie");
        Customer customer4 = new Customer(4, "Diana");

        // Customers view available products
        customer1.viewAvailableProducts(sharedInventory);
        customer2.viewAvailableProducts(sharedInventory);
        customer3.viewAvailableProducts(sharedInventory);
        customer4.viewAvailableProducts(sharedInventory);

        // Demonstrating ProductNotFoundException
        try {
            // Attempting to retrieve a product that doesn't exist
            sharedInventory.getProduct(999); // Invalid product ID
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Customers attempting to purchase products
        customer1.purchaseProduct(sharedInventory, 101, 5); // Successful purchase
        customer2.purchaseProduct(sharedInventory, 101, 60); // Insufficient stock
        customer3.purchaseProduct(sharedInventory, 104, 1); // Invalid product ID

        // Launch JavaFX statistics application
        InventoryApp.inventory = sharedInventory;  // Set shared inventory in InventoryApp
        InventoryApp.launch(InventoryApp.class, args);
    }
}
