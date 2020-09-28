package jp.co.reflect.tests.webelement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import jp.co.reflect.tests.cucumber.TestContext;
import jp.co.reflect.tests.enums.Wait;
import jp.co.reflect.tests.manager.TestDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;


public class FindWebElement {

    private static final By subFrame = By.xpath("//iframe[contains(@name, 'uxtabiframe')]");
    private final By loadingIndicator = By.xpath("//div[@class='LoadingIndicator']");
    private FluentWait<WebDriver> wait;
    private WebDriverWait busyIndicatorWait;
    private WebDriverWait extendedWait;
    private TestContext testContext;
    private TestDriverManager testDriverManager;
    private WebDriver driver;


    public FindWebElement(TestContext context) {
        this.testContext = context;
        this.testDriverManager = testContext.getTestDriverManager();
        this.driver = testDriverManager.getDriver();

        wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofMillis(100))
            .ignoring(NoSuchElementException.class);
    }

    private static final Logger logger = LoggerFactory.getLogger(FindWebElement.class);

    public WebElement webElement(By elemLocator) {

        logger.info("Trying to find element " + elemLocator);

        WebElement element = wait.until(d -> d.findElement(elemLocator));

        highlightElement(element);

        logger.info("Found element " + elemLocator);

        return element;
    }

    public WebElement webElement(By elemLocator, Wait waitOptions) {

        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

        WebElement element = null;

        logger.info("Trying to find element " + elemLocator);

        if (waitOptions.equals(Wait.TILLNOTBUSY)) {

            element = wait.until(d -> d.findElement(elemLocator));

        } else {
            if (waitOptions.equals(Wait.TOBEVISIBLE)) {

                wait.until(d -> d.findElement(elemLocator).isDisplayed());
                wait.until(d -> d.findElement(elemLocator).isEnabled());
                element = wait.until(d -> d.findElement(elemLocator));

            } else {
                if (waitOptions.equals(Wait.TOBELNINDICATOR)) {
                    wait.until(d ->
                    {
                        logger.info("Busy Indicator Found");
                        return driver.findElements(loadingIndicator).size() <= 0;
                    });

                    wait.until(ExpectedConditions.visibilityOfElementLocated(elemLocator));
                    wait.until(d -> d.findElement(elemLocator).isEnabled());
                    element = wait.until(d -> d.findElement(elemLocator));

                }
            }
        }

        String jsHighLighter = "arguments[0].style.border='1px dotted green'";

        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript(jsHighLighter, element);
        }

        logger.info("Found element " + elemLocator);

        return element;

    }

    public WebElement displayedElement(By elemLocator) {

        List<WebElement> elements = webElements(elemLocator);

        if (elements.size() > 0) {
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    return element;
                }
            }
        }

        elements.forEach(element -> System.out.println(element.getText()));

        throw new ElementNotVisibleException("No Visible element has been located");
    }

    public WebElement findElementByXpath(String xpath)
    {
        WebElement element = null;
        for(int i=0; i<=2;i++){
            try {
                wait.withMessage(xpath);
                wait.until((ExpectedCondition<Boolean>) d -> (driver.findElement(By.xpath(xpath)).isDisplayed()));
                element = driver.findElement(By.xpath(xpath));
                highlightElement(element);
                break;
            }catch(StaleElementReferenceException se){
                System.out.println(se.getMessage());
            }
        }
        return element;
    }

    public List<WebElement> findElementsByXpath(String xpath)
    {
        wait.withMessage(xpath);
        return driver.findElements(By.xpath(xpath));
    }

    public List<WebElement> webElements(By elemLocator) {


        logger.info("Trying to find elements " + elemLocator);

        List<WebElement> elements = wait.until(d -> d.findElements(elemLocator));

        if (elements.size() > 0) {
            logger.info("Elements found " + elemLocator + " is [" + elements.size() + "]");
        } else {
            logger.info("No elements found " + elemLocator);
        }
        return elements;
    }


    private void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public void scrollDown(int times) {
        int numTries = 0;
//        if (isAndroid) {
//            while (wait.until(d -> (List<MobileElement>) d.findElements(element)).size() == 0) {
        for (int i = 0; i < times; i++) {
            int heightScreen = testDriverManager.getDriver().manage().window().getSize().height;
            int midScreen = heightScreen / 2;
            int swipeAmount = heightScreen / 4;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.press(PointOption.point(0, midScreen))
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1500)))
                .moveTo(PointOption.point(0, swipeAmount))
                .release().perform();
        }
    }

}
