package jp.co.reflect.tests.manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import jp.co.reflect.tests.cucumber.TestContext;
import jp.co.reflect.tests.enums.DriverType;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;

public class TestDriverManager {

    private WebDriver driver;
    private DriverType driverType;

    private TestContext testContext;

    public TestDriverManager(TestContext context) {
        this.testContext = context;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private WebDriver createDriver() {
        driverType = getDeviceType();
        switch (driverType) {
            case CHROME:
                driver = createChromeDriver();
                break;
            case SAFARI:
                driver = createSafariDriver();
                break;
        }

        if (driver == null) {
            Assert.fail("Unable to initiate driver");
        }
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver createSafariDriver() {
        driver = new SafariDriver();
        return driver;
    }

    public DriverType getDeviceType() {
        String platformName = System.getProperty("properties.browser").toLowerCase();
        if (platformName.equals("chrome")) return DriverType.CHROME;
        else if (platformName.equals("safari")) return DriverType.SAFARI;
        else
            throw new RuntimeException("Platform Name Key value in System.properties is not matching : " + platformName);
    }

    public void closeDriver() {
        if (driver != null) {
            driver.close();
        }
    }

    private WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions desiredCapabilities = new ChromeOptions();
//        desiredCapabilities.addArguments("headless", "window-size=1920,1080");
        desiredCapabilities.addArguments("--no-sandbox");
        desiredCapabilities.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(desiredCapabilities);
        return driver;
    }

}

