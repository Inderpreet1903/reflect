package jp.co.reflect.tests.manager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import jp.co.reflect.tests.appium.AppiumSetup;
import jp.co.reflect.tests.cucumber.TestContext;
import jp.co.reflect.tests.enums.DriverType;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;

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
            case IOS:
                driver = createIphoneDriver();
                break;
            case ANDROID:
                driver = createAndroidDriver();
                break;
        }

        if (driver == null) {
            Assert.fail("Unable to initiate driver");
        }
//        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver createSafariDriver() {
        driver = new SafariDriver();
        return driver;
    }

    public DriverType getDeviceType() {
        String platformName = System.getProperty("properties.browser").toLowerCase();
        switch (platformName) {
            case "chrome":
                return DriverType.CHROME;
            case "safari":
                return DriverType.SAFARI;
            case "android":
                return DriverType.ANDROID;
            case "ios":
                return DriverType.IOS;
            default:
                throw new RuntimeException("Platform Name Key value in System.properties is not matching : " + platformName);
        }

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

    private WebDriver createIphoneDriver() {

        File appDir = new File(System.getProperty("user.dir"), "./app");

        File app = new File(appDir, System.getProperty("appium.appTest"));

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", System.getProperty("appium.deviceName"));
        caps.setCapability("platformName", System.getProperty("appium.platformName"));
        caps.setCapability("automationName", System.getProperty("appium.automationName"));
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
        caps.setCapability("udid", System.getProperty("appium.udid"));
        AppiumSetup.serviceBuilder();

        return new IOSDriver(AppiumSetup.getAppiumService(), caps);

    }

    private AppiumDriver createAndroidDriver() {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", System.getProperty("appium.deviceName"));
        caps.setCapability("avd", System.getProperty("appium.deviceName"));
        caps.setCapability("platformName", System.getProperty("appium.platformName"));
        caps.setCapability("platformVersion", System.getProperty("appium.platformVersion"));
        caps.setCapability("appActivity", System.getProperty("appium.appActivity"));
        caps.setCapability("automationName", System.getProperty("appium.automationName"));
        caps.setCapability("udid", System.getProperty("appium.udid"));
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        caps.setCapability("adbExecTimeout", 90000);
        caps.setCapability("fullReset", false);
        //Download the chromedrive which will be there a matching version of selected android device
        caps.setCapability("chromedriverExecutable", System.getProperty("user.dir") + "/./drivers/chromedriver");
        AppiumSetup.serviceBuilder();
        return new AndroidDriver(AppiumSetup.getAppiumService(), caps);

    }

    public DriverType getDriverType() {
        return driverType;
    }

}

