package com.planittesting.cloud.jupiter.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShopPage extends BasePage {

    private final By cartCounterLocator = By.className("cart-count");
    private final By buyButtonLocator = By.className("btn-success");
    private final By productNameLocator = By.className("product-title");
    private final By productPriceLocator = By.className("product-price");
    private final By productContainerLocator = By.className("product");


    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public List<Product> getProducts() {
        return driver.findElements(productContainerLocator)
                .stream()
                .filter(element -> !element.findElements(productNameLocator).isEmpty()
                        && !element.findElements(productPriceLocator).isEmpty())
                .map(this::instantiateProducts)
                .collect(Collectors.toList());
    }

    public Optional<BigDecimal> getProductPriceByName(String productName) {
        return getProducts()
                .stream()
                .filter(product -> product.getName().equalsIgnoreCase(productName.trim()))
                .map(Product::getPrice)
                .findFirst();
    }

    public Product instantiateProducts(WebElement productElement) {
        String productName = productElement.findElement(productNameLocator).getText();
        String priceText = productElement.findElement(productPriceLocator).getText().substring(1);
        BigDecimal price = new BigDecimal(priceText);
        WebElement shopButtonElement = productElement.findElement(buyButtonLocator);

        return new Product(productName, price, productElement, shopButtonElement);
    }

    public Optional<Product> findFirstProductByPrice(BigDecimal price) {
        return getProducts()
                .stream()
                .filter(product -> product.getPrice().equals(price))
                .findFirst();
    }

    public void clickBuyButton(Product product) {
        WebElement buyButton = product.getShopButtonElement();
        buyButton.click();
    }

    public Integer getCartItemCount() {
        List<WebElement> cartElements = driver.findElements(cartCounterLocator);
        return !cartElements.isEmpty() ? Integer.parseInt(cartElements.getFirst().getText()) : 0;
    }
}