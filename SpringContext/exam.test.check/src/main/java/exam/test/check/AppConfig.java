package exam.test.check;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("exam.test.check")
@Import(PropertiesConfiguration.class)
public class AppConfig {
}

