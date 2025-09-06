package com.planittesting.cloud.jupiter.model;

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
    private final By loginButtonLocator = By.className("btn-primary");
    private final By loggedInUserLocator = By.className("user");
    private final By logoutMenuLocator = By.id("nav-logout");
    private final By logoutButtonLocator = By.className("btn-success");
    private final By modalFooterLocator = By.className("modal-footer");

    public void populateLoginParameters(String userName, String password) {
        WebElement userNameField = driver.findElement(userNameLoginLocator);
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement passwordField = driver.findElement(passwordLoginLocator);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitLoginForm() {
        WebElement modal = driver.findElement(modalFooterLocator);
        WebElement loginButton = modal.findElement(loginButtonLocator);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginMenuLocator));
    }

    public String getUsernameLoggedIn() {
        List<WebElement> elements = driver.findElements(loggedInUserLocator);
        return !elements.isEmpty() ? elements.getFirst().getText() : "";
    }

    public void openLogoutWindow(){
        WebElement logoutMenu = driver.findElement(logoutMenuLocator);
        logoutMenu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalFooterLocator));
    }

    public void logOut(){
        WebElement modal = driver.findElement(modalFooterLocator);
        WebElement logoutButton = modal.findElement(logoutButtonLocator);
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(logoutMenuLocator));
    }
}
