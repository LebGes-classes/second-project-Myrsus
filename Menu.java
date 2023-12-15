import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
class Menu {
    static private final Scanner scanner = new Scanner(System.in);
    static private List<SalesPoint> salesPoints;

    static {
        try {
            salesPoints = ObjectSerializationUtil.deserializeFromFile("salesPoints.ser");
        } catch (Exception e) {
            System.out.println(e);
            salesPoints = new ArrayList<SalesPoint>();
        }
    }

    static private void clear(){
        for (int i = 0; i < 50; i++) {
            System.out.println("\n");
        }
    }
    static public void showMainMenu() {
        clear();
       ObjectSerializationUtil.saveSalesPoint(salesPoints);
        System.out.println("Выберите пункт меню:");
        System.out.println("1) Точки продажи");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showSalesPointsMenu();
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    static private void showSalesPointsMenu() {
        System.out.println("Точки продажи:");
        for (int i = 0;i < salesPoints.size();i++) {
            System.out.println(
                    String.format("%s) %s",i+1,salesPoints.get(i).getName())
            );
        }
        System.out.println("+) Добавить точку");
        System.out.println("0) Назад");

        String choice = scanner.next();
        scanner.nextLine();
        switch (choice) {
            case "+":
                addSalesPoint();
                break;
            case "0":
                showMainMenu();
                break;
            default:
                int index = Integer.parseInt(choice) - 1;
                manageSalesPoint(salesPoints.get(index));
        }
    }

    static private void addSalesPoint() {
        // логика добавления точки продажи
        System.out.print("Введите название точки: ");
        String name = scanner.nextLine();
        System.out.print("Введите имя сотрудника: ");
        String workerName = scanner.nextLine();
        System.out.print("Введите размер хранилища: ");
        int capacity = scanner.nextInt();
        Storage storage = new Storage(capacity);
        Worker worker = new Worker(workerName);
        SalesPoint salesPoint = new SalesPoint(name,worker,storage);
        salesPoints.add(salesPoint);
    }

    static private void manageSalesPoint(SalesPoint salesPoint) {
        ObjectSerializationUtil.saveSalesPoint(salesPoints);
        // логика управления
        System.out.println(salesPoint.getText());
        System.out.println("Выберите пункт меню:");
        System.out.println("1) Показать Склад");
        System.out.println("2) Поменять работника");
        System.out.println("3) Продать товар");
        System.out.println("4) Добавить товар");
        System.out.println("5) Удалить");
        System.out.println("6) Назад");

        String choice = scanner.next();
        scanner.nextLine();
        switch (choice) {
            case "1":
                storageManage(salesPoint);
                break;
            case "2":
                changeWorker(salesPoint);
                break;
            case "3":
                sellProduct(salesPoint);
                break;
            case "4":
                addProduct(salesPoint);
                break;
            case "5":
                removeSalesPoint(salesPoint);
                break;
            case "6":
                showMainMenu();
            default:
                int index = Integer.parseInt(choice) - 1;
                manageSalesPoint(salesPoints.get(index));
        }
    }
    static private void sellProduct(SalesPoint salesPoint){
        System.out.println("Введите ID продукта со склада: ");
        Integer id = scanner.nextInt();
        StorageUnit unit = salesPoint.getStorage().getUnitById(id);
        if (unit == null){
            System.out.println("Не удалось найти товара с таким ID");
            manageSalesPoint(salesPoint);
            return;
        }
        Buyer buyer = new Buyer("Костя Костылев");
        buyer.plusBalance(1000000000); // тут Костя становится миллионером
        salesPoint.sell(unit.getProduct(),buyer);
        System.out.println("Товар продан!");
        manageSalesPoint(salesPoint);
    }
    static private void addProduct(SalesPoint salesPoint){
        System.out.println("Введите имя продукта:");
        String name = scanner.nextLine();
        System.out.println("Введите цену продукта:");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите кол-во продукта:");
        int count = scanner.nextInt();
        scanner.nextLine();
        StorageUnit unit = new StorageUnit(new Product(name,price), count);
        if (salesPoint.getStorage().addUnit(unit)) {
            System.out.println("Товар добавлен!");
        }
        manageSalesPoint(salesPoint);
    }
    static private void changeWorker(SalesPoint salesPoint){
        clear();
        System.out.println(String.format("Текущий работник: %s",salesPoint.getWorker().name));
        System.out.println("Введите новоое имя работника: ");
        String name = scanner.nextLine();
        salesPoint.setWorker(new Worker(name));
        System.out.println("Успешно!");
        manageSalesPoint(salesPoint);
    }
    static private void storageManage(SalesPoint salesPoint){
        salesPoint.getStorage().print();
        manageSalesPoint(salesPoint);
    }
    static private void removeSalesPoint(SalesPoint point) {
        // логика удаления точки продажи
        salesPoints.remove(point);
        showMainMenu();
    }
    static private List<SalesPoint> getSalesPoints(){
        return salesPoints;
    }
}
