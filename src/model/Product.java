package model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Product {
    protected UUID id;
    protected String productName;
    protected BigDecimal price;
    protected int quantityAvailable;

    public Product(UUID id, String productName, BigDecimal price, int quantityAvailable) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void decreaseQuantityProduct(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("The number of products to be reduced cannot be negative");
        }
        if (quantity > quantityAvailable) {
            throw new IllegalArgumentException("Not enough items in stock");
        }
        this.quantityAvailable -= quantity;
    }

    public void increaseQuantityProduct(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("The number of products to be increased cannot be negative");
        }
        if (quantity > quantityAvailable) {
            throw new IllegalArgumentException("Not enough items in stock");
        }
        this.quantityAvailable += quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && quantityAvailable == product.quantityAvailable &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price, quantityAvailable);
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + id +
                ", Product name ='" + productName +
                ", price=" + price +
                ", Available quantity=" + quantityAvailable +
                '}';
    }
}
