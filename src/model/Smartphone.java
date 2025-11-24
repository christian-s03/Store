package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Smartphone extends Product {
    public enum Color {
        BLACK,
        WHITE,
        SILVER,
        GOLD,
        RED,
        PINK,
        BLUE
    }

    private String color;
    private int batteryCapacity;
    private List<String> accessories;

    public Smartphone(int id, String name, BigDecimal price, int quantityAvailable, String color, int batteryCapacity) {
        super(id, name, price, quantityAvailable);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.accessories = new ArrayList<>();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public List<String> getAccessories() {
        return accessories;
    }

    public void addAccessory(String accessory) {
        accessories.add(accessory);
    }

    public static void displayAvailableColors() {
        System.out.println("Available colors:");
        int i = 1;
        for (Color color : Color.values()) {
            System.out.println(i++ + ". " + color);
        }
    }

    @Override
    public String toString() {
        return "model.Smartphone{" + "id = " + getId() +
                ", Product name = " + getProductName() +
                ", color =" + color +
                ", Battery Capacity =" + batteryCapacity +
                ", accessories =" + accessories +
                '}';
    }
}

