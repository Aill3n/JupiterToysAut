package jupiter_toys.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactPageTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(ContactPageTest.class.getName());

    @Test
    public void enterInvalidEmailForContactMenuTest() {

        WebElement contactMenu = driver.findElement(By.className("icon-envelope"));
        contactMenu.click();
        logger.info("Clicked on Contact menu.");

        // Add a wait here until the page is loaded?
        WebElement emailField = driver.findElement(By.id("email"));

        // Enter incorrect email
        String invalidEmailText = "thisisnotavalidemail";
        emailField.sendKeys(invalidEmailText);

        //Validate error message is displayed
        WebElement errorValidation = driver.findElement(By.id("email-err"));
        String invalidEmailErrorTextFound = errorValidation.getText();
        logger.info("Invalid email entered: " + invalidEmailText);

        //Should I use a soft assertion here, so test doesn't stop upon failure?
        String expectedInvalidEmailText = "Please enter a valid email";
        assertEquals(expectedInvalidEmailText,invalidEmailErrorTextFound, "Invalid email error message not displayed");
        logger.info("Validation of invalid email passed.");

        logger.info("Test passed.");
    }
}

