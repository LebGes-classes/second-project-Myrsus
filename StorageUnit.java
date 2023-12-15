import java.io.Serializable;
import java.util.Random;

public class StorageUnit implements Serializable {
    private static final Random random = new Random();
    private final Integer id;
    private final Product product;
    private Integer count;
    public StorageUnit(Product product, Integer count) {
        this.id = random.nextInt();
        this.product = product;
        this.count = count;
    }
    public void plusCount(Integer countToPlus){
        this.count = this.count + countToPlus;
    }
    public Integer getCount(){
        return this.count;
    }
    public Product getProduct(){return this.product;}

    public Integer getId(){return this.id;}
    public String getText(){
        return "ID: %s | %s | %s руб./шт. | %s штук \n".formatted(this.id,product.name,product.price,count);
    }
}
