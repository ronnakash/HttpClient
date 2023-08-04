package client;

import mock.NestedObject;
import util.MockServerUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import providers.DefaultXmlBodyReader;
import providers.DefaultXmlBodyWriter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.TestUtils.makeBody;

public class XmlBodyWriterTest {
    private static MockServerUtils mockServerUtils;

//    @BeforeAll
//    public static void setUp() {
//        mockServerUtils = new MockServerUtils();
//        mockServerUtils.startServer();
//    }
//
//    @AfterAll
//    public static void tearDown() {
//        mockServerUtils.stopServer();
//    }

    @Test
    public void testXmlBodyWriter() {
        ClientConfig clientConfig = new ClientConfig()
                .register(new DefaultXmlBodyReader<>())
                .register(new DefaultXmlBodyWriter<>());
        Client client = JerseyClientBuilder.createClient(clientConfig);

        NestedObject requestBody = makeBody();

        boolean result = client
                .target("http://localhost:9090/test/xml/request")
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(requestBody, MediaType.APPLICATION_XML))
                .readEntity(new GenericType<>() {});

        assertTrue(result);
    }
}
