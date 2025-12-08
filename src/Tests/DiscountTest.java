package Tests;

import service.DiscountCalculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DiscountTest {

    public static void main(String[] args) {
        testAmountDiscount();
        testTimeDiscount();
    }

    private static void testAmountDiscount() {
        System.out.println("=== testAmountDiscount ===");
        DiscountCalculator discountCalculator = new DiscountCalculator();
        BigDecimal price1 = new BigDecimal(20000);
        BigDecimal discounted1 = discountCalculator.calculatePriceWithAmountDiscount(price1);
        System.out.println("Original: " + price1 + " | Expected 20% discount: " + discounted1);
        BigDecimal price2 = new BigDecimal(7000);
        BigDecimal discounted2 = discountCalculator.calculatePriceWithAmountDiscount(price2);
        System.out.println("Original: " + price2 + " | Expected 10% discount: " + discounted2);
        BigDecimal price3 = new BigDecimal(3000);
        BigDecimal discounted3 = discountCalculator.calculatePriceWithAmountDiscount(price3);
        System.out.println("Original: " + price3 + " | Expected no discount: " + discounted3);
        System.out.println();
    }

    private static void testTimeDiscount() {
        System.out.println("=== testTimeDiscount ===");
        DiscountCalculator discountCalculator = new DiscountCalculator();
        BigDecimal price = new BigDecimal(1000);
        LocalDateTime morning = LocalDateTime.of(2025, 12, 8, 10, 0);
        BigDecimal morningDiscount = discountCalculator.calculatePriceWithTimeDiscount(price, morning);
        System.out.println("Time: " + morning.getHour() + " | Expected 5% discount: " + morningDiscount);
        LocalDateTime afternoon = LocalDateTime.of(2025, 12, 8, 16, 0);
        BigDecimal afternoonDiscount = discountCalculator.calculatePriceWithTimeDiscount(price, afternoon);
        System.out.println("Time: " + afternoon.getHour() + " | Expected no discount: " + afternoonDiscount);
        System.out.println();
    }
}