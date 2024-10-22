package store.onlinestore;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Product> cart;

    public Customer(String name) {
        this.name = name;
        this.cart = new ArrayList<>();
    }

    // Добавление товара в корзину
    public void addToCart(Product product, int quantity) {
        product.setQuantity(quantity);
        if (product.getStock() >= quantity) {
            product.reduceStock(quantity);
            cart.add(product);
            System.out.println(name + " added " + quantity + " of " + product.getName() + " to the cart.");
        } else {
            System.out.println("Not enough stock to add " + product.getName() + " to the cart.");
        }
    }

    // Просмотр корзины
    public List<Product> viewCart() {
        System.out.println(name + "'s Cart:");
        for (Product product : cart) {
            System.out.println(product.displayProductCart(product));
        }
        return cart;
    }

    // Покупка всех товаров в корзине
    public double checkout() {
        double total = 0;
        System.out.println(name + " is checking out:");
        for (Product product : cart) {
            total += product.getPrice() * product.getQuantity();
            System.out.println("Purchased: " + product.getQuantity() + " " + product.getName() + " for " + product.getPrice());
        }
        System.out.println("Total amount: " + total);
        cart.clear();
        return total;
    }

    public String getName() {
        return name;
    }
}

