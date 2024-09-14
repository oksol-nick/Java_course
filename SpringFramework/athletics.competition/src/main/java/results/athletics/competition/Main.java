package results.athletics.competition;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(appConfig.class);
        ResultProcessor resultProcessor = applicationContext.getBean(ResultProcessor.class);

        String  fileName = "src/main/resources/results.csv";
        String gender = "M";
        int distance = 10;
        int numOfPeople = 3;

        resultProcessor.resultProcessor(fileName, gender, distance, numOfPeople);
    }
}
