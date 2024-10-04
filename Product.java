package products;

import errors.*;
import java.util.Scanner;
import static java.lang.System.out;

public class Product{
//object of product represnts the outline for 1 product

    public long id;
    public int stock;
    public String name;

    Product(String name, long id, int stock){
        this.id= id;
        this.name= name;
        this.stock= stock;
    }

    public void display_product(){
        out.println("Name: " + name + "\tID: " + id + "\tStock: " + stock);
    }

    //This should be all for product class, we can either make the data memebers protected and change them directly or make them private and use setter methods
}

public class Inventory{
//object of inventory represents the actual inventory

    private Product[] inventory;
    private int product_count;
    
    Inventory(int size){
        inventory= new Product[size];
        product_count= 0;
    }   

    //Right now we are parameterizing add and get with single products, we can do it with product array if required

    protected void add_product(Product p) throws InventoryFull{
        for(int i= 0; i< product_count; i++){
            if(inventory[i].id== p.id){
                inventory[i].stock+= p.stock;
            }
            else{
                inventory[product_count++]= new Product(p.name, p.id, p.stock);
            }
        }
    }

    protected void get_product(Product p) throws InsufficientStock, ProductNotFound {
        boolean product_found= false;
        for(int i= 0; i< product_count; i++){
            if(inventory[i].id== p.id){
                product_found= true;
                if(inventory[i].stock< p.stock)
                    throw new InsufficientStock(inventory[i].stock, p.stock);   //override toString method
                else
                    inventory[i].stock-= p.stock;
            }
        }
        if(!product_found)
            throw new ProductNotFound(p.name);  //override toString method
    }

    protected void display_inventory(){
        for(int i= 0; i< product_count; i++)
            inventory[i].display_product();
    }
}