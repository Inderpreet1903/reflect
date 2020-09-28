package jp.co.reflect.tests.stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import jp.co.reflect.tests.cucumber.TestContext;
import jp.co.reflect.tests.enums.DriverType;
import jp.co.reflect.tests.manager.TestDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Hooks {

    WebDriver driver;
    TestContext testContext;
    TestDriverManager testDriverManager;

    public Hooks(TestContext context) {
        testContext = context;
        testDriverManager = testContext.getTestDriverManager();
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        driver = testDriverManager.getDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (testDriverManager.getDriverType() != DriverType.ANDROID) {
            byte[] imageBytes = ((TakesScreenshot) testDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(imageBytes, "image/png", "image");
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        testDriverManager.closeDriver();
    }

}
