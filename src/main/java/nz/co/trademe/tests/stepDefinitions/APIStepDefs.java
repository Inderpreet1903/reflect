package nz.co.trademe.tests.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.trademe.tests.reponses.categoriesResponseData.Subcategories;
import nz.co.trademe.tests.verifications.TestVerification;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.co.trademe.tests.reponses.motorsResponseData.List;


public class APIStepDefs {

    private static final Logger logger = LoggerFactory.getLogger(APIStepDefs.class);
    private final TestVerification testVerification = new TestVerification();

    @Then("I should get response with {string}")
    public void iShouldGetResponseWith(String responseCode) {
        testVerification.verifyTheResponseCode(responseCode);
    }

    @When("I call API Endpoint request for searching Motors")
    public void iCallAPIEndpointRequestForSearchingMotors() {
        testVerification.getMotorSearch();
    }

    @And("I verify the result has at least one listing")
    public void iVerifyTheResultHasAtLeastOneListing() {
        int count = testVerification.getMotorsCount();
        Assert.assertTrue("The list has records total: " + count, count > 0);
    }

    @And("I verify the result has make {string}")
    public void iVerifyTheResultHasMakeKia(String make) {
        java.util.List<Subcategories> allMake = testVerification.getAllMotorMake();
        int count = (int) allMake.stream().filter(a -> a.Name.contentEquals(make)).count();
        Assert.assertTrue("Make: " + make + " existence test", count > 0);
    }

    @When("I call API Endpoint request for used car makes")
    public void iCallAPIEndpointRequestForUsedCarMakes() {
        testVerification.getAllCategories();
    }

    @And("I verify the result has a listing with details")
    public void iVerifyTheResultHasAListingWithDetails() {
        java.util.List<List> motorsListings = testVerification.getAllUsedMotorsListings();
        List motorListing = motorsListings.get(1);
        logger.info("NumberPlate: "+motorListing.NumberPlate);
        logger.info("Odometer: "+motorListing.Odometer);
        logger.info("BodyStyle: "+motorListing.BodyStyle);
        logger.info("Seats: "+motorListing.Seats);
        logger.info("Fuel: "+motorListing.Fuel);
        logger.info("EngineSize: "+motorListing.EngineSize);
        logger.info("Transmission: "+motorListing.Transmission);
        logger.info("ImportHistory: "+motorListing.ImportHistory);
        logger.info("RegistrationExpires: "+motorListing.RegistrationExpires);
        logger.info("WofExpires: "+motorListing.WofExpires);
        logger.info("Model: "+motorListing.Model);
    }
}
