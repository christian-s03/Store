package Tests;

import model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductManagerTest {
    public static void main(String[] args) {
        Product product = new Product(UUID.randomUUID(), "Laptop", new BigDecimal(2500.00), 10);
        System.out.println("=== Product Creation ===");
        System.out.println("Product created: " + product);

        try {
            product.decreaseQuantityProduct(3);
            System.out.println("After decreasing 3 units: " + product.getQuantityAvailable() + " units available");
        } catch (IllegalArgumentException e) {
            System.out.println("Error while decreasing quantity: " + e.getMessage());
        }


        try {
            product.increaseQuantityProduct(2);
            System.out.println("After increasing 2 units: " + product.getQuantityAvailable() + " units available");
        } catch (IllegalArgumentException e) {
            System.out.println("Error while increasing quantity: " + e.getMessage());
        }

        System.out.println("Final product info: " + product);
    }
}