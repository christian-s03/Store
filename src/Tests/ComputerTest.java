package Tests;

import model.Computer;

import java.math.BigDecimal;
import java.util.UUID;

public class ComputerTest {
    public static void main(String[] args) {
        testCreateComputer();
        testUpdateComponents();
    }

    private static void testCreateComputer() {
        System.out.println("=== testCreateComputer ===");
        Computer computer = new Computer(UUID.randomUUID(), "Gaming Laptop", new BigDecimal(8000),
                5, "Intel i7", "16GB", "1TB SSD", "65W");
        System.out.println("Computer info:");
        System.out.println(computer);
        System.out.println();
    }

    private static void testUpdateComponents() {
        System.out.println("=== testUpdateComponents ===");
        Computer computer = new Computer(UUID.randomUUID(), "Office Laptop", new BigDecimal(4000),
                3, "Intel i5", "8GB", "512GB SSD", "45W");

        computer.setProcessor("Intel i7");
        computer.setRam("16GB");
        computer.setSsd("1TB SSD");
        computer.setCharger("65W");

        System.out.println("Updated computer info:");
        System.out.println(computer);
        System.out.println();
    }
}