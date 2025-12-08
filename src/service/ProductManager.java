package service;

import Exceptions.NotAvailableException;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductManager {

    private List<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) throws NotAvailableException {
        if (product.getQuantityAvailable() <= 0) {
            throw new NotAvailableException("Product not available");
        }
        products.add(product);
    }

    public Optional<Product> findById(UUID id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    public void removeProduct(UUID id, int quantity) {
        Optional<Product> optionalProduct = findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            int currentQuantity = product.getQuantityAvailable();
            if (currentQuantity >= quantity) {
                product.setQuantityAvailable(currentQuantity - quantity);
            } else {
                System.out.println("Product enough not available");
            }
        } else {
            System.out.println("Invalid product ID or not available");
        }
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Not available");
        } else {
            System.out.println("Stock status");
            for (Product product : products) {
                System.out.println("ID: " + product.getId()
                        + ", Name: " + product.getProductName()
                        + ", Price: " + product.getPrice()
                        + ", available quantity: " + product.getQuantityAvailable());
            }
        }
    }
}
