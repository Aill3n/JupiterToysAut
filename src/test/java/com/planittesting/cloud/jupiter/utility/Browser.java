package com.planittesting.cloud.jupiter.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.function.Supplier;

/**
 * This is for selecting which browser to use
 * A convenience class for likely choices, though
 * currently only using Chrome
 */
public enum Browser {
    CHROME(ChromeDriver::new),
    FIREFOX(FirefoxDriver::new),
    EDGE(EdgeDriver::new);

    private final Supplier<WebDriver> driver;

    Browser(Supplier<WebDriver> driver) {
        this.driver = driver;
    }

    public WebDriver driver() {
        return driver.get();
    }
}
