package org.example.students;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CachedAverageAnalyticsTest {

    private final MockStorage storage = new MockStorage();

    private final Analytics analytics = new CachedAverageAnalytics(new AverageAnalytics(storage));

    @Test
    void callOnesForRepeatingRequests() {
        String request = "Философия";
        Double averageScore = analytics.getAverageForSubjectForAnalytic(request);

        Assertions.assertEquals(3, Math.round(analytics.getAverageForSubjectForAnalytic("Философия")));

        analytics.getAverageForSubjectForAnalytic(request);
        analytics.getAverageForSubjectForAnalytic(request);
        analytics.getAverageForSubjectForAnalytic(request);

        Assertions.assertEquals(1, storage.calls);

    }

    private class MockStorage implements Examination {

        private int calls = 0;

        @Override
        public void addScore(Score score) {

        }

        @Override
        public Score getScore(String name, String subject) {
            return storage.getScore(name, subject);
        }

        @Override
        public double getAverageForSubject(String subject) {
            return 0;
        }

        @Override
        public Set<String> multipleSubmissionsStudentNames() {
            return Set.of();
        }

        @Override
        public List<String> lastFiveStudentsWithExcellentMarkOnAnySubject() {
            return List.of();
        }

        @Override
        public Map<Integer, Score> getAllScores() {
            calls++;
            Score score1 = new Score(1, "Мария Швецова", "Философия", 5);
            Score score2 = new Score(2, "Денис Кораблёв", "Философия", 3);
            Score score3 = new Score(3, "Фёдор Курочкин", "Философия", 2);
            Score score4 = new Score(4, "Пётр Анисимович", "Психология", 1);
            Score score5 = new Score(5, "Пётр Анисимович", "Уголовное право", 2);
            Score score6 = new Score(6, "Людмила Колонкова", "Криминалистика", 4);
            return Map.of(score1.id(), score1, score2.id(), score2, score3.id(), score3, score4.id(), score4, score5.id(), score5, score6.id(), score6);
        }

        @Override
        public List<String> getAllSubjects() {
            return List.of();
        }
    }
}