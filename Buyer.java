import java.io.Serializable;

public class Buyer extends Human implements Serializable {
    private Integer balance;

    public Buyer(String name){
        super(name);
        this.balance = 0;
    }

    public void plusBalance(Integer plusValue){
        this.balance += plusValue;
    }

    public Integer getBalance(){
        return this.balance;
    }
}
