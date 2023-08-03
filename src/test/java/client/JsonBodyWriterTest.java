package client;

import mock.MockServerUtils;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonBodyWriterTest {
    private static MockServerUtils mockServerUtils;

    @BeforeAll
    public static void setUp() {
        mockServerUtils = new MockServerUtils();
        mockServerUtils.startServer();
    }

    @AfterAll
    public static void tearDown() {
        mockServerUtils.stopServer();
    }

    @Test
    public void testJsonBodyWriter() {
        ClientConfig clientConfig = new ClientConfig()
                .register(new DefaultJsonBodyReader<>())
                .register(new DefaultJsonBodyWriter<>());
        Client client = JerseyClientBuilder.createClient(clientConfig);

        List<Integer> arr = Arrays.asList(1, 2, 3);
        List<List<Integer>> requestBody = Arrays.asList(arr, arr, arr);

        List<List<Integer>> result = client
                .target("http://localhost:9090/test/json/request")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(requestBody, MediaType.APPLICATION_JSON))
                .readEntity(new GenericType<>() {});

        assertEquals(requestBody, result);
    }
}
