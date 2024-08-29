package org.example.students;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CachedAverageScore implements Examination{

    private final ExaminationDataBase examinationDataBase;

    public CachedAverageScore(ExaminationDataBase examinationDataBase) {

        this.examinationDataBase = examinationDataBase;
    }

    @Override
    public void addScore(Score score) {

    }

    @Override
    public Score getScore(String name, String subject) {
        return examinationDataBase.getScore(name, subject);
    }

    @Override
    public double getAverageForSubject(String subject) {
        return examinationDataBase.getAverageForSubject(subject);
    }

    @Override
    public Set<String> multipleSubmissionsStudentNames() {
        return examinationDataBase.multipleSubmissionsStudentNames();
    }

    @Override
    public List<String> lastFiveStudentsWithExcellentMarkOnAnySubject() {
        return examinationDataBase.lastFiveStudentsWithExcellentMarkOnAnySubject();
    }

    @Override
    public Map<Integer, Score> getAllScores() {
        return examinationDataBase.getAllScores();
    }

    @Override
    public List<String> getAllSubjects() {
        return examinationDataBase.getAllSubjects();
    }
}
