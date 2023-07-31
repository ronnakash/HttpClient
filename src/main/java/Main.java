import client.ObjectMapperContextResolver;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.lang.module.Configuration;
import java.util.List;

public class Main {

	public static void main(String[] args){
		try {
			System.out.println("hello!");
			Client client = new
					JerseyClientBuilder()
					.register(new ObjectMapperContextResolver())
					.build();

			List<List<Integer>> result = client
					.target("http://localhost:9090/test/request")
					.request(MediaType.APPLICATION_JSON_TYPE)
					.get(new GenericType<>(){});

			System.out.println(result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
