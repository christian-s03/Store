package Tests;

import Exceptions.NotAvailableException;
import model.Product;
import service.Cart;
import service.ProductManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class CartTest {
    public static void main(String[] args) throws NotAvailableException {
        testAddProductToCart();
        testRemoveProductFromCart();
        testTotalCartPrice();
        testViewCartProducts();
    }

    private static void testAddProductToCart() throws NotAvailableException {
        System.out.println("=== testAddProductToCart ===");

        ProductManager pm = new ProductManager();
        Product product = new Product(UUID.randomUUID(), "Mouse", new BigDecimal(100), 5);
        pm.addProduct(product);
        Cart cart = new Cart(new ArrayList<>(), pm);
        cart.addProductToCart(product, 3);

        System.out.println("Expected quantity in cart: 3");
        System.out.println("Actual quantity: " + cart.getQuantityInCart(product));
        System.out.println();
    }

    private static void testRemoveProductFromCart() throws NotAvailableException {
        System.out.println("=== testRemoveProductFromCart ===");

        ProductManager pm = new ProductManager();
        Product product = new Product(UUID.randomUUID(), "Keyboard", new BigDecimal(150), 5);
        pm.addProduct(product);

        Cart cart = new Cart(new ArrayList<>(), pm);
        cart.addProductToCart(product, 4);

        cart.removeProductFromCart(product, 2);
        System.out.println("Expected quantity in cart after removal: 2");
        System.out.println("Actual quantity: " + cart.getQuantityInCart(product));
        System.out.println();
    }

    private static void testTotalCartPrice() throws NotAvailableException {
        System.out.println("=== testTotalCartPrice ===");

        ProductManager pm = new ProductManager();
        Product product1 = new Product(UUID.randomUUID(), "Monitor", new BigDecimal(5000), 10);
        Product product2 = new Product(UUID.randomUUID(), "Headset", new BigDecimal(300), 10);
        pm.addProduct(product1);
        pm.addProduct(product2);

        Cart cart = new Cart(new ArrayList<>(), pm);
        cart.addProductToCart(product1, 2);
        cart.addProductToCart(product2, 1);

        BigDecimal total = cart.totalCartPrice();
        System.out.println("Total price after discounts: " + total);
        System.out.println();
    }

    private static void testViewCartProducts() throws NotAvailableException {
        System.out.println("=== testViewCartProducts ===");

        ProductManager pm = new ProductManager();
        Product product = new Product(UUID.randomUUID(), "Laptop", new BigDecimal(7000), 5);
        pm.addProduct(product);

        Cart cart = new Cart(new ArrayList<>(), pm);
        cart.addProductToCart(product, 1);

        cart.viewCartProducts();
        System.out.println();
    }
}