import java.io.Serializable;
import java.util.ArrayList;

public class Storage implements Serializable {
    private final ArrayList<StorageUnit> storage;
    Integer capasity;

    // Конструктор с начальной вместимостью
    public Storage(int capacity) {
        this.storage = new ArrayList<>();
        this.capasity = capacity;
    }

    public Boolean addUnit(StorageUnit unit){
        if (capasity < storage.size() + unit.getCount()){
            System.out.println("Хранилище полностью заполнено.");
            return false;
        }
        if (!changeUnitCount(unit.getProduct(),unit.getCount())){
            storage.add(unit);
        }
        return true;
    }
    // Метод для уменьшения количества продукта на складе
    public boolean takeProduct(Product product){
        // Проходим по каждой единице хранения в списке
        for (StorageUnit currentUnit : storage) {
            // Проверяем, содержит ли текущая единица хранения искомый продукт
            if (currentUnit.getProduct() == product) {
                // Уменьшаем количество товара на один
                currentUnit.plusCount(-1);
                // При достижении нуля удаляем единицу хранения
                if (currentUnit.getCount() <= 0) removeUnit(currentUnit);
                // После обработки выходим из метода
                return true;
            }
        }
        return false; // Если продукт не найден, метод завершится c фалс
    }

    private void removeUnit(StorageUnit unit){
        storage.remove(unit);
    }
    private boolean changeUnitCount(Product product,Integer count){
        for (StorageUnit  currentUnit : this.storage) {
            if (currentUnit == null) {
                continue;
            }
            //Если добавили плюс к товару то true
            if (currentUnit.getProduct().equals(product)){
                currentUnit.plusCount(count);
                return true;
            }
        }
        // Не добавили - false
        return false;
    }
    public void print(){
        StringBuilder text = new StringBuilder("________________\nХранилище:\n");
        for (StorageUnit unit :
                this.storage) {
            text.append(unit.getText());
        }
        text.append("\n__________________");
        System.out.println(text);
    }
    public StorageUnit getUnitById(Integer id){
        for (StorageUnit unit :
                storage) {
            if (unit.getId().equals(id)){
                return unit;
            }
        }
        return null;
    }
}
