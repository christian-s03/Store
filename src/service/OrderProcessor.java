package service;

import model.Customer;
import model.Order;
import model.Product;

import java.math.BigDecimal;
import java.util.List;

public class OrderProcessor implements Runnable {
    public Order order;

    public OrderProcessor() {
    }

    @Override
    public void run() {
        List<Product> products = order.getProducts();
        BigDecimal total = order.getTotalAmount();
        Customer customer = order.getCustomer();

        System.out.println("Processing your order...");
        System.out.println("Completed order for: " + customer.getCustomerName());
        System.out.println("Customer's e-mail: " + customer.getCustomerEmail());
        System.out.println("Total amount: " + total.setScale(2) + " zł ");

    }

    private void generateInvoice(Order order, BigDecimal total) {
        System.out.println(" === Invoice === ");
        System.out.println("Invoice ID: " + order.getOrderId());
        System.out.println("Invoice Date: " + order.getOrderTime());
        System.out.println("Customer Name: " + order.getCustomer());
        System.out.println("Products: ");
        List<Product> products = order.getProducts();
        for (Product product : products) {
            System.out.println("- " + product.getProductName() + " = " + product.getPrice().setScale(2) + " zł ");
        }
        System.out.println("Total = " + total.setScale(2) + " zł ");
    }
}

