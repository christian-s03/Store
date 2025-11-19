package model;

import java.math.BigDecimal;

public class Electronics extends Product {
    public Electronics(int id, String productName, BigDecimal price, int quantityAvailable) {
        super(id, productName, price, quantityAvailable);
    }
}
