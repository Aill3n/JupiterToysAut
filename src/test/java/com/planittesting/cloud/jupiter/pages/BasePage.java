package com.planittesting.cloud.jupiter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    // Locators - Contact Page
    private final By contactMenuLocator = By.className("icon-envelope");
    private final By emailFieldLocator = By.id("email");
    // Locators - Login Page
    private final By loginMenuLocator = By.id("nav-login");
    private final By userNameLoginLocator = By.id("loginUserName");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public ContactPage openContactPage() {
        WebElement contactMenu = driver.findElement(contactMenuLocator);
        contactMenu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldLocator));
        return new ContactPage(driver);
    }

    public LoginPage openLoginWindow() {
        WebElement loginMenu = driver.findElement(loginMenuLocator);
        loginMenu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLoginLocator));
        return new LoginPage(driver);
    }
}
