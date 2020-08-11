package nz.co.trademe.tests.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.trademe.tests.cucumber.TestContext;
import nz.co.trademe.tests.helper.ThreadSleep;
import nz.co.trademe.tests.manager.TestDriverManager;
import nz.co.trademe.tests.pages.HomePage;
import nz.co.trademe.tests.webelement.FindWebElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class WebsiteStepdefs {

    private HomePage homePage;

    TestContext testContext;
    TestDriverManager testDriverManager;
    FindWebElement find;

    public WebsiteStepdefs(TestContext context) {

        testContext = context;
        this.homePage = testContext.getPageObjectManager().getHomePage();
        this.find = testContext.getPageObjectManager().getFindWebElement();
        testDriverManager = testContext.getTestDriverManager();
    }

    @Given("I open the website")
    public void iOpenTheWebsite() {
        testDriverManager.getDriver().navigate().to(System.getProperty("properties.url"));
    }

    @When("I goto motors tab and search for used cars")
    public void iGotoMotorsTabAndSearchForUsedCars() {
        homePage.searchForUsedCars();
    }

    @Then("I verify result listing")
    public void iVerifyResultListing() {
        int listings = homePage.getListingsCount();
        Assert.assertTrue("Total listed vehicles: " + listings, listings > 1);
    }

    @When("I goto motors tab and select advance search link")
    public void iGotoMotorsTabAndSelectAdvanceSearchLink() {
        homePage.gotoAdvanceSearchLinkOfMotors();
    }

    @And("I verify the result has make {string} in the dropdown")
    public void iVerifyTheResultHasMakeKiaInTheDropdown(String make) {
        Assert.assertTrue("The existence of Make: " + make, homePage.checkMakeExistInList(make));
    }

    @And("I select the first listing to verify the details exists")
    public void iSelectTheFirstListingToVerifyTheDetailsExists(DataTable dataTable) {
        ThreadSleep.For(5);
        List<WebElement> listingAttributes = homePage.getFirstListingDetails();
        if(listingAttributes!=null) {
            for (int i = 0; i < dataTable.asList().size(); i++) {
                String detail = dataTable.asList().get(i);
                Assert.assertTrue("Check the availability of attribute: " + detail, listingAttributes.stream().anyMatch(a -> a.getText().contains(detail)));
            }
        }else {
            Assert.fail("The attributes were not found");
        }

    }
}
