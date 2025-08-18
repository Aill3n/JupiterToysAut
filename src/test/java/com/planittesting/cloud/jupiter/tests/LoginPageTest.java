package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoginPageTest extends BaseTest {

    private static final String USERNAME = "anyvaluewilldo";
    private static final String PASSWORD = "letmein";

    @Test
    public void loginWithValidCredentialsTest() {
        // Step 1: From the home page click the login dialogue
        LoginPage loginPage = basePage.openLoginWindow();

        // Step 2: Enter username and password
        loginPage.populateLoginParameters(USERNAME, PASSWORD);

        // Step 3: Click the Login button
        loginPage.submitLoginForm();

        // Step 4: Verify that the username appears in the menu bar
        String actualUser = loginPage.getUsernameLoggedIn();
        assertEquals(USERNAME, actualUser, "Validating expected user name");
    }

    @Test
    public void logoutAfterValidLoginTest() {
        // Step 1: From the home page go to the contact page
        basePage.openContactPage();

        // Step 2: Click the login button
        LoginPage loginPage = basePage.openLoginWindow();

        // Step 3: In the login dialog enter username as anyvaluewilldo and password as letmein
        loginPage.populateLoginParameters(USERNAME, PASSWORD);
        loginPage.submitLoginForm();
        String actualUser = loginPage.getUsernameLoggedIn();
        assertEquals(USERNAME, actualUser, "Validating expected user name");

        // Step 4: Click the logout menu
        loginPage.openLogoutWindow();

        // Step 5: Click the logout button
        loginPage.logOut();

        // Step 6: Validate that the username is not displayed
        String actualValue = loginPage.getUsernameLoggedIn();
        assertNotEquals(USERNAME, actualValue, "Validating username is no longer displayed.");
    }
}
