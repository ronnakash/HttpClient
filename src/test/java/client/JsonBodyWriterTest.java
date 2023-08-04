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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

        boolean result = client
                .target("http://localhost:9090/test/json/request")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(requestBody, MediaType.APPLICATION_JSON))
                .readEntity(new GenericType<>() {});

        assertTrue(result);
    }
}
