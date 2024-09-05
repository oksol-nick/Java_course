import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Owner owner = new Owner("Денис Кораблёв", "+7(912)815-35-27", "г.Санкт-Петербург, ул.Зюзина 15, кв.37");
        Animal cat = new Animal("Кошки", "Дворняга", 12, owner );

        File file = new File("./animal.txt");

        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(cat);
        objectOutputStream.close();
    }

}