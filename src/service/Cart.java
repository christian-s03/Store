package service;

import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;
    private ProductManager productManager;

    public Cart(List<Product> products, ProductManager productManager) {
        this.products = new ArrayList<>();
        this.productManager = productManager;
    }

    public void addProductToCart(Product product, int quantity) {
        productManager.findById(product.getId()).ifPresentOrElse(foundProduct -> {

            if (foundProduct.getQuantityAvailable() < quantity) {
                System.out.println("There is not enough products to add to cart");
                return;
            }

            for (int i = 0; i < quantity; i++) {
                products.add(foundProduct);
            }

            productManager.removeProduct(foundProduct.getId(), quantity);
            System.out.println("Product added to cart");

        }, () -> System.out.println("Product ID not found"));
    }

    public void removeProductFromCart(Product product, int quantity) {
        int removedCount = 0;
        while (removedCount < quantity) {
            if (products.remove(product)) {
                removedCount++;
            } else {
                break;
            }
        }
        if (removedCount > 0) {
            System.out.println("Removed " + removedCount + " product " + product.getProductName() + " from cart");
        } else {
            System.out.println("There's not enough product: " + product.getProductName() + " in cart");
        }
    }

    public int getQuantityInCart(Product product) {
        return (int) products.stream()
                .filter(p -> p.equals(product))
                .count();
    }

    public BigDecimal totalCartPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void viewCartProducts() {
        if (products.isEmpty()) {
            System.out.println("There is no products in the cart");
        } else {
            System.out.println("Cart: ");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public List<Product> getProductsFromCart() {
        return products;
    }
}
