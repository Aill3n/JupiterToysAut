package com.planittesting.cloud.jupiter.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CartPage extends BasePage {

    CartPage(WebDriver driver) {
        super(driver);
    }

    private final By totalLocator = By.className("total");
    private final By quantityValueLocator = By.name("quantity");
    private final By cartItemLocator = By.className("cart-item");
    private final By nameLocator = By.cssSelector("tr.cart-item.ng-scope > td.ng-binding:nth-child(2)");
    private final By priceLocator = By.cssSelector("tr.cart-item.ng-scope > td.ng-binding:nth-child(3)");
    private final By subTotalLocator = By.cssSelector("tr.cart-item.ng-scope > td.ng-binding:nth-child(4)");

    public Cart getItemsInCart(List<Product> allProducts) {
        List<WebElement> cartItemContainer = driver.findElements(cartItemLocator);
        List<CartItem> cartItems = new ArrayList<>();

        for (WebElement element : cartItemContainer) {

            String name = element.findElement(nameLocator).getText();
            String priceText = element.findElement(priceLocator).getText().substring(1);
            BigDecimal price = new BigDecimal(priceText);
            String subtotalText = element.findElement(subTotalLocator).getText().substring(1);
            BigDecimal subtotal = new BigDecimal(subtotalText);

            Optional<CartItem> cartItem = createCartItem(allProducts, name, price, subtotal, getItemQuantity(element));
            cartItem.ifPresent(cartItems::add);
        }

        BigDecimal total = getCartTotal();
        return new Cart(cartItems, total);
    }

    public BigDecimal getCartTotal() {
        List<WebElement> totalInCart = driver.findElements(totalLocator);
        String TOTAL_PREFIX = "Total: ";
        if (!totalInCart.isEmpty()) {
            String totalText = totalInCart.getFirst().getText().substring(TOTAL_PREFIX.length());
            return new BigDecimal(totalText);
        }
        return BigDecimal.ZERO;
    }

    public int getItemQuantity(WebElement element) {
        List<WebElement> quantityList = element.findElements(quantityValueLocator);
        if (!quantityList.isEmpty()) {
            String quantity = quantityList.getFirst().getDomProperty("value");
            return Integer.parseInt(Objects.requireNonNull(quantity));
        }
        return 0;
    }

    public Optional<CartItem> createCartItem(List<Product> products, String productName, BigDecimal productPrice, BigDecimal subtotal, int quantity) {
        return products.stream().
                filter(product -> product.getName().equalsIgnoreCase(productName) && product.getPrice().equals(productPrice)).findFirst()
                .map(product -> new CartItem(quantity, product, subtotal));
    }
}