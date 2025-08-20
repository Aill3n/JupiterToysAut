package com.planittesting.cloud.jupiter.utility;

import java.util.EnumMap;

public class ItemPrice {

    private static final EnumMap<ShopItem, Double> shopItemMap;

    static {
        shopItemMap = new EnumMap<>(ShopItem.class);
        assignPrices();
    }

    private static void assignPrices() {
        shopItemMap.put(ShopItem.TEDDY_BEAR, 19.99);
        shopItemMap.put(ShopItem.STUFFED_FROG, 10.99);
        shopItemMap.put(ShopItem.HANDMADE_DOLL, 10.99);
        shopItemMap.put(ShopItem.FLUFFY_BUNNY, 9.99);
        shopItemMap.put(ShopItem.SMILEY_BEAR, 14.99);
        shopItemMap.put(ShopItem.FUNNY_COW, 10.99);
        shopItemMap.put(ShopItem.VALENTINE_BEAR, 14.99);
        shopItemMap.put(ShopItem.SMILEY_FACE, 9.99);
    }

    public static double getExpectedPrice(ShopItem item) {
        return shopItemMap.get(item);
    }

    public static String formatItemName(ShopItem item){
        return item
                .name()
                .trim()
                .toLowerCase()
                .replace("_"," ");
    }
}
