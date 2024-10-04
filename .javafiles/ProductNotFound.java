package errors;

public class ProductNotFound extends Exception{
    String name;
    public ProductNotFound(String name){
        this.name= name;
    }

    public String toString(){
        return "Requested Product "+ name+ " not found";
    }
}
