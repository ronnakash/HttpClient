package client;

import util.InnerObject;
import util.LeafObject;
import util.MockServerUtils;
import util.NestedObject;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import providers.DefaultXmlBodyReader;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlBodyReaderTest {
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
    public void testXmlBodyReader() {
        ClientConfig clientConfig = new ClientConfig()
                .register(new DefaultXmlBodyReader<>());
        Client client = JerseyClientBuilder.createClient(clientConfig);

        NestedObject result = client
                .target("http://localhost:9090/test/xml/response")
                .request(MediaType.APPLICATION_XML)
                .get(NestedObject.class);

        List<LeafObject> leafObjects = Arrays.asList(new LeafObject(1), new LeafObject(2), new LeafObject(3));
        InnerObject innerObject = new InnerObject(leafObjects);
        List<InnerObject> innerObjects = Arrays.asList(innerObject, innerObject, innerObject);
        NestedObject expected = new NestedObject(innerObjects);

        assertEquals(expected, result);
    }


}
