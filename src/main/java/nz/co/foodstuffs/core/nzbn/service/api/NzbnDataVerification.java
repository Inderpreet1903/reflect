package nz.co.foodstuffs.core.nzbn.service.api;

import jakarta.ws.rs.core.Response;
import nz.co.foodstuffs.core.nzbn.service.connections.NzbnServiceClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NzbnDataVerification {

    private static final Logger logger = LoggerFactory.getLogger(NzbnDataVerification.class);

    private Response response;
    private NzbnServiceClient nzbnServiceClient = new NzbnServiceClient();

    public void getServiceHealthStatus() {
        response = nzbnServiceClient.getHealthStatus();
    }

    public void verifyTheResponseCode(String responseCode) {
        Assert.assertEquals(responseCode, String.valueOf(response.getStatus()));
    }

}
