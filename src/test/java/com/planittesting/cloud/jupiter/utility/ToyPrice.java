package com.planittesting.cloud.jupiter.utility;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class ToyPrice {

    private static final EnumMap<Toy, BigDecimal> TOY_MAP;

    static {
        TOY_MAP = new EnumMap<>(Toy.class);
        initializePrices();
    }

    private static void initializePrices() {
        TOY_MAP.put(Toy.TEDDY_BEAR, new BigDecimal("12.99"));
        TOY_MAP.put(Toy.STUFFED_FROG, new BigDecimal("10.99"));
        TOY_MAP.put(Toy.HANDMADE_DOLL, new BigDecimal("10.99"));
        TOY_MAP.put(Toy.FLUFFY_BUNNY, new BigDecimal("9.99"));
        TOY_MAP.put(Toy.SMILEY_BEAR, new BigDecimal("14.99"));
        TOY_MAP.put(Toy.FUNNY_COW, new BigDecimal("10.99"));
        TOY_MAP.put(Toy.VALENTINE_BEAR, new BigDecimal("14.99"));
        TOY_MAP.put(Toy.SMILEY_FACE, new BigDecimal("9.99"));
    }

    public static BigDecimal getExpectedPrice(Toy item) {
        return TOY_MAP.get(item);
    }

    public static int getIndex(Toy item) {
        int index = item.ordinal();
        return (index + 1);
    }

    public static Optional<Toy> getFirstToyByPrice(BigDecimal price) {
        return TOY_MAP.entrySet().stream()
                .filter(toy -> toy.getValue().equals(price))
                .map(Map.Entry::getKey)
                .findFirst();
    }
}
