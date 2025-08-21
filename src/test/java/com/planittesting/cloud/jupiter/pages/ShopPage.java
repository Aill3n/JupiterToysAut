package com.planittesting.cloud.jupiter.pages;

import com.planittesting.cloud.jupiter.utility.ItemPrice;
import com.planittesting.cloud.jupiter.utility.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopPage extends BasePage {

    public ShopPage(WebDriver driver) {
        super(driver);}

    public String getExpectedPrice(Product item) {
        String expectedPrice = String.valueOf(ItemPrice.getExpectedPrice(item));
        return "$" + expectedPrice;
    }

    public String getItemPrice(Product item) {
        int productIndex = ItemPrice.getIndex(item);
        By productLocator = By.id("product-"+ productIndex);

        WebElement productItem = driver.findElement(productLocator);
        WebElement productPrice = productItem.findElement((By.className("product-price")));

        return productPrice.getText();
    }
}
