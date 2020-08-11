package nz.co.trademe.tests.pages;


import nz.co.trademe.tests.cucumber.TestContext;
import nz.co.trademe.tests.enums.DriverType;
import nz.co.trademe.tests.manager.TestDriverManager;
import nz.co.trademe.tests.webelement.FindWebElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    TestContext testContext;
    TestDriverManager testDriverManager;
    private FindWebElement find;

    public HomePage(TestContext context) {
        testContext = context;
        this.find = testContext.getPageObjectManager().getFindWebElement();
        testDriverManager = testContext.getTestDriverManager();
    }

    public void searchForUsedCars() {
        find.webElement(By.id("SearchTabs1_MotorsLink")).click();
        find.webElement(By.id("min-13")).sendKeys("500");
        find.findElementByXpath("//*[@id=\"sidebarSearch\"]/button").click();
    }

    public int getListingsCount() {
        return find.webElements(By.className("tmm-search-card-list-view")).size();
    }

    public void gotoAdvanceSearchLinkOfMotors() {
        find.webElement(By.id("SearchTabs1_MotorsLink")).click();
        find.webElement(By.id("AdvancedCarSearchLink")).click();
    }

    public boolean checkMakeExistInList(String make) {
        find.webElement(By.id("form_make")).click();
        if (find.webElements(By.xpath("//*[@id=\"form_make\"]/option")).size() > 0) {
            for (WebElement webElement : find.webElements(By.xpath("//*[@id=\"form_make\"]/option"))) {
                if (webElement.getText().contains(make)) {
                    webElement.click();
                    find.webElement(By.id("used_cars_reset_side_search")).click();
                    return true;
                }
            }
        }
        return false;
    }

    public List<WebElement> getFirstListingDetails() {
        if (find.webElements(By.className("tmm-search-card-list-view")).size() > 0) {
            find.webElements(By.className("tmm-search-card-list-view")).get(0).click();
            List<WebElement> motorAttributes = find.webElements(By.xpath("//*[@id=\"AttributesDisplay_attributesSection\"]/ul/li"));
            if (motorAttributes.size() > 0) {
                return motorAttributes;
            } else {
                Assert.assertFalse("Listing Details are missing", false);
            }
        }
        return null;
    }
}
