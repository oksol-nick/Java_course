package exam.test.check;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class ApplicationTests {

    String file1 = "src/main/resources/key.txt";
    String file2 = "src/main/resources/answers.txt";
    String file3 = "src/main/resources/key.txt";

    File file11 = new File("src/main/resources/key.txt");
    File file22 = new File("src/main/resources/answers.txt");
    File file33 = new File("src/main/resources/key.txt");

    @Test
    void getAndParseResults() throws FileNotFoundException {
        List<EvaluationData> testList = new ArrayList<>();
        testList.add(new EvaluationData(1, "A", "A"));
        testList.add(new EvaluationData(2, "B", "D"));
        testList.add(new EvaluationData(3, "C", "C"));
        testList.add(new EvaluationData(4, "C", "-"));
        testList.add(new EvaluationData(5, "D", "D"));
        testList.add(new EvaluationData(6, "A", "A"));
        testList.add(new EvaluationData(7, "B", "C"));
        testList.add(new EvaluationData(8, "B", "B"));
        testList.add(new EvaluationData(9, "D", "A"));
        testList.add(new EvaluationData(10, "C", "C"));

        List<EvaluationData> results = GetAndParseResults.getAndParseResults(file11, file22);

        for (int i = 0; i < testList.size(); i++) {
            Assertions.assertEquals(testList.get(i).getQuestionNumber(), results.get(i).getQuestionNumber());
            Assertions.assertEquals(testList.get(i).getAnswerKey(), results.get(i).getAnswerKey());
            Assertions.assertEquals(testList.get(i).getAnswer(), results.get(i).getAnswer());
        }
    }

    @Test
    void resultProcessor () throws FileNotFoundException {

        List<String> questionCost = new ArrayList<>();
        questionCost.add("1");
        questionCost.add("1");
        questionCost.add("1");
        questionCost.add("1");
        questionCost.add("2");
        questionCost.add("2");
        questionCost.add("2");
        questionCost.add("2");
        questionCost.add("3");
        questionCost.add("3");

        ResultProcessor resultProcessor = new ResultProcessor(questionCost);

        Assertions.assertEquals(11, resultProcessor.resultProcessor(file1, file2));
        Assertions.assertEquals(18, resultProcessor.resultProcessor(file1, file3));
    }
}