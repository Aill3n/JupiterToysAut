package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.pages.LoginPage;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(LoginPageTest.class.getName());

    private static final String USERNAME = "anyvaluewilldo";
    private static final String PASSWORD = "letmein";

    @Test
    public void loginWithValidCredentialsTest() {

        LoginPage loginPage = new LoginPage(driver);

        // Step 1: From the home page click the login dialogue
        loginPage.openLoginWindow();
        logger.info("Opened the login dialogue.");

        // Step 2: Enter username and password
        loginPage.populateLoginParameters(USERNAME, PASSWORD);

        // Step 3: Click the Login button
        loginPage.submitLoginForm();
        logger.info("User logged in.");

        // Step 4: Verify that the username appears in the menu bar
        String actualUser = loginPage.getUsernameLoggedIn();
        assertEquals(USERNAME, actualUser, "Validating expected user name");
    }

}
