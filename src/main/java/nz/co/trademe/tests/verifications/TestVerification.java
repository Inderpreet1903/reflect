package nz.co.trademe.tests.verifications;

import jakarta.ws.rs.core.Response;
import nz.co.trademe.tests.connections.ServiceClient;
import nz.co.trademe.tests.reponses.CategoriesResponse;
import nz.co.trademe.tests.reponses.MotorsResponse;
import nz.co.trademe.tests.reponses.categoriesResponseData.Subcategories;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nz.co.trademe.tests.reponses.motorsResponseData.List;

import java.util.Arrays;


public class TestVerification {

    private static final Logger logger = LoggerFactory.getLogger(TestVerification.class);

    private Response response;
    private ServiceClient serviceClient = new ServiceClient();

    public void verifyTheResponseCode(String responseCode) {
        Assert.assertEquals(responseCode, String.valueOf(response.getStatus()));
    }

    public void getMotorSearch() {
        response = serviceClient.getMotorsJson();
    }

    public int getMotorsCount() {
        MotorsResponse motorsResponse = response.readEntity(MotorsResponse.class);
        return motorsResponse.TotalCount;
    }

    public java.util.List<Subcategories> getAllMotorMake() {
        CategoriesResponse categoriesResponse = response.readEntity(CategoriesResponse.class);
        return Arrays.asList(categoriesResponse.Subcategories);
    }

    public void getAllCategories() {
        response = serviceClient.getUsedCarsMake();
    }

    public java.util.List<List> getAllUsedMotorsListings() {
        MotorsResponse motorsResponse = response.readEntity(MotorsResponse.class);
        return Arrays.asList(motorsResponse.List);
    }
}
