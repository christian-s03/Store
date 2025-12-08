package Tests;

import model.Customer;
import model.Order;

import java.util.UUID;

public class CustomerTest {

    public static void main(String[] args) {
        testCreateCustomer();
        testSetCurrentOrder();
        testUpdateCustomerInfo();
    }

    private static void testCreateCustomer() {
        System.out.println("=== testCreateCustomer ===");
        Customer customer = new Customer("Jan Kowalski", "jan@example.com");

        System.out.println("Customer info:");
        System.out.println(customer);
        System.out.println();
    }

    private static void testSetCurrentOrder() {
        System.out.println("=== testSetCurrentOrder ===");
        Customer customer = new Customer("Anna Nowak", "anna@example.com");

        Order order = new Order();
        customer.setCurrentOrder(order);

        System.out.println("Customer's current order set:");
        System.out.println("Order ID: " + customer.getCurrentOrder().getOrderId());
        System.out.println();
    }

    private static void testUpdateCustomerInfo() {
        System.out.println("=== testUpdateCustomerInfo ===");
        Customer customer = new Customer("Marek Wiśniewski", "marek@example.com");

        customer.setCustomerName("Marek Nowak");
        customer.setCustomerEmail("marek.nowak@example.com");

        System.out.println("Updated customer info:");
        System.out.println(customer);
        System.out.println();
    }
}
