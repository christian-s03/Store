package service;

import model.Customer;
import model.Order;
import model.Product;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderProcessor {
    private final ExecutorService EXECUTOR;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");

    public OrderProcessor() {
        this.EXECUTOR = Executors.newFixedThreadPool(10);
    }

    public void processOrder(Order order) {
        EXECUTOR.submit(new OrderProcessingTask(order));
    }

    private static class OrderProcessingTask implements Runnable {
        private final Order order;

        public OrderProcessingTask(Order order) {
            this.order = order;
        }

        @Override
        public void run() {
            System.out.println("\n==============================================");
            System.out.println("Invoice: "
                    + Thread.currentThread().getName());
            System.out.println("==============================================");

            Customer customer = order.getCustomer();
            List<Product> products = order.getProducts();
            BigDecimal totalAmount = order.getTotalAmount();

            System.out.println("Customer order: " + customer.getCustomerName());
            System.out.println("Customer's email: " + customer.getCustomerEmail());
            System.out.println("Number of products: " + products.size());
            System.out.println("Total amount: " + totalAmount + " zł");

            generateInvoice(order, totalAmount);

            OrderSaveToTxt.saveOrdersToTxtFile(Collections.singletonList(order));

            System.out.println("Order ID " + order.getOrderId() + " completed!");
            System.out.println("==============================================\n");
        }

        private void generateInvoice(Order order, BigDecimal totalAmount) {
            System.out.println("\n----- Invoice -----");
            String formattedDate = order.getOrderDateTime().format(FORMATTER);
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Date: " + formattedDate);
            System.out.println("Customer: " + order.getCustomer());
            System.out.println("Products: :");

            for (Product product : order.getProducts()) {
                System.out.println("- " + product.getProductName() + " | Price: "
                        + product.getPrice().setScale(2) + " zł");
            }

            System.out.println("Total: " + totalAmount.setScale(2) + " zł");
            System.out.println("----- Invoice ended -----\n");
        }
    }
}