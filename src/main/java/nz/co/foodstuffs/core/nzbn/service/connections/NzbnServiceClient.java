package nz.co.foodstuffs.core.nzbn.service.connections;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.Assert.fail;


public class NzbnServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(NzbnServiceClient.class);

    private WebTarget servicenzbn;

    /*	Building connection with service	*/
    public NzbnServiceClient() {
        try {
            // make a client with the json stuff enabled
            Client client = ClientBuilder.newClient();

            // build an nzbnService...
            servicenzbn = client.target(System.getProperty("endpoint.nzbnServiceEndpoint"));

        } catch (Exception e) {
            logger.debug(e.getMessage());
            fail("Unable to build the nzbn service client");
        }
    }

    public Response getHealthStatus() {

        try {
            servicenzbn = servicenzbn.path("health");
            logger.info(servicenzbn.getUri().toString());

            return servicenzbn.request().get();

        } catch (Exception e) {
            logger.debug(e.getMessage());
            fail("Unable to get balance");
        }
        return null;
    }
}
