import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class ResultsBoard {

    TreeSet<Student> studentBase = new TreeSet<Student>();

    public static void main(String[] args) {
        ResultsBoard resultsBoard = new ResultsBoard();
        resultsBoard.addStudent("Мария Швецова", 4.7f);
        resultsBoard.addStudent("Денис Кораблёв", 4.2f);
        resultsBoard.addStudent("Фёдор Курочкин", 3.8f);
        resultsBoard.addStudent("Ольга Цветкова", 4.4f);
        resultsBoard.addStudent("Борис Шипов", 3.4f);
        resultsBoard.addStudent("Людмила Колонкова", 4.3f);

        System.out.println(resultsBoard.top3());
    }

    class Student implements Comparable<Student> {
        float averageScore;
        String name;

        @Override
        public int compareTo(Student o) {
            if (averageScore == o.averageScore) {
                return 0;
            } else if (averageScore > o.averageScore) {
                return 1;
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return name + " : " + averageScore;
        }
    }

    void addStudent(String name, Float averageScore) {
        Student student = new Student();
        student.name = name;
        student.averageScore = averageScore;
        studentBase.add(student);
    }

    List<Student> top3() {
        List<Student> bestStudents = new ArrayList<>(3);
        NavigableSet<Student> decendStudents = studentBase.descendingSet();

        for(int i = 0; i < 3; i++) {
            if(!decendStudents.isEmpty()){
                bestStudents.add(decendStudents.pollFirst());
            }
        }
        return bestStudents;
    }
}

