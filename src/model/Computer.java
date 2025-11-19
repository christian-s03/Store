package model;

import java.math.BigDecimal;

public class Computer extends Product {
    private String processor;
    private int ram;
    private int ssd;
    private int charger;

    public Computer(int id, String productName, BigDecimal price, int quantityAvailable,
                    String processor, int ram, int ssdDriveCapacity, int charger) {
        super(id, productName, price, quantityAvailable);
        this.processor = processor;
        this.ram = ram;
        this.ssd = ssd;
        this.charger = charger;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setSsd(int ssd) {
        this.ssd = ssd;
    }

    public void setCharger(int charger) {
        this.charger = charger;
    }
    @Override
    public String toString() {
        return "Computer{" +
                "id=" + getId() +
                ", Product name='" + getProductName() +
                ", procesor='" + processor +
                ", memory ram=" + ram + " GB" +
                ", SSD=" + ssd + " GB" +
                ", charger=" + charger + " W" +
                '}';
    }
}