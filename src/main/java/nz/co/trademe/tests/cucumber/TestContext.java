package nz.co.trademe.tests.cucumber;


import nz.co.trademe.tests.manager.PageObjectManager;
import nz.co.trademe.tests.manager.TestDriverManager;

public class TestContext {
    private TestDriverManager testDriverManager;
    private PageObjectManager pageObjectManager;
    public ScenarioContext scenarioContext;

    public TestContext() {
        testDriverManager = new TestDriverManager(this);
        pageObjectManager = new PageObjectManager(this);
        scenarioContext = new ScenarioContext();
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public TestDriverManager getTestDriverManager() {
        return testDriverManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

}
