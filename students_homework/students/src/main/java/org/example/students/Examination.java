package org.example.students;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Examination {
    void addScore(Score score);

    Score getScore(String name, String subject);

    double getAverageForSubject(String subject); ;

    Set<String> multipleSubmissionsStudentNames();;

    List<String> lastFiveStudentsWithExcellentMarkOnAnySubject();;

    Map<Integer, Score> getAllScores();;

    List<String> getAllSubjects();;
}
