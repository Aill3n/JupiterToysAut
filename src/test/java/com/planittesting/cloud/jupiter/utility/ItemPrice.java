package com.planittesting.cloud.jupiter.utility;

import java.math.BigDecimal;
import java.util.EnumMap;

public class ItemPrice {

    private static final EnumMap<Toy, BigDecimal> ProductMap;

    static {
        ProductMap = new EnumMap<>(Toy.class);
        initializePrices();
    }

    private static void initializePrices() {
        ProductMap.put(Toy.TEDDY_BEAR, new BigDecimal("12.99"));
        ProductMap.put(Toy.STUFFED_FROG, new BigDecimal("10.99"));
        ProductMap.put(Toy.HANDMADE_DOLL, new BigDecimal("10.99"));
        ProductMap.put(Toy.FLUFFY_BUNNY, new BigDecimal("9.99"));
        ProductMap.put(Toy.SMILEY_BEAR, new BigDecimal("14.99"));
        ProductMap.put(Toy.FUNNY_COW, new BigDecimal("10.99"));
        ProductMap.put(Toy.VALENTINE_BEAR, new BigDecimal("14.99"));
        ProductMap.put(Toy.SMILEY_FACE, new BigDecimal("9.99"));
    }

    // TODO: Consider using BigDecimal
    public static BigDecimal getExpectedPrice(Toy item) {
        return ProductMap.get(item);
    }

    public static int getIndex(Toy item) {
        int index = item.ordinal();
        return (index + 1);
    }
}