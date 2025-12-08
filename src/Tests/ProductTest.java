package Tests;

import model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductTest {

    public static void main(String[] args) {
        testCreation();
        testSetters();
        testDecreaseQuantity();
        testIncreaseQuantity();
    }

    private static void testCreation() {
        System.out.println("=== testCreation ===");
        Product product = new Product(
                UUID.randomUUID(),
                "Laptop",
                new BigDecimal(2999.99),
                20
        );
        System.out.println(product);
        System.out.println();
    }

    private static void testSetters() {
        System.out.println("=== testSetters ===");
        Product product = new Product(
                UUID.randomUUID(),
                "Laptop",
                new BigDecimal(2999.99),
                20
        );
        product.setProductName("Gaming Laptop");
        product.setPrice(new BigDecimal(3999.99));
        product.setQuantityAvailable(15);

        System.out.println("Name: " + product.getProductName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Quantity: " + product.getQuantityAvailable());
        System.out.println();
    }

    private static void testDecreaseQuantity() {
        System.out.println("=== testDecreaseQuantity ===");
        Product product = new Product(
                UUID.randomUUID(),
                "Laptop",
                new BigDecimal(2999.99),
                20
        );

        product.decreaseQuantityProduct(5);
        System.out.println("Expected quantity: 15, Actual: " + product.getQuantityAvailable());
        System.out.println();

        try {
            product.decreaseQuantityProduct(100);
        } catch (Exception e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        System.out.println();
    }

    private static void testIncreaseQuantity() {
        System.out.println("=== testIncreaseQuantity ===");
        Product product = new Product(
                UUID.randomUUID(),
                "Laptop",
                new BigDecimal(2999.99),
                20
        );
        product.increaseQuantityProduct(5);
        System.out.println("Expected quantity: 25, Actual: " + product.getQuantityAvailable());
        System.out.println();
        try {
            product.increaseQuantityProduct(-10);
        } catch (Exception e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        System.out.println();
    }
}
