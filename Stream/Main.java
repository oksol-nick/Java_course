import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        List<String> myStringList = Arrays.asList("Мария Швецова", "Денис Кораблёв",
                "Мария Швецова", "Ольга Цветкова", "Фёдор Курочкин", "Ольга Цветкова");

        char myLetter = 'М';

        List<Integer> myIntegerList = Arrays.asList(1, 2, 56, 37, 544, 0, 241, 15, 37, 54);

        System.out.println("Уникальные элементы списка:\n" + deleteRepeatedElements(myStringList) + "\n");

        System.out.println("Количество строк в списке, начинающихся с буквы " + myLetter + ": "
                + stringsCounterStartsFromMyLetter(myStringList, myLetter) + "\n");

        System.out.println("Второе максимальное значение равно: " + findeSecondMaxElement(myIntegerList ));
    }

    public static List<String> deleteRepeatedElements(List<String> listString) {

        return listString.stream().distinct().toList();
    }

    public static Long stringsCounterStartsFromMyLetter(List<String> listString, char myLetter) {

        return listString.stream().filter(n -> n.charAt(0) == myLetter).count();
    }

    public static Integer findeSecondMaxElement(List<Integer> listInteger) {

        return listInteger.stream().sorted(Comparator.reverseOrder()).filter(n -> n % 2 != 0).findFirst().orElse( 0);
    }
}