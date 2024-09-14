package results.athletics.competition;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class GetAndParseResults {

    public static List <Result> getAndParseResults(File file) {
        List<Result> results = null;
        try {
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                Scanner scanner = new Scanner(bufferedReader).useDelimiter("\\A");
                String result = scanner.hasNext() ? scanner.next() : "";
                results = new ArrayList<>();
                String[] lines = result.split("\r\n");
                for (int i = 0; i < lines.length; i++) {
                    results.add(convertLineToResult(lines[i]));
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return results;
    }

    private static Result convertLineToResult(String line) {
        String[] tokens = line.split(",");
        String[] distance = tokens[2].split(" ");
        String[] time = tokens[3].split(":");
        return new Result(tokens[0], tokens[1], Integer.parseInt(distance[0]), Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
    }
}
