package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PcComponents {
    private List<Component> processors;
    private List<Component> ram;
    private List<Component> ssd;
    private List<Component> chargers;

    public PcComponents() {
        this.processors = new ArrayList<>();
        this.ram = new ArrayList<>();
        this.ssd = new ArrayList<>();
        this.chargers = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        processors.add(new Component(1, "Intel Core i5", BigDecimal.valueOf(449.99)));
        processors.add(new Component(2, "Intel Core i7", BigDecimal.valueOf(679.99)));
        processors.add(new Component(3, "AMD Ryzen 5", BigDecimal.valueOf(389.99)));

        ram.add(new Component(1, "Patriot 32 GB", BigDecimal.valueOf(1499.99)));
        ram.add(new Component(2, "Kingston 16 GB", BigDecimal.valueOf(1099.99)));
        ram.add(new Component(3, "Patriot 64 GB", BigDecimal.valueOf(1999.99)));

        ssd.add(new Component(1, "256GB", BigDecimal.valueOf(319.00)));
        ssd.add(new Component(2, "512GB", BigDecimal.valueOf(459.99)));
        ssd.add(new Component(3, "1TB", BigDecimal.valueOf(909.99)));

        chargers.add(new Component(1, "650W", BigDecimal.valueOf(84.99)));
        chargers.add(new Component(2, "750W", BigDecimal.valueOf(119.99)));
        chargers.add(new Component(3, "850W", BigDecimal.valueOf(139.99)));
    }

    public List<Component> getProcessors() {
        return processors;
    }

    public void setProcessors(List<Component> processors) {
        this.processors = processors;
    }

    public List<Component> getRam() {
        return ram;
    }

    public void setRam(List<Component> ram) {
        this.ram = ram;
    }

    public List<Component> getSsd() {
        return ssd;
    }

    public void setSsd(List<Component> ssd) {
        this.ssd = ssd;
    }

    public List<Component> getChargers() {
        return chargers;
    }

    public void setChargers(List<Component> chargers) {
        this.chargers = chargers;
    }

    public class Component extends Product {
        public Component(int id, String productName, BigDecimal productPrice) {
            super(id, productName, productPrice, 0);
        }
    }
}
