import java.io.Serializable;

public class Product implements Serializable {
    String name;
    Integer price;

    public Product(String name, Integer price){
        this.name = name;
        this.price = price;
    }
    public boolean equals(Product product){
        System.out.println(this.name);
        System.out.println(product.name);
        System.out.println(this.name.equals(product.name));
        return this.name.equals(product.name) && this.price.equals(product.price);
    }

}
