package Tests;

import model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductTest {
    public static void main(String[] args) {

        Product product = new Product(
                UUID.randomUUID(),
                "Monitor LG",
                new BigDecimal("799.99"),
                10
        );

        System.out.println("Initial: " + product);

        product.decreaseQuantityProduct(2);
        System.out.println("After decrease: " + product);

        product.increaseQuantityProduct(3);
        System.out.println("After increase: " + product);

        System.out.println("Name: " + product.getProductName());
        System.out.println("Price: " + product.getPrice());
    }
}

