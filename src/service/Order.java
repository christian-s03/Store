package service;

import model.Product;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private String customerName;
    private String customerEmail;
    private List<Product> products;
    private BigDecimal totalAmount;

    public Order(String customerName, String customerEmail, List<Product> products, Cart cart) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.products = products;
        this.totalAmount = cart.totalCartPrice();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void printOrderSummary() {
        System.out.println("Customer Name: " + customerName);
        System.out.println("Customer Email: " + customerEmail);
        System.out.println("Products: ");
        products.forEach(System.out::println);
        System.out.println("Total Amount: " + totalAmount.setScale(2));
    }
}
