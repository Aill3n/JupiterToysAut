package com.planittesting.cloud.jupiter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        try {
            WebElement errorValidation = driver.findElement(emailErrorLocator);
            return errorValidation.getText();
        } catch (NoSuchElementException e) {
            return "";
        }
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
        try {
            WebElement forenameValidation = driver.findElement(forenameErrorLocator);
            return forenameValidation.getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public String getMessageErrorMessage() {
        try {
            WebElement messageValidation = driver.findElement(messageErrorLocator);
            return messageValidation.getText();
        } catch (NoSuchElementException e) {
            return "";
        }
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