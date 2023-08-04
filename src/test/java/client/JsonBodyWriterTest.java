package client;

import mock.NestedObject;
import util.MockServerUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import providers.DefaultJsonBodyReader;
import providers.DefaultJsonBodyWriter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static util.TestUtils.makeBody;

public class JsonBodyWriterTest {
    private static MockServerUtils mockServer;

    @BeforeAll
    public static void setUp() {
        mockServer = new MockServerUtils();
        mockServer.startServer();
    }

    @AfterAll
    public static void tearDown() {
        mockServer.stopServer();
    }

    @Test
    public void testJsonBodyWriter() {
        ClientConfig clientConfig = new ClientConfig()
                .register(new DefaultJsonBodyReader<>())
                .register(new DefaultJsonBodyWriter<>());
        Client client = JerseyClientBuilder.createClient(clientConfig);

        NestedObject requestBody = makeBody();

        Response response = client
                .target("http://localhost:9090/test/json/request")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(requestBody, MediaType.APPLICATION_JSON));

        // Check the response status code
        int statusCode = response.getStatus();
        System.out.println("Response Status: " + statusCode);

        // Handle the case of a successful response
        if (statusCode == 200) {
            // Ensure the response body is not empty
            String responseBody = response.readEntity(String.class);
            assertTrue(responseBody != null && !responseBody.isEmpty(), "Response body is empty");

            // Optionally, you can assert the content of the XML response
            assertTrue(responseBody.contains("<result>SUCCESS</result>"));
        } else {
            // Handle the case of an error response (e.g., 4xx, 5xx)
            // Add appropriate assertions or error handling here
            fail("Received error response with status code: " + statusCode);
        }
    }

}
