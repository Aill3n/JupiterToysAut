package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.pages.ContactPage;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ContactPageTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(ContactPageTest.class.getName());

    // Expected fields validation messages
    private static final String EXPECTED_FORENAME_REQUIRED_MESSAGE = "Forename is required";
    private static final String EXPECTED_EMAIL_REQUIRED_MESSAGE = "Email is required";
    private static final String EXPECTED_MESSAGE_REQUIRED_MESSAGE = "Message is required";

    // Invalid messages
    private static final String INVALID_EMAIL_TEXT = "thisisnotavalidemail";
    private static final String EXPECTED_INVALID_EMAIL_TEXT = "Please enter a valid email";

    // Valid field values
    private static final String VALID_EMAIL = "test@planittesting.com";
    private static final String VALID_FORENAME = "Test";
    private static final String VALID_MESSAGE = "Our test bears won’t let a single bug through the toy aisle.";

    @Test
    public void enterInvalidEmailTest() {

        ContactPage contactPage = new ContactPage(driver);

        // Step 1: From the home page go to the contact page
        contactPage.openContactPage();
        logger.info("Navigated to the Contact page.");

        // Step 2: Populate the email field with thisisnotavalidemail
        contactPage.enterEmail(INVALID_EMAIL_TEXT);
        logger.info("Entered invalid email: " + INVALID_EMAIL_TEXT);

        // Step 3: Verify that the email error is displayed with text:
        String actualErrorMessage = contactPage.getEmailErrorMessage();
        assertEquals(EXPECTED_INVALID_EMAIL_TEXT, actualErrorMessage, "Error message displayed when email is invalid");

    }

    @Test
    public void submitFormWithEmptyFieldsTest() {

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
                () -> assertEquals(EXPECTED_FORENAME_REQUIRED_MESSAGE, actualForenameErrorMessage, "Error message displayed when Forename is empty"),
                () -> assertEquals(EXPECTED_EMAIL_REQUIRED_MESSAGE, actualEmailErrorMessage, "Error message displayed when Email is empty"),
                () -> assertEquals(EXPECTED_MESSAGE_REQUIRED_MESSAGE, actualMessageErrorMessage, "Error message displayed when Message is empty")
        );

        // Step 4: Populate mandatory fields
        contactPage.populateMandatoryFields(VALID_EMAIL, VALID_FORENAME, VALID_MESSAGE);
        logger.info("Populated mandatory fields.");

        // Step 5: Validate that the mandatory errors are no longer displayed
        contactPage.waitUntilRequiredMessagesNotVisible();
        logger.info("Error messages for required fields are no longer shown.");
    }

    @Test
    public void submitValidContactFormTest() {

        String expectedThanksMessage = String.format("Thanks %s, we appreciate your feedback.", VALID_FORENAME);

        ContactPage contactPage = new ContactPage(driver);

        // Step 1: From the home page go to the contact page
        contactPage.openContactPage();
        logger.info("Navigated to the Contact page.");

        // Step 2: Populate the mandatory fields
        contactPage.populateMandatoryFields(VALID_EMAIL, VALID_FORENAME, VALID_MESSAGE);

        // Step 3: Click on the Submit button
        contactPage.submitForm();
        logger.info("Clicked the Submit button with mandatory fields populated.");

        // Step 4: Verify Thanks message text
        String actualThanksMessage = contactPage.getSubmissionText();
        assertEquals(expectedThanksMessage, actualThanksMessage, "Submission message correctly displayed");
    }
}