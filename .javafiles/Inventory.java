package inventory;

import errors.*;
import static java.lang.System.out;

class Product{
//object of product represents the outline for 1 product

    public long id;
    public int stock;
    public String name;

    Product(String name, long id, int stock){
        this.id= id;
        this.name= name;
        this.stock= stock;
    }
}

public class Inventory{
//object of inventory represents the actual inventory

    private Product[] shelf;
    private int product_count;
    
    Inventory(int size){
        shelf= new Product[size];
        product_count= 0;
    }   

    //Right now we are parameterizing add and get with single products, we can do it with product array if required

    protected void add_product(Product p) throws InventoryFull{
        if(product_count>= shelf.length) 
            throw new InventoryFull();
        for(int i= 0; i< product_count; i++){
            if(shelf[i].id== p.id)
                shelf[i].stock+= p.stock;
            else
                shelf[product_count++]= new Product(p.name, p.id, p.stock);
        }
    }

    protected void get_product(Product p) throws InsufficientStock, ProductNotFound {
        boolean product_found= false;
        for(int i= 0; i< product_count; i++){
            if(shelf[i].id== p.id){
                product_found= true;
                if(shelf[i].stock< p.stock)
                    throw new InsufficientStock(shelf[i].stock, p.stock);   //override toString method
                else
                shelf[i].stock-= p.stock;
            }
        }
        if(!product_found)
            throw new ProductNotFound(p.name);  //override toString method
    }

    protected void display_inventory(){
        for(int i= 0; i< product_count; i++)
            out.println("Name: " + shelf[i].name + "\tID: " + shelf[i].id + "\tStock: " + shelf[i].stock);
    }
}