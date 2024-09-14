package results.athletics.competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ResultProcessor {

    @Autowired
    public ResultProcessor() {
    }

    public List<Result> resultProcessor(String fileName, String gender, int distance, int numOfPersons) {

        List<Result> parsedList = GetAndParseResults.getAndParseResults(new File(fileName));
        List<Result> processedResults =  parsedList.stream().sorted(Comparator.comparing(Result::getDistance).thenComparing(Result::getGender).
                thenComparing(Result::getTime)).filter(result -> Objects.equals(result.getGender(), gender)).
                filter(result -> result.getDistance() == distance).limit(numOfPersons).collect(Collectors.toList());

        for(int i = 0; i < processedResults.size(); i++) {
            System.out.println(processedResults.get(i));
        }

        return processedResults;
    }
}
