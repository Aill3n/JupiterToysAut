package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.model.ShopPage;
import com.planittesting.cloud.jupiter.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopPageTest extends BaseTest {

    @Test
    public void validateItemPriceTest() {
        // Step 1: From the home page go to the shop page
        ShopPage shopPage = basePage.openShopPage();

        // Step 2: Validate a given price for a given product title. For example given Teddy Bear validate that the price is 12.99
        String fluffyBunny = "Fluffy Bunny";
        Product product = shopPage.filterProduct(p -> p.getName().equals(fluffyBunny));

        assertEquals(fluffyBunny, product.getName(), "Name match.");
        assertEquals(new BigDecimal("8.99"), product.getPrice(), "Product price.");
    }

    @Test
    public void validateNumberOfItemsInCartTest() {
        // Step 1: From the home page go to the shop page
        ShopPage shopPage = basePage.openShopPage();

        // Step 2: Buy the first product you find with a given price.
        // Smiley Bear $14.99
        Integer initialCartCount = shopPage.getCartItemCount();
        Product product = shopPage.filterProduct(p -> p.getPrice().equals(new BigDecimal("13.99")));

        product.clickBuyButton();

        // Step 3: Validate that the cart menu displays 1
        Integer actualQuantityInCart = shopPage.getCartItemCount();
        assertEquals(initialCartCount + 1, actualQuantityInCart, "Cart count.");
    }
}