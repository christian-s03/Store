package Tests;

import service.DiscountCalculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DiscountTest {
    public static void main(String[] args) {
        DiscountCalculator calculator = new DiscountCalculator();

        BigDecimal price1 = new BigDecimal(12000);
        BigDecimal priceAfterAmount1 = calculator.calculatePriceWithAmountDiscount(price1);
        System.out.println("Starting price: " + price1 + ", after amount discount: " + priceAfterAmount1);

        BigDecimal price2 = new BigDecimal(7000);
        BigDecimal priceAfterAmount2 = calculator.calculatePriceWithAmountDiscount(price2);
        System.out.println("Starting price: " + price2 + ", after amount discount: " + priceAfterAmount2);

        BigDecimal price3 = new BigDecimal(4000);
        BigDecimal priceAfterAmount3 = calculator.calculatePriceWithAmountDiscount(price3);
        System.out.println("Starting price: " + price3 + ", after amount discount: " + priceAfterAmount3);

        LocalDateTime morning = LocalDateTime.of(2025, 11, 27, 10, 0);
        BigDecimal price4 = new BigDecimal(5000);
        BigDecimal priceAfterTime1 = calculator.calculatePriceWithTimeDiscount(price4, morning);
        System.out.println("Starting price: " + price4 + ", after time discount (10:00): " + priceAfterTime1);

        LocalDateTime afternoon = LocalDateTime.of(2025, 11, 27, 16, 0);
        BigDecimal price5 = new BigDecimal(5000);
        BigDecimal priceAfterTime2 = calculator.calculatePriceWithTimeDiscount(price5, afternoon);
        System.out.println("Starting price: " + price5 + ", after time discount (16:00): " + priceAfterTime2);
    }
}