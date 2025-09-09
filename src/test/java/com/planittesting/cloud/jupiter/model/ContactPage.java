package com.planittesting.cloud.jupiter.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ContactPage extends BasePage {

    // Buttons
    private final By submitButtonLocator = By.className("btn-primary");

    // Form Fields
    private final By forenameFieldLocator = By.id("forename");
    private final By emailFieldLocator = By.id("email");
    private final By messageFieldLocator = By.id("message");

    // Error Messages
    private final By forenameErrorLocator = By.id("forename-err");
    private final By emailErrorLocator = By.id("email-err");
    private final By messageErrorLocator = By.id("message-err");

    // Submission message
    private final By loadingModalLocator = By.className("modal-header");
    private final By submissionTextLocator = By.className("alert-success");

    public ContactPage(WebDriver driver) {
        super(driver);
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
        List<WebElement> elements = driver.findElements(submitButtonLocator);
        if (!elements.isEmpty()) {
            elements.getFirst().click();
        }
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

    public String getSubmissionText() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingModalLocator));
        List<WebElement> elements = driver.findElements(submissionTextLocator);
        return !elements.isEmpty() ? elements.getFirst().getText().trim() : "";
    }
}