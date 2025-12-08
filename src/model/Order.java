package model;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private List<Product> products;
    private BigDecimal totalAmount;
    private ZonedDateTime orderDateTime;

    public Order(int orderId, Customer customerName, List<Product> products,
                 BigDecimal totalAmount) {
        this.orderId = orderId;
        this.customer = customerName;
        this.products = products;
        this.totalAmount = totalAmount;
        this.orderDateTime = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public ZonedDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void printOrderSummary() {
        System.out.println("Order Id: " + orderId);
        System.out.println("Customer " + customer);
        System.out.println("Products: ");
        products.forEach(System.out::println);
        System.out.println("Total Amount: " + totalAmount.setScale(2));
    }
}
