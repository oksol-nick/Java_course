import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> myArray = new ArrayList<>(Arrays.asList(1, 2, 3, 5));

        averageWindow(myArray, 2);

    }

    public static void averageWindow (ArrayList<Integer> myArray, int k) {
        LinkedList<Float> subList = new LinkedList<>();

        for(int i = 0; i <= myArray.size() - k; i++) {
            float itemsSum = 0;
            for(int j = 0; j < k; j++ ) {
                itemsSum = itemsSum + myArray.get(j + i);
            }
            subList.add(itemsSum/2);
        }
        System.out.println(subList);
    }
}
