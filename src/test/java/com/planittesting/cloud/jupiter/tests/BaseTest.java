package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.utility.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.logging.Logger;

public class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());
    protected String baseUrl = "https://jupiter.cloud.planittesting.com/#/home";

    @BeforeEach
    public void testSetUp() {
        logger.info("Initialing the WebDriver.");
        driver = Browser.CHROME.driver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        logger.info("Navigating to URL: " + baseUrl);
        driver.navigate().to(baseUrl);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing WebDriver.");
            driver.quit();
        }
    }
}

