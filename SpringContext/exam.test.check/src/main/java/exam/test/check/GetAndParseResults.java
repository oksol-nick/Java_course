package exam.test.check;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class GetAndParseResults {

    public static List<EvaluationData> getAndParseResults(File fileKey, File fileAnswers) throws FileNotFoundException {
        List<File> processedFiles = new ArrayList<>();
        List<String> evaluationData = new ArrayList<>();
        List<EvaluationData> dataForProceed = new ArrayList<>();
        processedFiles.add(fileKey);
        processedFiles.add(fileAnswers);

        try {
            for (int i = 0; i < processedFiles.size(); i++) {
                if (processedFiles.get(i).exists()) {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(processedFiles.get(i)));
                    Scanner scanner = new Scanner(bufferedReader).useDelimiter("\\A");
                    String data = scanner.hasNext() ? scanner.next() : "";
                    String[] lines = data.split("\r\n");
                    scanner.close();
                    for (int j = 0; j < lines.length; j++) {
                        if (evaluationData.size() < lines.length) {
                            evaluationData.add(lines[j]);
                        } else {
                            dataForProceed.add(convertLineToResult(evaluationData.get(j) + " - " + lines[j]));
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //System.out.println(dataForProceed);
        return dataForProceed;
    }

    private static EvaluationData convertLineToResult(String line) {
        String[] tokens = line.split(" - ");
        //System.out.println(Arrays.toString(tokens));
        if(tokens.length < 4) {
            String[] tokensAdd = new String[4];
            System.arraycopy(tokens, 0, tokensAdd, 0, 3);
            tokensAdd[3] = "-";
            return new EvaluationData(Integer.parseInt(tokensAdd[0]), tokensAdd[1], tokensAdd[3]);
        } else {
        return new EvaluationData(Integer.parseInt(tokens[0]), tokens[1], tokens[3]);}
    }
}

