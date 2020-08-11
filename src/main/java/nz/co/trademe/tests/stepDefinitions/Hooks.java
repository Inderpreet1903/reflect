package nz.co.trademe.tests.stepDefinitions;

//import com.saucelabs.saucerest.SauceREST;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import nz.co.trademe.tests.cucumber.TestContext;
import nz.co.trademe.tests.manager.TestDriverManager;
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
        if(!scenario.getName().contains("api")) {
            driver = testDriverManager.getDriver();
        }
    }


    @After
    public void afterScenario(Scenario scenario) {

        testDriverManager.closeDriver();

    }


    private static void failedScenario(Scenario scenario) {

//        if (TestDriver.getProperties().getProperty("execution.type").equals("local")) {
//
//            byte[] imageBytes = TestDriver.browser().saveScreenshot();
//            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
//
//            try {
//                BufferedImage bImage = ImageIO.read(bis);
//                ImageIO.write(bImage, "png", new File("C:/screenshots/" + scenario.getName() + ".png"));
//            } catch (Exception e) {
//
//                LogUtil.log("Folder Not found");
//            }
//
//        }
    }


    private static void updateSauceLabs(Scenario scenario) {
//        SauceLabs browser = (SauceLabs) TestDriver.browser();
//        SauceREST sauce;
//        sauce = SauceLabsUtilities.getSauceRestClient(browser.getUserName(), browser.getPassword());
//
//        HashMap<String, Object> updates = new HashMap<>();
//        updates.put("name", scenario.getName());
//        updates.put("build", SauceLabsUtilities.getBuildName());
//
//        if (!scenario.isFailed()) {
//            updates.put("passed", true);
//        } else {
//            updates.put("passed", false);
//        }
//
//        sauce.updateJobInfo(TestDriver.browser().getSessionId(), updates);
    }
}
