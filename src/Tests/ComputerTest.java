package Tests;

import model.Computer;

import java.math.BigDecimal;
import java.util.UUID;

public class ComputerTest {
    public static void main(String[] args) {
        Computer pc = new Computer(
                UUID.randomUUID(),
                "Dell Inspiron",
                new BigDecimal("3499.99"),
                5,
                "Intel i5",
                "16GB",
                "512GB",
                "65W charger"
        );
        System.out.println(pc);
    }
}
