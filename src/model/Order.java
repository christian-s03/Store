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
    private ZonedDateTime orderTime;

    public Order(int orderId, Customer customerName, List<Product> products,
                 BigDecimal totalAmount, ZoneId orderTime) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.totalAmount = totalAmount;
        this.orderTime = ZonedDateTime.now(orderTime);
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

    public void setOrderTime(ZonedDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public ZonedDateTime getOrderTime() {
        return orderTime;
    }

    public void printOrderSummary() {
        System.out.println("Order Id: " + orderId);
        System.out.println("Customer " + customer);
        System.out.println("Products: ");
        products.forEach(System.out::println);
        System.out.println("Total Amount: " + totalAmount.setScale(2));
    }
}
