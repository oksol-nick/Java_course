package org.example.students;

import java.util.*;

public class ExaminationDataBase implements Examination {

    public static final int INITIAL_CAPACITY = 256;

    private final Map<Integer, Score> items = new HashMap<>(INITIAL_CAPACITY);

    private final Set<String> multypalSub = new HashSet<>();


    @Override
    public void addScore(Score score) {

        for(Score existScore : items.values()) {
            if (Objects.equals(existScore.name(), score.name()) && Objects.equals(existScore.subject(), score.subject())) {
                multypalSub.add(existScore.name());
                items.remove(existScore.id());
                break;
            }
        }
        items.put(score.id(), score);

    }

    @Override
    public Score getScore(String name, String subject) {

        Score findedScore = null;
        for(Score score : items.values()) {

            if (Objects.equals(score.name(), name) && Objects.equals(score.subject(), subject)) {
                findedScore = score;
            }
        }
        return findedScore;
    }

    @Override
    public double getAverageForSubject(String subject) {
        int i = 0;
        double scoreSumm = 0;
        for(Score score : items.values()) {
            if (Objects.equals(score.subject(), subject)) {
                scoreSumm = scoreSumm + score.score();
                i = i + 1;
            }
        }

        double averageScore = scoreSumm / i;

        return averageScore;
    }

    @Override
    public Set<String> multipleSubmissionsStudentNames() {

        return multypalSub;
    }

    @Override
    public List<String> lastFiveStudentsWithExcellentMarkOnAnySubject() {

        return  items.values().stream().filter(score -> score.score() == 5).sorted(Comparator.comparing(Score::id)
                .reversed()).map(Score::name).distinct().limit(5).toList();
    }

    @Override
    public Map<Integer, Score> getAllScores() {

        return new HashMap<Integer, Score>(items);
    }


    @Override
    public List<String> getAllSubjects() {

        Set<String> subjects = new HashSet<>();

        for (Score score : items.values()) subjects.add(score.subject());

        List<String> sortedSubjects = new ArrayList<>(subjects);

        Collections.sort(sortedSubjects);

        return sortedSubjects;
    }

}


