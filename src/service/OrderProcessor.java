package service;

import model.Product;

import java.math.BigDecimal;
import java.util.List;

public class OrderProcessor implements Runnable {
    private final Order order;

    public OrderProcessor(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        BigDecimal total = order.getTotalAmount();

        System.out.println("Processing your order...");
        System.out.println("Completed order for: " + order.getCustomerName());
        System.out.println("Customer's e-mail: " + order.getCustomerEmail());
        System.out.println("Total amount: " + total.setScale(2) + " zł ");

        generateInvoice();
    }

    private void generateInvoice() {
        System.out.println(" === Invoice === ");
        System.out.println("Customer Name: " + order.getCustomerName());
        System.out.println("Customer Email: " + order.getCustomerEmail());
        System.out.println("Products: ");
        List<Product> products = order.getProducts();
        for (Product product : products) {
            System.out.println("- " + product.getProductName() + " = " +  product.getPrice().setScale(2) + " zł ");
        }
        BigDecimal total = order.getTotalAmount();
        System.out.println("Total = " + total.setScale(2) + " zł ");
    }
}

