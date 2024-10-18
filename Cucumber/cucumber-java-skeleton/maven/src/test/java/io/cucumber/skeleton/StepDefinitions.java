package io.cucumber.skeleton;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StepDefinitions {

    @Given("Пользователь находится на главной странице системы")
    public void Пользователь_находится_на_главной_странице_системы() {
        System.out.println("Введите название товара");
    }

    @When("Пользователь вводит название товара в поисковую строку <{string}>")
    public void Пользователь_вводит_название_товара_в_поисковую_строку(String good) {
        System.out.printf("Найти: %s \n", good);
        Assertions.assertEquals("spri", good);
    }

    @Then("Под поисковой строкой появляется список с возможными вариантами товара:")
    public void Под_поисковой_строкой_появляется_список_с_возможными_вариантами_товара(List<String> prompts) {
        List<String> promptsToEqual = new ArrayList<>();
        promptsToEqual.add("Spring быстро");
        promptsToEqual.add("Spring 5 для профессионалов");
        System.out.println(prompts);
        Assertions.assertEquals(promptsToEqual, prompts);
    }

    @And("В поисковой строке частично введено название товара <{string}>")
    public void В_поисковой_строке_частично_введено_название_товара(String good) {
        System.out.printf("Найти: %s \n", good);
        Assertions.assertEquals("spri", good);
    }

    @And("Под поисковой строкой сформирован список с возможными вариантами товара:")
    public void Под_поисковой_строкой_сформирован_список_с_возможными_вариантами_товара(List<String> prompts) {
        List<String> promptsToEqual = new ArrayList<>();
        promptsToEqual.add("Spring быстро");
        promptsToEqual.add("Spring 5 для профессионалов");
        System.out.println(prompts);
        Assertions.assertEquals(promptsToEqual, prompts);
    }

    @When("Пользователь выбирает щелком левой клавиши мыши первый элемент списка подсказок <{string}>")
    public void Пользователь_выбирает_щелком_левой_клавиши_мыши_первый_элемент_списка_подсказок(String userChoice) {
        System.out.printf("Я выбираю: %s \n", userChoice);
        Assertions.assertEquals("Spring быстро", userChoice);
    }

    @Then("Частично введенное пользователем название товара изменяется на название товара из первого элемента списка подсказок <{string}>")
    public void Частично_введенное_пользователем_название_товара_изменяется_на_название_товара_из_первого_элемента_списка_подсказок
            (String stringForSearch) {
        System.out.printf("Найти: %s \n", stringForSearch);
        Assertions.assertEquals("Spring быстро", stringForSearch);
    }

    @And("В поисковой строке введено название товара <{string}>")
    public void В_поисковой_строке_введено_название_товара(String goodForSearch) {
        System.out.printf("Найти: %s \n", goodForSearch);
        Assertions.assertEquals("Spring быстро", goodForSearch);
    }

    @When("Пользователь нажимает кнопку НАЙТИ <{string}>")
    public void Пользователь_нажимает_кнопку_НАЙТИ(String click) {
        System.out.printf("НАЙТИ->%s \n", click);
        Assertions.assertEquals("CLICK", click);
    }

    @Then("Пользователь перенаправляется на страницу с результатами поиска:")
    public void Пользователь_перенаправляется_на_страницу_с_результатами_поиска(@NotNull DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            String name = row.get("name");
            String type = row.get("type");
            String price = row.get("price");
        }

        System.out.println("Результаты поиска:");
        System.out.println(data);
    }

    @And("В поисковой строке введено некорректное название товара {string}")
    public void В_поисковой_строке_введено_некорректное_название_товара(String goodForSearch) {
        System.out.printf("Найти: %s \n", goodForSearch);
        //Assertions.assertEquals("Spring быстро", goodForSearch);
    }

    @When("Пользователь нажимает кнопку НАЙТИ_2 <{string}>")
    public void Пользователь_нажимает_кнопку_НАЙТИ_2(String click) {
        System.out.printf("НАЙТИ->%s \n", click);
        Assertions.assertEquals("CLICK", click);
    }
}

