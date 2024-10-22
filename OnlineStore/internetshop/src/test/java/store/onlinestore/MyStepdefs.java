package store.onlinestore;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {

    Product product1 = new Product("Laptop", 1200, 5);
    Product product2 = new Product("Phone", 800, 10);

    Customer customer1 = new Customer("Marija Shvetsova");

    @Given("Авторизованный пользователь находится на главной странице системы. Пользователь нашел нужный товар")
    public void Пользователь_находится_на_главной_странице_системы_Пользователь_нашел_нужный_товар () {
        System.out.println(product1.getName());
    }

    @Then("Пользователь кликает по иконке товара и перенаправляется на страницу с информацией о товаре")
    public void пользовательКликаетПоИконкеТовараИПеренаправляетсяНаСтраницуСИнформациейОТоваре() {
        System.out.println(Product.displayProduct(product1));
    }

    @And("Пользователь находится на странице товара")
    public void пользовательНаходитсяНаСтраницеТовара() {
        System.out.println(Product.displayProduct(product1));
    }

    @When("Пользователь нажимает кнопку добавить товар в корзину")
    public void пользовательНажимаетКнопкуДобавитьТоварВКорзину() {
       customer1.addToCart(product1, 1);
    }

    @Then("Товар в необходимом количестве появляется в корзине пользователя, общее количество товара уменьшается на количество, выбранное пользователем")
    public void товарВНеобходимомКоличествеПоявляетсяВКорзинеПользователяОбщееКоличествоТовараУменьшаетсяНаКоличествоВыбранноеПользователем() {
        System.out.println(customer1.viewCart());
    }

    @And("Пользователь добавил товары в корзину в необходимом количестве")
    public void пользовательДобавилТоварыВКорзинуВНеобходимомКоличестве() {
        customer1.addToCart(product1, 1);
        customer1.addToCart(product2, 2);
    }

    @When("Пользователь нажимает кнопку купить")
    public void пользовательНажимаетКнопкуКупить() {
        System.out.println("Checkout");
    }

    @Then("Пользователь перенаправляется на страницу с информацией о покупке и сумме к оплате")
    public void пользовательПеренаправляетсяНаСтраницуСИнформациейОСуммеКОплате() {
        customer1.checkout();
    }
}
