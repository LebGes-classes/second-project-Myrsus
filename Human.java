import java.io.Serializable;
import java.util.Random;

abstract public class Human implements Serializable {
    private final Random random = new Random();
    String name;
    Integer id;

    Human(String name){
        this.name = name;
        this.id = random.nextInt();
    }
}