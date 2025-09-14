package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.model.*;
import com.planittesting.cloud.jupiter.utility.ProductTestData;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest extends BaseTest {

    private static final List<ProductTestData> TEST_PRODUCTS = List.of(
            new ProductTestData("Stuffed Frog", new BigDecimal("10.99"), 2),
            new ProductTestData("Fluffy Bunny", new BigDecimal("8.99"), 5),
            new ProductTestData("Valentine Bear", new BigDecimal("13.99"), 3)
    );

    @Test
    public void validateShoppingCartTotalsTest() {

        // Step 1: Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
        ShopPage shopPage = basePage.openShopPage();
        List<Product> allProducts = shopPage.getProducts();

        // Add items to cart
        TEST_PRODUCTS.forEach(testData -> {
            Product product = shopPage.filterProduct(p -> p.getName().equals(testData.name()));
            product.clickBuyButton(testData.quantity());
        });

        // Step 2: Go to the cart page
        Cart cart = basePage.openCartPage().getItemsInCart(allProducts);

        // Step 3: Verify the price for each product
        // &
        // Step 4: Verify that each product’s sub total = product price * quantity
        TEST_PRODUCTS.forEach(testData -> {
            CartItem item = cart.filterItems(cartItem -> cartItem.getProduct().getName().equals(testData.name())).getFirst();
            assertAll(testData.name(),
                    () -> assertEquals(testData.price(), item.getProduct().getPrice(), "Price"),
                    () -> assertEquals(testData.expectedSubtotal(), item.getSubtotal(), "Subtotal")
            );
        });

        // Step 5: Verify that total = sum(sub totals)
        assertEquals(ProductTestData.calculateTotal(TEST_PRODUCTS).setScale(2, RoundingMode.HALF_UP),
                cart.getTotal().setScale(2, RoundingMode.HALF_UP), "Total");
    }
}