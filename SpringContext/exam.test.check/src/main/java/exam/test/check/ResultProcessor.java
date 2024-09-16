package exam.test.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ResultProcessor {

    @Value("#{'${question.cost}'.split(',')}")
    private List<String> questionCost;

    @Autowired
    public ResultProcessor(List<String> questionCost) {
        this.questionCost = questionCost;
    }

    public int resultProcessor(String fileNameKey, String fileNameAnswer) throws FileNotFoundException {

        List<Integer> integerCost = questionCost.stream().map(Integer::valueOf).collect(Collectors.toList());

        int mark = 0;

        List<EvaluationData> data = GetAndParseResults.getAndParseResults(new File(fileNameKey), new File(fileNameAnswer));

        for(int i =0; i < data.size(); i++) {
            if(Objects.equals(data.get(i).getAnswerKey(), data.get(i).getAnswer())) {
                mark = mark + integerCost.get(i);
            }
        }

        System.out.println("Всего баллов " + mark + " из " + integerCost.stream().mapToInt(Integer::intValue).sum() + " возможных");

        return mark;
    }
}
