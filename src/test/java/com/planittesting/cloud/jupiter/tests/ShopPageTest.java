package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.pages.ShopPage;
import com.planittesting.cloud.jupiter.utility.Toy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopPageTest extends BaseTest {

    @Test
    public void validateItemPrice() {
        // Step 1: From the home page go to the shop page
        ShopPage shopPage = basePage.openShopPage();

        // Step 2: Validate a given price for a given product title. For example given Teddy Bear validate that the price is 12.99
        String expectedValue = shopPage.getExpectedPrice(Toy.FLUFFY_BUNNY);
        String actualValue = shopPage.getItemPrice(Toy.FLUFFY_BUNNY);

        assertEquals(expectedValue, actualValue, "Validating price found for product: " + Toy.FLUFFY_BUNNY.name());
    }
}
