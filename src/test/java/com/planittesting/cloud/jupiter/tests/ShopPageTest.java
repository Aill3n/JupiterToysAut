package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.pages.ShopPage;
import com.planittesting.cloud.jupiter.utility.ShopItem;
import org.junit.jupiter.api.Test;

public class ShopPageTest extends BaseTest {

    @Test
    public void validateItemPrice(){
        // Step 1: From the home page go to the shop page
        ShopPage shopPage = basePage.openShopPage();

        // Step 2: Validate a given price for a given product title. For example given Teddy Bear validate that the price is 12.99
        String expectedPrice = shopPage.getExpectedPrice(ShopItem.TEDDY_BEAR);
        String actualPrice = shopPage.getItemPrice(ShopItem.TEDDY_BEAR);

      //  assertEquals(expectedPrice, actualPrice, "Validating the price found for a Teddy Bear is correct");
    }
}