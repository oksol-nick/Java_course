package store.onlinestore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnlineStoreApplicationTests {

	Product product1 = new Product("Laptop", 1200, 5);
	Product product2 = new Product("Phone", 800, 10);
	Product product3 = new Product("Headphones", 100, 20);

	Customer customer1 = new Customer("Marija Shvetsova");
	Customer customer2 = new Customer("Denis Korablev");

	@AfterEach
	void printLine() {
		System.out.println("----------------------------------------------------------------------------");
	}

	@Test
	void displayProductTest() {
		assertEquals("Product: Laptop, Price: 1200.0, Stock: 5", Product.displayProduct(product1));
		assertEquals("Product: Phone, Price: 800.0, Stock: 10", Product.displayProduct(product2));
		assertEquals("Product: Headphones, Price: 100.0, Stock: 20", Product.displayProduct(product3));
	}

	@Test
	void addProductToCartTest() {
		customer1.addToCart(product1, 1);
		customer2.addToCart(product2,7);

		assertEquals(4, product1.getStock());
		assertEquals(3, product2.getStock());
	}

	@Test
	void viewCartTest() {
		List<Product> cartToAssert = new ArrayList<>();
		cartToAssert.add(product1);
		cartToAssert.add(product2);

		customer1.addToCart(product1, 1);
		customer1.addToCart(product2, 2);

		assertEquals(cartToAssert, customer1.viewCart());
	}

	@Test
	void checkoutTest() {
		customer1.addToCart(product1, 1);
		customer1.addToCart(product2, 2);

		assertEquals(2800, customer1.checkout());
	}
}

