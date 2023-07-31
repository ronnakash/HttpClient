import providers.JsonProvider;
import providers.ObjectMapperContextResolver;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class Main {

	public static void main(String[] args){
		try {
			Client client = new JerseyClientBuilder()
//					.register(new ObjectMapperContextResolver())
					.register(new JsonProvider<>())
					.build();

			List<String> result = client
					.target("http://localhost:9090/test/request")
					.request(MediaType.APPLICATION_JSON_TYPE)
					.get(new GenericType<>() {});

			System.out.println(result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	private <T> T test(Class<T> clazz) {
		Client client = new JerseyClientBuilder()
//					.register(new ObjectMapperContextResolver())
				.register(new JsonProvider<>())
				.build();

		return client
				.target("http://localhost:9090/test/request")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get(new GenericType<>() {});

	}

}
