package com.planittesting.cloud.jupiter.tests;

import com.planittesting.cloud.jupiter.model.BasePage;
import com.planittesting.cloud.jupiter.utility.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected String baseUrl = "https://jupiter.cloud.planittesting.com/#/home";
    protected BasePage basePage;

    @BeforeEach
    public void testSetUp() {
        driver = Browser.CHROME.driver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.navigate().to(baseUrl);
        this.basePage = new BasePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

