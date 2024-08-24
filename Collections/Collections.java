import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Collections {
    public static void main(String[] args) {
        changeHM();
        oneMillionNumbers();
        deleteDuplicates();
    }

    public static void changeHM() {

        HashMap<String, Integer> stringInteger = new HashMap<>();
        HashMap<Integer, String> integerString = new HashMap<>();

        stringInteger.put("Банан", 1);
        stringInteger.put("Карандаш", 77);
        stringInteger.put("Солнце", 1529);

        for(Map.Entry<String, Integer> entry : stringInteger.entrySet()) {
            integerString.put(entry.getValue(), entry.getKey());
        }

        System.out.println("Задание 1." + "\n" + "   До: " + stringInteger + "\n" + "После: " + integerString + "\n");
    }

   public static void oneMillionNumbers() {

       String[][] results = new String[3][2];
       results[0][0] = "Коллекция";
       results[1][0] = "ArrayList";
       results[2][0] = "LinkedList";
       results[0][1] = "Время, мкс";

       ArrayList<Integer> randomArrayList = new ArrayList<>();
       LinkedList<Integer> randomLinkedList = new LinkedList<>();

       Random rand = new Random();

       for (int i = 0; i < 1000000; i++) {
           randomArrayList.add(rand.nextInt(100));
           randomLinkedList.add(rand.nextInt(100));
       }

       long startArrayList = System.nanoTime();
       for (int j = 0; j < 1000; j++) {
           randomArrayList.get(rand.nextInt(999999));
       }
       long endArrayList = System.nanoTime();

       long elapsedArrayList = (endArrayList - startArrayList) / 1000;
       results[1][1] = Long.toString(elapsedArrayList);

       long startLinkedList = System.nanoTime();
       for (int j = 0; j < 1000; j++) {
           randomLinkedList.get(rand.nextInt(999999));
       }
       long endLinkedList = System.nanoTime();

       long elapsedLinkedList = (endLinkedList - startLinkedList) / 1000;
       results[2][1] = Long.toString(elapsedLinkedList);


       System.out.println("Задание 2.");
       for (int k = 0; k < 3; k++) {
           System.out.println(Arrays.deepToString(results[k]));
       }
   }

   public static void deleteDuplicates() {
       ArrayList<String> checkedList = new ArrayList<>();
       checkedList.add("Банан");
       checkedList.add("Карандаш");
       checkedList.add("Солнце");
       checkedList.add("Суджук");
       checkedList.add("Карандаш");
       checkedList.add("Терем");
       checkedList.add("Банан");
       checkedList.add("Терем");
       checkedList.add("Банан");
       checkedList.add("Банан");
       checkedList.add("Банан");
       checkedList.add("Банан");
       checkedList.add("Банан");
       checkedList.add("Банан");


       System.out.println("\n" + "Задание 3" + "\n" + "   До: " + checkedList);

       for (int i = 0; i < checkedList.size()-1; i++){
           for(int j = i + 1; j < checkedList.size(); j++ ) {
               //System.out.println(checkedList.get(i).equals(checkedList.get(j)));
               if(checkedList.get(i).equals(checkedList.get(j))){
                   checkedList.remove(j);
               }
           }
       }

       System.out.println("После: " + checkedList);
   }
}