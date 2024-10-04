package errors;

class InventoryFull extends Exception{
    
}

class InsufficientStock extends Exception{
    int availabe_stock, requested_stock;
    public InsufficientStock(int availabe_stock,int requested_stock){
        this.availabe_stock=availabe_stock;
        this.requested_stock=requested_stock;
    }

    public String toString(){
        return "Requested Stock: "+ requested_stock+"\tAvailable Stock:"+availabe_stock;
    }
}

class ProductNotFound extends Exception{
    String name;
    public ProductNotFound(String name){
        this.name= name;
    }

    public String toString(){
        return name+" not found";
    }
}

