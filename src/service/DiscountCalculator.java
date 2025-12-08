package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class DiscountCalculator {

    private static final BigDecimal DISCOUNT_10 = new BigDecimal(0.10);
    private static final BigDecimal DISCOUNT_20 = new BigDecimal(0.20);
    private static final BigDecimal DISCOUNT_5  = new BigDecimal(0.05);

    public BigDecimal calculatePriceWithAmountDiscount(BigDecimal price) {

        if (price.compareTo(new BigDecimal(10000)) > 0) {
            return price.subtract(price.multiply(DISCOUNT_20)).setScale(2, RoundingMode.HALF_UP);
        } else if (price.compareTo(new BigDecimal(5000)) > 0) {
            return price.subtract(price.multiply(DISCOUNT_10)).setScale(2, RoundingMode.HALF_UP);
        } else {
            return price.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public BigDecimal calculatePriceWithTimeDiscount(BigDecimal price, LocalDateTime dateTime) {
        int hour = dateTime.getHour();
        if (hour < 15) {
            return price.subtract(price.multiply(DISCOUNT_5)).setScale(2, RoundingMode.HALF_UP);
        } else {
            return price.setScale(2, RoundingMode.HALF_UP);
        }
    }
}
