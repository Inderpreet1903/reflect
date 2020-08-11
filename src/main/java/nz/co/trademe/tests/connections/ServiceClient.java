package nz.co.trademe.tests.connections;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.Assert.fail;


public class ServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(ServiceClient.class);

    private WebTarget service;

    /*	Building connection with service	*/
    public ServiceClient() {
        try {
            // make a client with the json stuff enabled
            Client client = ClientBuilder.newClient();

            // build the service...
            service = client.target(System.getProperty("endpoint.trademeServiceEndpoint"));

        } catch (Exception e) {
            logger.debug(e.getMessage());
            fail("Unable to build the service client");
        }
    }

    public Response getMotorsJson() {

        try {
            service = service.path("/Search/Motors/Used.json");
            logger.info(service.getUri().toString());

            return service.request().header("Authorization", System.getProperty("0Auth.token")).get();

        } catch (Exception e) {
            logger.debug(e.getMessage());
            fail("Unable to get Motors");
        }
        return null;
    }

    public Response getUsedCarsMake() {

        //Retrieve used car makes
        try {
            service = service.path("/Categories/UsedCars.json");
            logger.info(service.getUri().toString());

            return service.request().header("Authorization", System.getProperty("0Auth.token")).get();

        } catch (Exception e) {
            logger.debug(e.getMessage());
            fail("Unable to get Motors");
        }
        return null;
    }
}
