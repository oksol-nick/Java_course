import exam.test.check.AppConfig;
import exam.test.check.ResultProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] arg) throws FileNotFoundException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ResultProcessor resultProcessor = applicationContext.getBean(ResultProcessor.class);

        String file1 = "src/main/resources/key.txt";
        String file2 = "src/main/resources/answers.txt";

        resultProcessor.resultProcessor(file1, file2);
    }
}
