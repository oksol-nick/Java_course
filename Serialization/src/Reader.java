import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Reader {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("./animal.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Animal savedCat = (Animal) objectInputStream.readObject();

        System.out.println(savedCat.getBreed() + "\n" + savedCat.owner.getName());
    }
}
