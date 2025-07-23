package com.planittesting.cloud.jupiter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ContactPage extends BasePage {

    // Menu
    private final By contactMenuLocator = By.className("icon-envelope");

    // Buttons
    private final By submitButtonLocator = By.className("btn-contact");

    // Form Fields
    private final By forenameFieldLocator = By.id("forename");
    private final By emailFieldLocator = By.id("email");
    private final By messageFieldLocator = By.id("message");

    // Error Messages
    private final By forenameErrorLocator = By.id("forename-err");
    private final By emailErrorLocator = By.id("email-err");
    private final By messageErrorLocator = By.id("message-err");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void openContactPage() {
        WebElement contactMenu = driver.findElement(contactMenuLocator);
        contactMenu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldLocator));
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(emailFieldLocator);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public String getEmailErrorMessage() {
        List<WebElement> elements = driver.findElements(emailErrorLocator);
        return !elements.isEmpty() ? elements.getFirst().getText() : "";
    }

    public void submitForm() {
        WebElement submitButton = driver.findElement(submitButtonLocator);
        submitButton.click();
    }

    public void enterForename(String forename) {
        WebElement forenameField = driver.findElement(forenameFieldLocator);
        forenameField.clear();
        forenameField.sendKeys(forename);
    }

    public String getForenameErrorMessage() {
        List<WebElement> elements = driver.findElements(forenameErrorLocator);
        return !elements.isEmpty() ? elements.getFirst().getText() : "";
    }

    public String getMessageErrorMessage() {
        List<WebElement> elements = driver.findElements(messageErrorLocator);
        return !elements.isEmpty() ? elements.getFirst().getText() : "";
    }

    public void enterMessage(String message) {
        WebElement messageField = driver.findElement(messageFieldLocator);
        messageField.clear();
        messageField.sendKeys(message);
    }

    public void populateMandatoryFields(String validEmail, String validForename, String validMessage) {
        enterEmail(validEmail);
        enterForename(validForename);
        enterMessage(validMessage);
    }

    public void waitUntilRequiredMessagesNotVisible() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(forenameErrorLocator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(emailErrorLocator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(messageErrorLocator));
    }
}