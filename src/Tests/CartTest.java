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
        Product product1 = new Product(UUID.randomUUID(), "Laptop", new BigDecimal("6000"), 5);
        ProductManager productManager = new ProductManager();
        productManager.addProduct(product1);

        Cart cart = new Cart(new ArrayList<>(), productManager);

        try {
            cart.addProductToCart(product1, 1);
        } catch (NotAvailableException e) {
            System.out.println("Error adding product to cart: " + e.getMessage());
        }

        System.out.println("\n=== View Cart ===");
        cart.viewCartProducts();

        System.out.println("\nQuantity of Laptop in cart: " + cart.getQuantityInCart(product1));

        System.out.println("\n=== Remove Products ===");
        cart.removeProductFromCart(product1, 1);

        System.out.println("\n=== View Cart After Removal ===");
        cart.viewCartProducts();
    }
}