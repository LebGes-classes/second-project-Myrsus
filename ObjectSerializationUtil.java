import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectSerializationUtil {

    // Метод для сериализации списка объектов в файл
    private static <T> void serializeToFile(List<T> objects, String filename) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(objects);
        }
    }

    // Метод для десериализации списка объектов из файла
    @SuppressWarnings("unchecked")
    public static <T> List<T> deserializeFromFile(String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (List<T>) objectIn.readObject();
        }
    }
    public static void saveSalesPoint(List<SalesPoint> salesPoints){
        try {
            ObjectSerializationUtil.serializeToFile(salesPoints,"salesPoints.ser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}