package com.planittesting.cloud.jupiter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    // Contact Page
    private final By contactMenuLocator = By.className("icon-envelope");
    // Login Page
    private final By loginMenuLocator = By.id("nav-login");
    // Shop Page
    private final By shopMenuLocator = By.id("nav-shop");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public ContactPage openContactPage() {
        WebElement contactMenu = driver.findElement(contactMenuLocator);
        contactMenu.click();
        return new ContactPage(driver);
    }

    public LoginPage openLoginWindow() {
        WebElement loginMenu = driver.findElement(loginMenuLocator);
        loginMenu.click();
        return new LoginPage(driver);
    }

    public ShopPage openShopPage(){
        WebElement shopPage = driver.findElement(shopMenuLocator);
        shopPage.click();
        return new ShopPage(driver);
    }
}
