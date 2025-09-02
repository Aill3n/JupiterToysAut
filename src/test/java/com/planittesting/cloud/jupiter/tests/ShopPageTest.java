package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.pages.ShopPage;
import com.planittesting.cloud.jupiter.utility.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShopPageTest extends BaseTest {

    @Test
    public void validateItemPriceTest() {
        // Step 1: From the home page go to the shop page
        ShopPage shopPage = basePage.openShopPage();

        // Step 2: Validate a given price for a given product title. For example given Teddy Bear validate that the price is 12.99
        BigDecimal expectedValue = new BigDecimal("9.99");
        Optional<BigDecimal> actualValue = shopPage.getProductPriceByName("Fluffy Bunny");

        assertTrue(actualValue.isPresent());
        assertEquals(expectedValue, actualValue.get(), "Validating price found for product: ");
    }

    @Test
    public void validateNumberOfItemsInCartTest(){
        // Step 1: From the home page go to the shop page
        ShopPage shopPage = basePage.openShopPage();

        // Step 2: Buy the first product you find with a given price.
        // Smiley Bear $14.99
        BigDecimal price = new BigDecimal("14.99");
        Optional<Product> product = shopPage.findFirstProductByPrice(price);
        assertTrue(product.isPresent(), "Validating the price provided matches the price of the first element found.");

        shopPage.addProductToCart(product.get());

        // Step 3: Validate that the cart menu displays 1
        String actualQuantityInCart = shopPage.getCartItemCount();
        assertEquals("1",actualQuantityInCart, "Validating the Cart contains one item.");
    }
}