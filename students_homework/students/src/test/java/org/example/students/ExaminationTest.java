package org.example.students;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExaminationTest {

    private final Examination base = new ExaminationDataBase();
    @Test
    void addScore() {
        Score score1 = new Score(1,"Мария Швецова", "Философия", 5 );
        Score score2 = new Score(2,"Денис Кораблёв", "Философия", 3 );
        Score score3 = new Score(3,"Фёдор Курочкин", "Философия", 2 );
        Score score4 = new Score(4, "Фёдор Курочкин", "Философия", 3 );
        base.addScore(score1);
        base.addScore(score2);
        base.addScore(score3); //запись должна быть удалена
        base.addScore(score4);


        Assertions.assertEquals(3, (base.getAllScores().size()));
    }

    @Test
    void getScore() {
        Score score1 = new Score(1,"Мария Швецова", "Философия", 5 );
        Score score2 = new Score(2,"Денис Кораблёв", "Философия", 3 );
        Score score3 = new Score(3, "Фёдор Курочкин", "Философия", 2 );
        base.addScore(score1);
        base.addScore(score2);
        base.addScore(score3);

        System.out.println(base.getScore("Денис Кораблёв", "Философия"));
        Assertions.assertEquals(score2, base.getScore("Денис Кораблёв", "Философия"));

    }

    @Test
    void getAverageForSubject() {
        Score score1 = new Score(1, "Мария Швецова", "Философия", 5);
        Score score2 = new Score(2, "Денис Кораблёв", "Философия", 3);
        Score score3 = new Score(3, "Фёдор Курочкин", "Философия", 2);
        Score score4 = new Score(4, "Пётр Анисимович", "Психология", 1);
        Score score5 = new Score(5, "Пётр Анисимович", "Уголовное право", 2);
        Score score6 = new Score(6, "Людмила Колонкова", "Криминалистика", 4);
        base.addScore(score1);
        base.addScore(score2);
        base.addScore(score3);
        base.addScore(score4);
        base.addScore(score5);
        base.addScore(score6);

        Assertions.assertEquals(3, Math.round(base.getAverageForSubject("Философия"))); //округлим до ближайшего целого

        Assertions.assertEquals(2, Math.round(base.getAverageForSubject("Уголовное право")));
    }

    @Test
    void multipleSubmissionsStudentNames() {
        Score score1 = new Score(1,"Мария Швецова", "Философия", 5 );
        Score score2 = new Score(2,"Денис Кораблёв", "Философия", 3 ); // должен быть в списке
        Score score3 = new Score(3,"Фёдор Курочкин", "Философия", 2 ); // должен быть в списке
        Score score4 = new Score(4,"Фёдор Курочкин", "Философия", 3 );
        Score score5 = new Score(5,"Денис Кораблёв", "Философия", 5 );
        base.addScore(score2);
        base.addScore(score3);
        base.addScore(score4);
        base.addScore(score5);

        Assertions.assertEquals(2, base.multipleSubmissionsStudentNames().size());
        System.out.println(base.multipleSubmissionsStudentNames());

    }

    @Test
    void lastFiveStudentsWithExcellentMarkOnAnySubject() {
        Score score1 = new Score(1, "Мария Швецова", "Философия", 5);
        Score score2 = new Score(2, "Денис Кораблёв", "Философия", 5);
        Score score3 = new Score(3, "Фёдор Курочкин", "Философия", 5);
        Score score4 = new Score(4, "Пётр Анисимович", "Психология", 2);
        Score score5 = new Score(5, "Борис Шипов", "Психология", 3);
        Score score6 = new Score(6, "Пётр Анисимович", "Уголовное право", 5);
        Score score7 = new Score(7, "Людмила Колонкова", "Криминалистика", 5);
        Score score8 = new Score(8, "Ольга Цветкова", "Криминалистика", 5);
        Score score9 = new Score(9, "Пётр Анисимович", "Криминалистика", 5);
        base.addScore(score1);
        base.addScore(score2);
        base.addScore(score3);
        base.addScore(score4);
        base.addScore(score5);
        base.addScore(score6);
        base.addScore(score7);
        base.addScore(score8);
        base.addScore(score9);

        List<String> lastFiveScoreStudents = List.of("Пётр Анисимович", "Ольга Цветкова", "Людмила Колонкова", "Фёдор Курочкин", "Денис Кораблёв");

        Assertions.assertEquals(lastFiveScoreStudents, base.lastFiveStudentsWithExcellentMarkOnAnySubject());
        System.out.println(base.lastFiveStudentsWithExcellentMarkOnAnySubject());
    }

    @Test
    void getAllSubjects() {
        Score score1 = new Score(1,"Мария Швецова", "Философия", 5 );
        Score score2 = new Score(2,"Денис Кораблёв", "Философия", 3 );
        Score score3 = new Score(3,"Фёдор Курочкин", "Философия", 2 );
        Score score4 = new Score(4,"Пётр Анисимович", "Психология", 1 );
        Score score5 = new Score(5,"Пётр Анисимович", "Уголовное право", 2 );
        Score score6 = new Score(6,"Людмила Колонкова", "Криминалистика", 4 );
        base.addScore(score1);
        base.addScore(score2);
        base.addScore(score3);
        base.addScore(score4);
        base.addScore(score5);
        base.addScore(score6);

        List<String> sortedOrder = List.of("Криминалистика", "Психология", "Уголовное право", "Философия");

        Assertions.assertEquals(sortedOrder, base.getAllSubjects());
        System.out.println(base.getAllSubjects());
    }

    @Test
    void getAllScores() {
        Score score1 = new Score(1,"Мария Швецова", "Философия", 5 );
        Score score2 = new Score(2,"Денис Кораблёв", "Философия", 3 );
        Score score3 = new Score(3,"Фёдор Курочкин", "Философия", 2 );
        Score score4 = new Score(4,"Пётр Анисимович", "Психология", 1 );
        Score score5 = new Score(5,"Пётр Анисимович", "Уголовное право", 2 );
        base.addScore(score1);
        base.addScore(score2);
        base.addScore(score3);
        base.addScore(score4);
        base.addScore(score5);

        Map<Integer, Score> allScores = base.getAllScores();

        Assertions.assertEquals(5, allScores.size());

    }
}
