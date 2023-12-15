import java.io.Serializable;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class SalesPoint implements Serializable {

    private String name;
    private Worker worker;
    private Storage storage;

    SalesPoint(String name, Worker worker, Storage storage){
        this.name =name;
        this.worker=worker;
        this.storage = storage;
    }
    public boolean sell(Product prodcut,Buyer buyer){
        if(prodcut.price > buyer.getBalance()) {
            System.out.println("У покупателя недостаточно средств.");
            return false;
        }
        boolean wasTaken = storage.takeProduct(prodcut);
        if (wasTaken){
            buyer.plusBalance(-prodcut.price);
        }
        return wasTaken;
    }

    public void refund(Product product,Buyer buyer){
        storage.addUnit(new StorageUnit(product,1));
        buyer.plusBalance(product.price);
    }

    public void setWorker(Worker worker){
        this.worker = worker;
    }

    public Storage getStorage(){
        return this.storage;
    }

    public String getText(){
        return ("Имя: %s\n" +
                "Работник: %s\n" +
                "Размер хранилища: %s\n" +
                "______________________").formatted(this.name,this.worker.name,this.storage.capasity);

    }
    public String getName(){return this.name;}
    public Worker getWorker(){return this.worker;}
}
