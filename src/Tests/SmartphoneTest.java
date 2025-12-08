package Tests;

import model.Smartphone;

import java.math.BigDecimal;
import java.util.UUID;

public class SmartphoneTest {

    public static void main(String[] args) {
        testCreation();
        testSetColor();
        testSetBatteryCapacity();
        testAddAccessory();
    }

    private static void testCreation() {
        System.out.println("=== testCreation ===");
        Smartphone phone = new Smartphone(
                UUID.randomUUID(),
                "Galaxy S23",
                new BigDecimal(3500.00),
                10,
                "BLACK",
                4500
        );
        System.out.println(phone);
        System.out.println();
    }

    private static void testSetColor() {
        System.out.println("=== testSetColor ===");
        Smartphone phone = new Smartphone(
                UUID.randomUUID(),
                "Galaxy S23",
                new BigDecimal(3500.00),
                10,
                "BLACK",
                4500
        );
        phone.setColor("WHITE");
        System.out.println("Expected: WHITE, Actual: " + phone.getColor());
        System.out.println();
    }

    private static void testSetBatteryCapacity() {
        System.out.println("=== testSetBatteryCapacity ===");
        Smartphone phone = new Smartphone(
                UUID.randomUUID(),
                "Galaxy S23",
                new BigDecimal(3500.00),
                10,
                "BLACK",
                4500
        );
        phone.setBatteryCapacity(5000);
        System.out.println("Expected: 5000, Actual: " + phone.getBatteryCapacity());
        System.out.println();
    }

    private static void testAddAccessory() {
        System.out.println("=== testAddAccessory ===");
        Smartphone phone = new Smartphone(
                UUID.randomUUID(),
                "Galaxy S23",
                new BigDecimal(3500.00),
                10,
                "BLACK",
                4500
        );
        phone.addAccessory("Charger");
        System.out.println("Accessories: " + phone.getAccessories());
        System.out.println();
    }
}
