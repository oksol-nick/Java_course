package io.cucumber.skeleton;

import io.cucumber.java.*;


public class Hooks {
    @BeforeAll
    public static void beforeAll() {

        System.out.println("ПОЕХАЛИ!!!\n\n");
    }

    @Before()
    public void beforeScenario(Scenario scenario) {

            System.out.println("-----------" +
                    scenario.getName() + "-----------");
    }

    @After
    public void afterScenario(Scenario scenario) {

        System.out.println("----------------------------------------------------------\n\n" );
    }


    @AfterAll
    public static void afterAll() {
        System.out.println("ВСЁ, ПРИЕХАЛИ!!!");
    }
}



