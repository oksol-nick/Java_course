package store.onlinestore;

public class Product {
    private String name;
    private double price;
    private int stock;
    private int quantity;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, double price, int stock, int quantity) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void reduceStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        } else {
            System.out.println("Not enough stock for " + name);
        }
    }

    // Отображение товара
    public static String displayProduct(Product product) {
        String displayProduct = "Product: " + product.getName() + ", Price: " + product.getPrice() + ", Stock: " + product.getStock();
        return displayProduct;
    }

    // Отображение товара в корзине
    public static String displayProductCart(Product product) {
        String displayProduct = "Product: " + product.getName() + ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity();
        return displayProduct;
    }
}

