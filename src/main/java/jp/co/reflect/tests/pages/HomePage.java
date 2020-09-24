package jp.co.reflect.tests.pages;


import jp.co.reflect.tests.cucumber.TestContext;
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
        if (find.webElement(By.xpath("//button[contains(text(),'Choose Language')]")).isDisplayed()) {
            find.webElement(By.xpath("//button[contains(text(),'Choose Language')]")).click();
            ThreadSleep.For(3);
        } else {
            Assert.fail("The language drop down is not available");
        }
    }

    public void verifyTheLanguageOptions() {
        Assert.assertEquals("Validating the first item in language selection is correct:", "Original", find.webElements(By.xpath("//button[@class='dropdown-item']")).get(0).getText());
        Assert.assertEquals("Validating the second item in language selection is correct:", "Thai", find.webElements(By.xpath("//button[@class='dropdown-item']")).get(1).getText());
        Assert.assertEquals("Validating the third item in language selection is correct:", "Japanese", find.webElements(By.xpath("//button[@class='dropdown-item']")).get(2).getText());
        Assert.assertEquals("Validating the forth item in language selection is correct:", "English", find.webElements(By.xpath("//button[@class='dropdown-item']")).get(3).getText());
    }

    public void selectLanguage(String language) {
        if (find.webElements(By.xpath("//button[contains(text(),'" + language + "')]")).size() < 1) {
            find.webElement(By.xpath("//button[contains(text(),'Choose Language')]")).click();
        }
        find.webElement(By.xpath("//button[contains(text(),'" + language + "')]")).click();
        ThreadSleep.For(10);
    }

    public void clickNextPage() {
        testDriverManager.getDriver().switchTo().defaultContent();
        find.webElement(By.xpath("//button[@aria-label='Next']")).click();
        ThreadSleep.For(7);
    }

    public void clickOnButton(String btnText) {
        find.webElement(By.xpath("//button[text() = '" + btnText + "']")).click();
    }

    public void writeAReflection() {
        find.webElement(By.xpath("//*[@id='dateInput']")).click();
        find.webElement(By.xpath("//*[@id='dateInput']")).sendKeys("2020/09/24");
        find.webElement(By.xpath("//label[text()='Pick a day to write a diary about. *']")).click();
        ThreadSleep.For(2);
        find.webElement(By.xpath("//*[@id='q11']")).click();
        find.webElement(By.xpath("//*[@id='q24']")).click();
        find.webElement(By.xpath("//*[@id='q33']")).click();
        find.webElement(By.xpath("//label[text()='Pick a day to write a diary about. *']")).submit();
        ThreadSleep.For(5);
    }

    public void writeAMessage() {
        find.webElement(By.xpath("//*[@id='textclear']")).click();
        find.webElement(By.xpath("//*[@id='textclear']")).sendKeys("Text added to send message");
        find.webElement(By.xpath("//*[@id='textclear']")).submit();
        ThreadSleep.For(2);
    }
}
