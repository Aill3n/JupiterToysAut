package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest extends BaseTest {

    @Test
    public void validateShoppingCartTotalsTest() {

        // Step 1: Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
        ShopPage shopPage = basePage.openShopPage();
        List<Product> allProducts = shopPage.getProducts();

        String stuffedFrogName = "Stuffed Frog";
        String fluffyBunnyName = "Fluffy Bunny";
        String valentineBearName = "Valentine Bear";

        Product stuffedFrog = shopPage.filterProduct(p -> p.getName().equals(stuffedFrogName));
        Product fluffyBunny = shopPage.filterProduct(p -> p.getName().equals(fluffyBunnyName));
        Product valentineBear = shopPage.filterProduct(p -> p.getName().equals(valentineBearName));

        stuffedFrog.clickBuyButton(2);
        fluffyBunny.clickBuyButton(5);
        valentineBear.clickBuyButton(3);

        // Step 2: Go to the cart page
        CartPage cartPage = basePage.openCartPage();
        Cart cart = cartPage.getItemsInCart(allProducts);

        // Step 3: Verify the price for each product
        CartItem stuffedFrogCartItem = cart.filterItems(item -> item.getProduct().getName().equals(stuffedFrogName)).getFirst();
        CartItem fluffyBunnyCartItem = cart.filterItems(item -> item.getProduct().getName().equals(fluffyBunnyName)).getFirst();
        CartItem valentineBearCartItem = cart.filterItems(item -> item.getProduct().getName().equals(valentineBearName)).getFirst();

        BigDecimal stuffedFrogExpectedPrice = new BigDecimal("10.99");
        BigDecimal fluffyBunnyExpectedPrice = new BigDecimal("8.99");
        BigDecimal valentineBearExpectedPrice = new BigDecimal("13.99");

        int stuffedFrogExpectedQuantity = 2;
        int fluffyBunnyExpectedQuantity = 5;
        int valentineBearExpectedQuantity = 3;

        BigDecimal stuffedFrogExpectedSubtotal = stuffedFrogExpectedPrice.multiply(new BigDecimal(stuffedFrogExpectedQuantity));
        BigDecimal fluffyBunnyExpectedSubtotal = fluffyBunnyExpectedPrice.multiply(new BigDecimal(fluffyBunnyExpectedQuantity));
        BigDecimal valentineBearExpectedSubtotal = valentineBearExpectedPrice.multiply(new BigDecimal(valentineBearExpectedQuantity));

        assertAll(stuffedFrogName,
                () -> assertEquals(stuffedFrogExpectedPrice, stuffedFrogCartItem.getProduct().getPrice(),"Price: " + stuffedFrogCartItem.getProduct().getPrice()),
                () -> assertEquals(stuffedFrogExpectedSubtotal, stuffedFrogCartItem.getSubtotal(),"Subtotal: " + stuffedFrogCartItem.getSubtotal())
        );

        assertAll(fluffyBunnyName,
                () -> assertEquals(fluffyBunnyExpectedPrice, fluffyBunnyCartItem.getProduct().getPrice(), "Price: " + fluffyBunnyCartItem.getProduct().getPrice()),
                () -> assertEquals(fluffyBunnyExpectedSubtotal, fluffyBunnyCartItem.getSubtotal(), "Subtotal: " + fluffyBunnyCartItem.getSubtotal())
        );

        assertAll(valentineBearName,
                () -> assertEquals(valentineBearExpectedPrice, valentineBearCartItem.getProduct().getPrice(), "Price: " + valentineBearCartItem.getProduct().getPrice()),
                () -> assertEquals(valentineBearExpectedSubtotal, valentineBearCartItem.getSubtotal(), "Subtotal: " + valentineBearCartItem.getSubtotal())
        );

        // Step 5: Verify that total = sum(sub totals)
        BigDecimal cartExpectedTotal = valentineBearExpectedSubtotal.add(fluffyBunnyExpectedSubtotal).add(stuffedFrogExpectedSubtotal);
        BigDecimal cartActualTotal = cart.getTotal().setScale(2, RoundingMode.HALF_UP);
        assertEquals(cartExpectedTotal, cartActualTotal, "cart total");
    }
}