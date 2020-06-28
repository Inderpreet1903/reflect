package nz.co.foodstuffs.core.nzbn.service.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.foodstuffs.core.nzbn.service.api.NzbnDataVerification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NzbnCoreTestStepDefs {

    private static final Logger logger = LoggerFactory.getLogger(NzbnCoreTestStepDefs.class);
    private final NzbnDataVerification nzbnDataVerification = new NzbnDataVerification();

    @Then("I should get response with {string}")
    public void iShouldGetResponseWith(String responseCode) {
        nzbnDataVerification.verifyTheResponseCode(responseCode);
    }

    @When("I call API Endpoint request for health status")
    public void iCallAPIEndpointRequestForHealthStatus() {
        nzbnDataVerification.getServiceHealthStatus();
    }
}
