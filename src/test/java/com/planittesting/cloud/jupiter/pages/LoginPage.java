package com.planittesting.cloud.jupiter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By loginMenuLocator = By.id("nav-login");
    private final By userNameLoginLocator = By.id("loginUserName");
    private final By passwordLoginLocator = By.id("loginPassword");
    private final By buttonLogin = By.className("btn-primary");
    private final By userLoggedInLocator = By.className("user");

    public void openLoginWindow() {
        WebElement loginMenu = driver.findElement(loginMenuLocator);
        loginMenu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLoginLocator));
    }

    public void populateLoginParameters(String userName, String password) {
        WebElement userNameField = driver.findElement(userNameLoginLocator);
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement passwordField = driver.findElement(passwordLoginLocator);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitLoginForm() {
        WebElement loginButton = driver.findElement(buttonLogin);
        loginButton.click();
    }

    public String getUsernameLoggedIn() {
        List<WebElement> elements = driver.findElements(userLoggedInLocator);
        return !elements.isEmpty() ? elements.getFirst().getText() : "";
    }
}
