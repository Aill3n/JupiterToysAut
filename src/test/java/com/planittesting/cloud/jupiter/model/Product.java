package com.planittesting.cloud.jupiter.model;

import org.openqa.selenium.WebElement;

import java.math.BigDecimal;

public class Product {

    String name;
    BigDecimal price;
    WebElement productElement;
    WebElement shopButtonElement;


    public Product(String name, BigDecimal price, WebElement productElement, WebElement shopButtonElement) {
        this.name = name;
        this.price = price;
        this.productElement = productElement;
        this.shopButtonElement = shopButtonElement;
    }

    public WebElement getProductElement() {
        return productElement;
    }

    public void setProductElement(WebElement productElement) {
        this.productElement = productElement;
    }

    public WebElement getShopButtonElement() {
        return shopButtonElement;
    }

    public void setShopButtonElement(WebElement shopButtonElement) {
        this.shopButtonElement = shopButtonElement;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
