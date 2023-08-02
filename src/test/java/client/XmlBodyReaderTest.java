package client;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.Test;
import providers.DefaultXmlBodyReader;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlBodyReaderTest {

    @Test
    public void testXmlBodyReader() {

        ClientConfig clientConfig = new ClientConfig().register(new DefaultXmlBodyReader<>());
        Client client = JerseyClientBuilder.createClient(clientConfig);

        List<List<Integer>>  result = client
                .target("http://localhost:9090/test/xml")
                .request(MediaType.APPLICATION_XML)
                .get(new GenericType<>() {});

        List<Integer> arr = Arrays.asList(1,2,3);

        List<List<Integer>> expected = Arrays.asList(arr, arr, arr);

        assertEquals(expected, result);
    }

}
