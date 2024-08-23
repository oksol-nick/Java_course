import java.util.Arrays;
import java.util.Random;
import java.lang.Long;

public class Main {
    public static void main(String[] args) {

        //int [] N = {10, 30, 50};
        int [] N = {1000, 10000, 100000}; // Три массива для тестирования

        String [][]  results = new String[10][3];
        results[0][0] =  "Метод сортировки      ";
        results[1][0] = results[2][0] = results[3][0] = "Arrays.sort()         ";
        results[4][0] = results[5][0] = results[6][0] ="Пузырьковая сортировка";
        results[7][0] = results[8][0] = results[9][0] ="Сортировка вставками  ";
        results[0][1] = "N";
        results[0][2] = "Время, нс";



        for (int i = 0; i < N.length; i++) {
            int[] myArray = makeArray(N[i]);

            long startJava = System.nanoTime();
            int[] javaSorted = javaSort(myArray);
            long endJava = System.nanoTime();
            long elapsedJava = endJava - startJava;

            long startBubble = System.nanoTime();
            int[] sortedBubbleArray = bubbleSort(myArray);
            long endBubble = System.nanoTime();
            long elapsedBubble = endBubble - startBubble;

            long startPaste = System.nanoTime();
            int[] sortedPasteArray = pasteSort(myArray);
            long endPaste = System.nanoTime();
            long elapsedPaste = endPaste - startPaste;


            if (N[i] <= 50) { // Большие массивы не выводим
                System.out.println("        Исходный массив: " + Arrays.toString(myArray));
                System.out.println("          Arrays.sort(): " + Arrays.toString(javaSorted));
                System.out.println(" Пузырьковая сортировка: " + Arrays.toString(sortedBubbleArray));
                System.out.println("   Сортировка вставками: " + Arrays.toString(sortedPasteArray));
                //System.out.println("        Исходный массив: " + Arrays.toString(myArray));
                System.out.println("===================================================================");
            }

            // Собираем данные N и время в один массив
            results[i+1][1] = Integer.toString(N[i]);
            results[i+4][1] = Integer.toString(N[i]);
            results[i+7][1] = Integer.toString(N[i]);

            results[i+1][2] = Long.toString(elapsedJava);
            results[i+4][2] = Long.toString(elapsedBubble);
            results[i+7][2] = Long.toString(elapsedPaste);
        }

        for (int k = 0; k < 10; k++) {
            System.out.println(Arrays.deepToString(results[k]));
        }

    }

    // Массив случайных числе
    public static int[] makeArray(int N) {
        int [] randomArray = new int[N];
        Random rand = new Random();

        for(int i = 0; i < randomArray.length; i++) {
            randomArray[i] = rand.nextInt(100);
        }
        return randomArray;
    }

    // Методы сортировки
    // В каждом методе сортировки создаем копию массива randomArray, чтобы избежать его измерения

    public static int[] bubbleSort(int[] randomArray) {
        int [] sortedArray = new int[randomArray.length];

        System.arraycopy(randomArray, 0, sortedArray, 0, sortedArray.length);

        for (int i = 0; i <sortedArray.length - 1; i++ ){
            for (int j = i+1; j < sortedArray.length; j++) {
                if (sortedArray[i] > sortedArray[j]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[i];
                    sortedArray[i] = temp;
                }
            }
        }
        return sortedArray;
    }

    public static int[] pasteSort(int[] randomArray) {
        int [] sortedArray = new int[randomArray.length];
        System.arraycopy(randomArray, 0, sortedArray, 0, sortedArray.length);

        for (int i = 1; i <sortedArray.length; i++ ){
            for (int j = 0; j < sortedArray.length; j++) {
                if (sortedArray[i] < sortedArray[j]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[i];
                    sortedArray[i] = temp;
                }
            }
        }
        return sortedArray;
    }

    public static int[] javaSort(int[] randomArray) {
        int [] sortedArray = new int[randomArray.length];
        System.arraycopy(randomArray, 0, sortedArray, 0, sortedArray.length);

        Arrays.sort(sortedArray);

        return sortedArray;
    }
    
}
