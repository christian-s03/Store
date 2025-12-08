package Tests;

import Exceptions.NotAvailableException;
import model.Product;
import service.ProductManager;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductManagerTest {

    public static void main(String[] args) {
        testAddProduct();
        testFindById();
        testRemoveProduct();
        testDisplayProducts();
    }

    private static void testAddProduct() {
        System.out.println("=== testAddProduct ===");

        ProductManager manager = new ProductManager();
        Product product = new Product(
                UUID.randomUUID(),
                "Laptop",
                new BigDecimal(3000.00),
                5
        );

        try {
            manager.addProduct(product);
            System.out.println("Product added successfully");
        } catch (NotAvailableException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
        Product product2 = new Product(
                UUID.randomUUID(),
                "Phone",
                new BigDecimal(1500.00),
                0
        );
        try {
            manager.addProduct(product2);
        } catch (NotAvailableException e) {
            System.out.println("Expected error: " + e.getMessage());
        }
        System.out.println();
    }

    private static void testFindById() {
        System.out.println("=== testFindById ===");

        ProductManager manager = new ProductManager();
        UUID id = UUID.randomUUID();
        Product product = new Product(
                id,
                "Tablet",
                new BigDecimal(1999.99),
                10
        );

        try {
            manager.addProduct(product);
        } catch (NotAvailableException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
        System.out.println("Searching for product with ID: " + id);
        System.out.println("Found: " + manager.findById(id));
        System.out.println("Searching for no existing ID:");
        System.out.println("Found: " + manager.findById(UUID.randomUUID()));
        System.out.println();
    }

    private static void testRemoveProduct() {
        System.out.println("=== testRemoveProduct ===");

        ProductManager manager = new ProductManager();
        UUID id = UUID.randomUUID();
        Product product = new Product(
                id,
                "Monitor",
                new BigDecimal(1200.00),
                8
        );
        try {
            manager.addProduct(product);
        } catch (NotAvailableException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        System.out.println("Removing 3 items");
        manager.removeProduct(id, 3);
        System.out.println("Quantity after remove: " + product.getQuantityAvailable());

        System.out.println("Removing 100 items");
        manager.removeProduct(id, 100);

        System.out.println("Removing from invalid ID");
        manager.removeProduct(UUID.randomUUID(), 1);

        System.out.println();
    }

    private static void testDisplayProducts() {
        System.out.println("=== testDisplayProducts ===");

        ProductManager manager = new ProductManager();
        System.out.println("Display empty list:");
        manager.displayProducts();
        Product product = new Product(
                UUID.randomUUID(),
                "Keyboard",
                new BigDecimal(150.00),
                20
        );
        try {
            manager.addProduct(product);
        } catch (NotAvailableException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
        System.out.println("Display list with 1 product:");
        manager.displayProducts();
        System.out.println();
    }
}
