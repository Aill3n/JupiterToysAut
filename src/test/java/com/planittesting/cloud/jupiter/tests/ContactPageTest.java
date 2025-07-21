package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.pages.ContactPage;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ContactPageTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(ContactPageTest.class.getName());

    @Test
    public void enterInvalidEmailTest() {
        String invalidEmailText = "thisisnotavalidemail";
        String expectedInvalidEmailText = "Please enter a valid email";

        logger.info("Test Case 1 starting: Enter invalid contact email address");

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

        logger.info("Test Case 1 passed: Enter invalid contact email address");
    }

    @Test
    public void submitFormWithEmptyFieldsTest() {
        String expectedForenameRequiredMessage = "Forename is required";
        String expectedEmailRequiredMessage = "Email is required";
        String expectedMessageRequiredMessage = "Message is required";

        String validEmail = "test@planittesting.com";
        String validForename = "Test";
        String validMessage = "Our test bears won’t let a single bug through the toy aisle.";

        logger.info("Test Case 2 starting: Submit contact form with empty fields");

        ContactPage contactPage = new ContactPage(driver);

        // Step 1: From the home page go to the contact page
        contactPage.openContactPage();
        logger.info("Navigated to the Contact page.");

        // Step 2: Click on the Submit button
        contactPage.submitForm();
        logger.info("Clicked the Submit button with empty mandatory fields.");

        // Step 3: Validate that the mandatory errors for missing fields shows field 'is required' message
        String actualForenameErrorMessage = contactPage.getForenameErrorMessage();
        String actualEmailErrorMessage = contactPage.getEmailErrorMessage();
        String actualMessageErrorMessage = contactPage.getMessageErrorMessage();

        assertAll("Validation of messages displayed for required fields",
                () -> assertEquals(expectedForenameRequiredMessage, actualForenameErrorMessage, "Error message displayed when Forename is empty"),
                () -> assertEquals(expectedEmailRequiredMessage, actualEmailErrorMessage, "Error message displayed when Email is empty"),
                () -> assertEquals(expectedMessageRequiredMessage, actualMessageErrorMessage, "Error message displayed when Message is empty")
        );

        // Step 4: Populate mandatory fields
        contactPage.populateMandatoryFields(validEmail, validForename, validMessage);
        logger.info("Populated mandatory fields.");

        // Step 5: Validate that the mandatory errors are no longer displayed
        contactPage.waitUntilRequiredMessagesNotVisible();
        logger.info("Error messages for required fields are no longer shown.");
        logger.info("Test Case 2 passed: Submit form with empty fields");
    }
}

