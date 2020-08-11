package nz.co.trademe.tests.manager;


import nz.co.trademe.tests.cucumber.TestContext;
import nz.co.trademe.tests.pages.*;
import nz.co.trademe.tests.webelement.FindWebElement;

public class PageObjectManager {

    private TestContext testContext;
    private HomePage homePage;
    private FindWebElement findWebElement;


    public PageObjectManager(TestContext context) {
        testContext = context;
    }

    public FindWebElement getFindWebElement(){

        return (findWebElement == null) ? findWebElement = new FindWebElement(testContext) : findWebElement;

    }

    public HomePage getHomePage(){
        return (homePage == null) ? homePage = new HomePage(testContext) : homePage;
    }

}
