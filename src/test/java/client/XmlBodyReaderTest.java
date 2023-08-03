package client;

import mock.MockServerUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import providers.DefaultXmlBodyReader;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlBodyReaderTest {
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
    public void testXmlBodyWriter() {

        ClientConfig clientConfig = new ClientConfig().register(new DefaultXmlBodyReader<>());
        Client client = JerseyClientBuilder.createClient(clientConfig);

        List<List<Integer>> result = client
                .target("http://localhost:9090/test/xml/response")
                .request(MediaType.APPLICATION_XML)
                .get(new GenericType<>() {});

        List<Integer> arr = Arrays.asList(1, 2, 3);
        List<List<Integer>> expected = Arrays.asList(arr, arr, arr);
        assertEquals(expected, result);
    }
}
