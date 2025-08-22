package com.planittesting.cloud.jupiter.pages;

import com.planittesting.cloud.jupiter.utility.ToyPrice;
import com.planittesting.cloud.jupiter.utility.Toy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

public class ShopPage extends BasePage {

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public String getExpectedPrice(Toy item) {
        BigDecimal price = ToyPrice.getExpectedPrice(item);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        return currencyFormatter.format(price);
    }

    public String getItemPrice(Toy item) {
        int toyIndex = ToyPrice.getIndex(item);
        By toyLocator = By.id("product-" + toyIndex);
        By priceLocator = By.className("product-price");

        List<WebElement> productFinder = driver.findElements(toyLocator);
        if (productFinder.isEmpty()) {
            return "";
        }

        WebElement productElement = productFinder.getFirst();

        List<WebElement> prices = productElement.findElements(priceLocator);
        return !prices.isEmpty() ? prices.getFirst().getText() : "";
    }
}
