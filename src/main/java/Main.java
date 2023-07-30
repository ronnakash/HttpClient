import client.ObjectMapperContextResolver;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import java.lang.module.Configuration;

public class Main {

	public static void main(String[] args){
		System.out.println("hello!");
		Client clientBuilder = new
				JerseyClientBuilder()
				.register(new ObjectMapperContextResolver())
				.build();

	}

}
