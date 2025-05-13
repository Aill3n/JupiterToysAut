package jupiter_toys.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By contactMenuLocator = By.className("icon-envelope");
    private final By emailFieldLocator = By.id("email");
    private final By emailErrorLocator = By.id("email-err");

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        WebElement errorValidation = driver.findElement(emailErrorLocator);
        return errorValidation.getText();
    }
}
