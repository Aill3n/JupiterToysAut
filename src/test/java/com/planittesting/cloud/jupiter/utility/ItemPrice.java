package com.planittesting.cloud.jupiter.utility;

import java.util.EnumMap;

public class ItemPrice{

    private static final EnumMap<Product, Double> ProductMap;

    static {
        ProductMap = new EnumMap<>(Product.class);
        initializePrices();
    }

    private static void initializePrices() {
        ProductMap.put(Product.TEDDY_BEAR, 12.99);
        ProductMap.put(Product.STUFFED_FROG, 10.99);
        ProductMap.put(Product.HANDMADE_DOLL, 10.99);
        ProductMap.put(Product.FLUFFY_BUNNY, 9.99);
        ProductMap.put(Product.SMILEY_BEAR, 14.99);
        ProductMap.put(Product.FUNNY_COW, 10.99);
        ProductMap.put(Product.VALENTINE_BEAR, 14.99);
        ProductMap.put(Product.SMILEY_FACE, 9.99);
    }

    public static double getExpectedPrice(Product item) {
        return ProductMap.get(item);
    }

    public static int getIndex(Product item) {
        int index = item.ordinal();
        return (index+1);
    }
}