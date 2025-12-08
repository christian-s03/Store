package Tests;

import Exceptions.NotAvailableException;
import Exceptions.ProcessingException;
import model.*;
import service.Cart;
import service.OrderProcessor;
import service.ProductManager;
import service.Shopping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class OpenStore {
    public static void main(String[] args) throws NotAvailableException, ProcessingException {
        testViewProducts();
        testAddProductToCart();
        testRemoveProductFromCart();
        testSetUpPc();
        testSetUpPhone();
        testCheckout();
    }

    private static Shopping createShoppingWithSampleData(String scannerInput) throws NotAvailableException {
        ProductManager productManager = new ProductManager();
        Cart cart = new Cart(new ArrayList<>(), productManager);
        OrderProcessor orderProcessor = new OrderProcessor();
        Scanner scanner = new Scanner(scannerInput);
        PcComponents pcComponents = new PcComponents();

        productManager.addProduct(new Product(UUID.randomUUID(), "Laptop", new BigDecimal(3000), 5));
        productManager.addProduct(new Product(UUID.randomUUID(), "Phone", new BigDecimal(1500), 10));

        return new Shopping(productManager, cart, orderProcessor, scanner, pcComponents);
    }

    private static void testViewProducts() throws NotAvailableException {
        System.out.println("=== testViewProducts ===");
        Shopping shopping = createShoppingWithSampleData("");
        shopping.viewProducts();
        System.out.println();
    }

    private static void testAddProductToCart() throws NotAvailableException {
        System.out.println("=== testAddProductToCart ===");

        Product product = new Product(UUID.randomUUID(), "Mouse", new BigDecimal(99.99), 4);

        ProductManager pm = new ProductManager();
        pm.addProduct(product);
        String simulatedInput =
                product.getId().toString() + "\n" +
                        "2\n";
        Shopping shopping = new Shopping(pm, new Cart(), new OrderProcessor(), new Scanner(simulatedInput), new PcComponents());
        shopping.addProductToCart();
        System.out.println("Cart should contain 2 products.");
        System.out.println();
    }

    private static void testRemoveProductFromCart() throws NotAvailableException {
        System.out.println("=== testRemoveProductFromCart ===");
        Product product = new Product(UUID.randomUUID(), "Keyboard", new BigDecimal(150), 10);
        ProductManager pm = new ProductManager();
        pm.addProduct(product);

        Cart cart = new Cart();
        cart.addProductToCart(product, 3);

        String simulatedInput =
                product.getId().toString() + "\n" +
                        "2\n";
        Shopping shopping = new Shopping(pm, cart, new OrderProcessor(), new Scanner(simulatedInput), new PcComponents());

        shopping.removeProductFromCart();

        System.out.println("Expected to remove 2 products.");
        System.out.println("Cart should now have only 1 product.");
        System.out.println();
    }

    private static void testSetUpPc() throws NotAvailableException {
        System.out.println("=== testSetUpPc ===");

        String simulatedInput =
                "1\n" +
                        "1\n" +
                        "1\n" +
                        "1\n" +
                        "YES\n" +
                        "0\n";

        Shopping shopping = createShoppingWithSampleData(simulatedInput);
        try {
            shopping.setUpPc();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("PC setup test completed.");
        System.out.println();
    }

    private static void testSetUpPhone() throws NotAvailableException {
        System.out.println("=== testSetUpPhone ===");
        String simulatedInput =
                "1\n" +
                        "2\n" +
                        "1\n" +
                        "0\n" +
                        "YES\n" +
                        "0\n";

        Shopping shopping = createShoppingWithSampleData(simulatedInput);
        shopping.setUpPhone();
        System.out.println("Smartphone setup test completed.");
        System.out.println();
    }

    private static void testCheckout() throws NotAvailableException, ProcessingException {
        System.out.println("=== testCheckout ===");

        String simulatedInput =
                "Jan Kowalski\n" +
                        "jan@example.com\n";

        Shopping shopping = createShoppingWithSampleData(simulatedInput);

        Product example = new Product(UUID.randomUUID(), "Monitor", new BigDecimal(500), 5);
        shopping.productManager.addProduct(example);
        shopping.cart.addProductToCart(example, 1);

        shopping.checkout();
    }
}
