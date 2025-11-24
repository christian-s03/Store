package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Smartphone extends Product {
    private Color color;
    private int batteryCapacity;
    private List<String> accessories;

    public Smartphone(UUID id, String name, BigDecimal price, int quantityAvailable, Color color, int batteryCapacity) {
        super(id, name, price, quantityAvailable);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.accessories = new ArrayList<>();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
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

