package store.onlinestore;
import io.cucumber.java.*;

public class Hooks {

    @Before()
    public void beforeScenario(Scenario scenario) {

            System.out.println("-----------" +
                    scenario.getName() + "-----------");
    }

    @After
    public void afterScenario(Scenario scenario) {

        System.out.println("----------------------------------------------------------\n\n" );
    }

}



