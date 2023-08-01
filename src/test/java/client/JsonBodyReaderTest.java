package client;

import org.glassfish.jersey.client.JerseyClientBuilder;
import providers.DefaultJsonBodyReader;
import providers.JsonBodyReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class JsonBodyReaderTest {

    @Test
    public void testJsonBodyReader() {

        Client client = new JerseyClientBuilder()
                .register(new DefaultJsonBodyReader<>())
                .build();

        // Assuming the response is a String, change the GenericType accordingly if needed.
        String result = client
                .target("http://localhost:9090/test/request")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});

        assertEquals("[\"[1,2,3]\",\"[1,2,3]\",\"[1,2,3]\"]", result);
    }
}
