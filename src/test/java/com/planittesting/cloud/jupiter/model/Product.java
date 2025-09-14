package com.planittesting.cloud.jupiter.model;

import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

public class Product {

    String name;
    BigDecimal price;
    WebElement shopButtonElement;
    WebElement starsRatingElement;

    public Product(String name, BigDecimal price, WebElement shopButtonElement, WebElement starsRatingElement) {
        this.name = name;
        this.price = price;
        this.shopButtonElement = shopButtonElement;
        this.starsRatingElement = starsRatingElement;
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

    public WebElement getStarsRatingElement() {
        return starsRatingElement;
    }

    public void clickBuyButton(int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.getShopButtonElement().click();
        }
    }
}