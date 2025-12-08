package Tests;

import model.Smartphone;

import java.math.BigDecimal;
import java.util.UUID;

public class SmartphoneTest {

    public static void main(String[] args) {
        System.out.println("=== Available Colors ===");
        Smartphone.displayAvailableColors();

        Smartphone phone = new Smartphone(
                UUID.randomUUID(),
                "Galaxy S23",
                new BigDecimal("3500.00"),
                10,
                "BLACK",
                4500
        );
        System.out.println("\n=== Smartphone Created ===");
        System.out.println(phone);

        phone.setColor("WHITE");
        phone.setBatteryCapacity(5000);
        System.out.println("\n=== Smartphone After Updating Color and Battery ===");
        System.out.println(phone);
    }
}