package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.pages.ContactPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ContactPageTest extends BaseTest {

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
        // Step 1: From the home page go to the contact page
        ContactPage contactPage = basePage.openContactPage();

        // Step 2: Populate the email field with thisisnotavalidemail
        contactPage.enterEmail(INVALID_EMAIL_TEXT);

        // Step 3: Verify that the email error is displayed with text:
        String actualErrorMessage = contactPage.getEmailErrorMessage();
        assertEquals(EXPECTED_INVALID_EMAIL_TEXT, actualErrorMessage, "Error message displayed when email is invalid");

    }

    @Test
    public void submitFormWithEmptyFieldsTest() {

        // Step 1: From the home page go to the contact page
        ContactPage contactPage = basePage.openContactPage();

        // Step 2: Click on the Submit button
        contactPage.submitForm();

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

        // Step 5: Validate that the mandatory errors are no longer displayed
        contactPage.waitUntilRequiredMessagesNotVisible();
    }

    @Test
    public void submitValidContactFormTest() {
        String expectedThanksMessage = String.format("Thanks %s, we appreciate your feedback.", VALID_FORENAME);

        // Step 1: From the home page go to the contact page
        ContactPage contactPage = basePage.openContactPage();


        // Step 2: Populate the mandatory fields
        contactPage.populateMandatoryFields(VALID_EMAIL, VALID_FORENAME, VALID_MESSAGE);

        // Step 3: Click on the Submit button
        contactPage.submitForm();

        // Step 4: Verify Thanks message text
        String actualThanksMessage = contactPage.getSubmissionText();
        assertEquals(expectedThanksMessage, actualThanksMessage, "Submission message correctly displayed");
    }
}