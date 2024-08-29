package org.example.students;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AverageAnalyticsTest {
    private Examination storage = new ExaminationDataBase(); ;


    @Test
    void getAverageForSubjectForAnalytic() {

        Score score1 = new Score(1, "Мария Швецова", "Философия", 5);
        Score score2 = new Score(2, "Денис Кораблёв", "Философия", 3);
        Score score3 = new Score(3, "Фёдор Курочкин", "Философия", 2);
        Score score4 = new Score(4, "Пётр Анисимович", "Психология", 1);
        Score score5 = new Score(5, "Пётр Анисимович", "Уголовное право", 2);
        Score score6 = new Score(6, "Людмила Колонкова", "Криминалистика", 4);
        storage.addScore(score1);
        storage.addScore(score2);
        storage.addScore(score3);

        storage.addScore(score4);
        storage.addScore(score5);
        storage.addScore(score6);

        Assertions.assertEquals(3, Math.round(storage.getAverageForSubject("Философия"))); //округлим до ближайшего целого
        Assertions.assertEquals(2, Math.round(storage.getAverageForSubject("Уголовное право")));
    }
}