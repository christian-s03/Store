package model;

import java.math.BigDecimal;
import java.util.UUID;

public class Computer extends Product {
    private String processor;
    private String ram;
    private String ssd;
    private String charger;

    public Computer(UUID id, String productName, BigDecimal price, int quantityAvailable,
                    String processor, String ram, String ssdDriveCapacity, String charger) {
        super(id, productName, price, quantityAvailable);
        this.processor = processor;
        this.ram = ram;
        this.ssd = ssd;
        this.charger = charger;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }
    @Override
    public String toString() {
        return "Computer{" +
                "id=" + getId() +
                ", Product name='" + getProductName() +
                ", procesor='" + processor +
                ", memory ram=" + ram +
                ", SSD=" + ssd +
                ", charger=" + charger +
                '}';
    }
}