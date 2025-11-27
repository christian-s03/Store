package Tests;

import model.Customer;

public class CustomerTest {

    public static void main(String[] args) {
        Customer customer = new Customer("John Doe", "john.doe@example.com");
        System.out.println("=== Customer Creation ===");
        System.out.println("Customer info:\n" + customer);

        customer.setCustomerName("Jane Smith");
        customer.setCustomerEmail("jane.smith@example.com");
        System.out.println("\n=== Updated Customer Info ===");
        System.out.println(customer);
    }
}