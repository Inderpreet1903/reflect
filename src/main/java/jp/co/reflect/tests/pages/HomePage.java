package jp.co.reflect.tests.pages;


import io.appium.java_client.AppiumDriver;
import jp.co.reflect.tests.cucumber.TestContext;
import jp.co.reflect.tests.enums.DriverType;
import jp.co.reflect.tests.helper.ThreadSleep;
import jp.co.reflect.tests.manager.TestDriverManager;
import jp.co.reflect.tests.webelement.FindWebElement;
import org.junit.Assert;
import org.openqa.selenium.By;

public class HomePage {

    TestContext testContext;
    TestDriverManager testDriverManager;
    private FindWebElement find;

    public HomePage(TestContext context) {
        testContext = context;
        this.find = testContext.getPageObjectManager().getFindWebElement();
        testDriverManager = testContext.getTestDriverManager();
    }

    public void selectLanguageOption() {
        switch (testDriverManager.getDriverType()) {
            case ANDROID:
            case IOS:
                find.webElement(By.xpath("//a[@href='#menu']")).click();
                ThreadSleep.For(2);
                find.webElement(By.xpath("//span[text()='Language']")).click();
                break;
            default:
                if (find.webElement(By.xpath("//button[contains(text(),'Choose Language')]")).isDisplayed()) {
                    find.webElement(By.xpath("//button[contains(text(),'Choose Language')]")).click();
                    ThreadSleep.For(3);
                } else {
                    Assert.fail("The language drop down is not available");
                }
                break;
        }
    }

    public void verifyTheLanguageOptions() {
        switch (testDriverManager.getDriverType()) {
            case ANDROID:
            case IOS:
                Assert.assertEquals("Validating the first item in language selection is correct:", "Original", find.webElement(By.xpath("//a[text()='Original']")).getAttribute("text"));
                Assert.assertEquals("Validating the second item in language selection is correct:", "Thai", find.webElement(By.xpath("//a[text()='Thai']")).getAttribute("text"));
                Assert.assertEquals("Validating the third item in language selection is correct:", "Japanese", find.webElement(By.xpath("//a[text()='Japanese']")).getAttribute("text"));
                Assert.assertEquals("Validating the forth item in language selection is correct:", "English", find.webElement(By.xpath("//a[text()='English']")).getAttribute("text"));
                break;
            default:
                Assert.assertEquals("Validating the first item in language selection is correct:", "Original", find.webElements(By.xpath("//button[@class='dropdown-item']")).get(0).getText());
                Assert.assertEquals("Validating the second item in language selection is correct:", "Thai", find.webElements(By.xpath("//button[@class='dropdown-item']")).get(1).getText());
                Assert.assertEquals("Validating the third item in language selection is correct:", "Japanese", find.webElements(By.xpath("//button[@class='dropdown-item']")).get(2).getText());
                Assert.assertEquals("Validating the forth item in language selection is correct:", "English", find.webElements(By.xpath("//button[@class='dropdown-item']")).get(3).getText());
                break;
        }
    }

    public void selectLanguage(String language) {
        switch (testDriverManager.getDriverType()) {
            case ANDROID:
            case IOS:
                find.webElement(By.xpath("//a[text()='" + language + "']")).click();
                break;
            default:
                if (find.webElements(By.xpath("//button[contains(text(),'" + language + "')]")).size() < 1) {
                    find.webElement(By.xpath("//button[contains(text(),'Choose Language')]")).click();
                }
                find.webElement(By.xpath("//button[contains(text(),'" + language + "')]")).click();
                break;
        }
        ThreadSleep.For(10);
    }

    public void clickNextPage() {
        if (testDriverManager.getDriverType() == DriverType.CHROME || testDriverManager.getDriverType() == DriverType.SAFARI) {
            testDriverManager.getDriver().switchTo().defaultContent();
            find.webElement(By.xpath("//button[@aria-label='Next']")).click();
            ThreadSleep.For(7);
        }
    }

    public void clickOnButton(String btnText) {
        if (testDriverManager.getDriverType() == DriverType.ANDROID || testDriverManager.getDriverType() == DriverType.IOS) {
            find.webElement(By.xpath("//a[@href='#menu']")).click();
            find.webElement(By.xpath("//a[contains(text(),'" + btnText + "')]")).click();
        } else {
            find.webElement(By.xpath("//button[text() = '" + btnText + "']")).click();
        }
    }

    public void writeAReflection() {
        ThreadSleep.For(5);
        find.webElement(By.xpath("//*[@id='dateInput']")).click();
        if (testDriverManager.getDriverType() == DriverType.ANDROID || testDriverManager.getDriverType() == DriverType.IOS) {
            ((AppiumDriver) testDriverManager.getDriver()).hideKeyboard();
        }
        find.webElement(By.xpath("//*[@id='dateInput']")).sendKeys("2020/09/24");
        find.webElement(By.xpath("//label[text()='Pick a day to write a diary about. *']")).click();
        ThreadSleep.For(1);
        find.webElement(By.xpath("//*[@id='q11']")).click();
        ThreadSleep.For(1);
        find.webElement(By.xpath("//*[@id='q24']")).click();
        ThreadSleep.For(1);
        find.webElement(By.xpath("//*[@id='q33']")).click();
        ThreadSleep.For(1);
        if (testDriverManager.getDriverType() == DriverType.ANDROID || testDriverManager.getDriverType() == DriverType.IOS) {
            find.webElements(By.xpath("//button[@type='submit']")).get(1).click();
        } else {
            find.webElement(By.xpath("//label[text()='Pick a day to write a diary about. *']")).submit();
        }
        ThreadSleep.For(8);
    }

    public void writeAMessage() {
        ThreadSleep.For(5);
        find.webElement(By.xpath("//*[@id='textclear']")).click();
        find.webElement(By.xpath("//*[@id='textclear']")).sendKeys("Text added to send message");
        ThreadSleep.For(2);
        find.webElement(By.xpath("//*[text()='Submit']")).click();
        ThreadSleep.For(2);
    }

    public void selectPage(String page) {
        if (testDriverManager.getDriverType() == DriverType.ANDROID || testDriverManager.getDriverType() == DriverType.IOS) {
            find.webElement(By.xpath("//a[@href='#menu']")).click();
            ThreadSleep.For(2);
            find.webElement(By.xpath("//a[text()='" + page + "']")).click();
            ThreadSleep.For(5);
        }
    }
}
