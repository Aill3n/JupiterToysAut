package com.planittesting.cloud.jupiter.utility;

import java.math.BigDecimal;
import java.util.List;

public record ProductTestData(

        String name,
        BigDecimal price,
        int quantity) {
    public BigDecimal expectedSubtotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public static BigDecimal calculateTotal(List<ProductTestData> products) {
        return products.stream()
                .map(ProductTestData::expectedSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
