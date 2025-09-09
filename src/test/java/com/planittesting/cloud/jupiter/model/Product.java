package com.planittesting.cloud.jupiter.model;

import org.openqa.selenium.WebElement;

import java.math.BigDecimal;

public class Product {

    String name;
    BigDecimal price;
    WebElement shopButtonElement;


    public Product(String name, BigDecimal price, WebElement shopButtonElement) {
        this.name = name;
        this.price = price;
        this.shopButtonElement = shopButtonElement;
    }

    public WebElement getShopButtonElement() {
        return shopButtonElement;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void clickBuyButton() {
        this.getShopButtonElement().click();
    }
}
