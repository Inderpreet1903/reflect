package jp.co.reflect.tests.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.reflect.tests.cucumber.TestContext;
import jp.co.reflect.tests.manager.TestDriverManager;
import jp.co.reflect.tests.pages.HomePage;
import jp.co.reflect.tests.webelement.FindWebElement;


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

    @When("I select the language drop down")
    public void iSelectTheLanguageDropDown() {
        homePage.selectLanguageOption();
    }

    @Then("I verify the languages available")
    public void iVerifyTheLanguagesAvailable() {
        homePage.verifyTheLanguageOptions();
    }

    @Then("I choose language {string}")
    public void iChooseLanguageLanguage(String language) {
        homePage.selectLanguage(language);
    }

    @And("I choose next page to save data")
    public void iChooseNextPageToSaveData() {
        homePage.clickNextPage();
    }

    @When("I click on button {string}")
    public void iClickOnButtonWriteAReflection(String btnText) {
        homePage.clickOnButton(btnText);
    }

    @Then("I fill the form to submit")
    public void iFillTheFormToSubmit() {
        homePage.writeAReflection();
    }
}
