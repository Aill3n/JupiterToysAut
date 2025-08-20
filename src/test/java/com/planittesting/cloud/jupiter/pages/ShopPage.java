package com.planittesting.cloud.jupiter.pages;

import com.planittesting.cloud.jupiter.utility.ItemPrice;
import com.planittesting.cloud.jupiter.utility.ShopItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShopPage extends BasePage{

    private final By itemPriceLocator = By.className("ng-scope");
    private final By productTitleLocator = By.className("product-title");
    private final By productPriceLocator = By.className("product-price");

    public ShopPage(WebDriver driver) {
        super(driver);}

    public String getExpectedPrice(ShopItem item) {
        return String.valueOf(ItemPrice.getExpectedPrice(item));
    }

    public String getItemPrice(ShopItem item){

        String itemName = ItemPrice.formatItemName(item);

        WebElement productTitle = driver.findElement(productTitleLocator);

        List<WebElement> elements = driver.findElements(itemPriceLocator);

        WebElement nameFound = productTitle.findElement(By.className(itemName));
        WebElement priceFound = nameFound.findElement(By.className("product-price"));

        return priceFound.getText();
    }
}
