import providers.DefaultJsonBodyReader;
import providers.JsonBodyReader;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class Main {

	//TODO:
	// 1. test multiple requests using same client with different response types
	// 2. try to figure out if we can know if we need concrete class or generic class when making request - seems to work, test further

	public static void main(String[] args){
		try {
			Client client = new JerseyClientBuilder()
					.register(new DefaultJsonBodyReader<>())
					.build();

			String result = client
					.target("http://localhost:9090/test/request")
					.request(MediaType.APPLICATION_JSON_TYPE)
					.get(new GenericType<>() {});

			System.out.println(result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
