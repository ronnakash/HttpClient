package client;

import util.MockServerUtils;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import providers.DefaultJsonBodyReader;
import util.NestedObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestUtils.makeBody;

public class JsonBodyReaderTest {
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
    public void testJsonBodyReader() {

        Client client = new JerseyClientBuilder()
                .register(new DefaultJsonBodyReader<>())
                .build();

        NestedObject result = client
                .target("http://localhost:9090/test/json/response")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});

        NestedObject requestBody = makeBody();
        assertEquals(requestBody, result);
    }
}
