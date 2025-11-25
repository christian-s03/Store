package service;

import model.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderSaveToTxt {
    private static final String TXT_FILE_PATH = "src/orders.txt";

    public static void saveOrdersToTxtFile(List<Order> orders) {
        try (FileWriter writer = new FileWriter(TXT_FILE_PATH)) {
            String ordersText = orders.stream()
                    .map(OrderSaveToTxt::formatOrder)
                    .collect(Collectors.joining("\n"));

            writer.write(ordersText);
            System.out.println("Order saved to " + TXT_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error with saving order: " + e.getMessage());
        }
    }

    private static String formatOrder(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(order.getOrderId()).append("\n");
        sb.append("Products: ").append(order.getProducts()).append("\n");
        sb.append("Order amount: ").append(order.getTotalAmount()).append("\n");
        sb.append("Customer: ").append(order.getCustomer().getCustomerName()).append("\n");
        sb.append("Date of order: ").append(order.getOrderTime()).append("\n");
        sb.append("----\n");
        return sb.toString();
    }
}
