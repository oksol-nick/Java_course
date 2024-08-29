package org.example.students;

import java.util.Map;
import java.util.Objects;

public class AverageAnalytics implements Analytics{

    private final Examination storage;

    public AverageAnalytics(Examination storage) {
        this.storage = storage;
    }

    @Override
    public double getAverageForSubjectForAnalytic(String subject) {
        int i = 0;
        double scoreSumm = 0;
        Map<Integer, Score> scores = storage.getAllScores();
        for(Score score : scores.values()) {
            if (Objects.equals(score.subject(), subject)) {
                scoreSumm = scoreSumm + score.score();
                i = i + 1;
            }
        }
        double averageScore = scoreSumm / i;

        return averageScore;

    }
}
