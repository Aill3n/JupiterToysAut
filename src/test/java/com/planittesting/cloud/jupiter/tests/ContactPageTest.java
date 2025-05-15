package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.pages.ContactPage;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactPageTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(ContactPageTest.class.getName());

    @Test
    public void enterInvalidEmailTest() {
        String invalidEmailText = "thisisnotavalidemail";
        String expectedInvalidEmailText = "Please enter a valid email";

        logger.info("Test starting: Enter invalid contact email address");

        ContactPage contactPage = new ContactPage(driver);

        // Step 1: From the home page go to the contact page
        contactPage.openContactPage();
        logger.info("Navigated to the Contact page.");

        // Step 2: Populate the email field with thisisnotavalidemail
        contactPage.enterEmail(invalidEmailText);
        logger.info("Entered invalid email: " + invalidEmailText);

        // Step 3: Verify that the email error is displayed with text:
        String actualErrorMessage = contactPage.getEmailErrorMessage();
        assertEquals(expectedInvalidEmailText, actualErrorMessage, "Error message displayed when email is invalid");

        logger.info("Test passed: Enter invalid contact email address");
    }
}

