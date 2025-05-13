package jupiter_toys.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.logging.Logger;

public class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());
    protected String baseUrl = System.getProperty("base.url", "https://jupiter.cloud.planittesting.com/#/home");

    @BeforeEach
    public void testSetUp() {
        logger.info("Initialing the WebDriver.");
        driver = initialiseDriver(System.getProperty("browser", "chrome"));
        // Best practice to always maximize the window for screenshots
        driver.manage().window().maximize();
        // Opting to use an implicitlyWait over a sleep
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(300));
        // Navigate to Url
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

    private WebDriver initialiseDriver(String browser) {
        return switch (browser) {
            case "firefox" -> {
                logger.info("Using Firefox.");
                yield new FirefoxDriver();
            }
            case "safari" -> {
                logger.info("Using Safari.");
                yield new SafariDriver();
            }
            case "edge" -> {
                logger.info("Using Edge.");
                yield new EdgeDriver();
            }
            default -> new ChromeDriver();
        };
    }
}

