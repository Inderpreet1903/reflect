package jp.co.reflect.tests.manager;


import jp.co.reflect.tests.cucumber.TestContext;
import jp.co.reflect.tests.pages.HomePage;
import jp.co.reflect.tests.webelement.FindWebElement;

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
