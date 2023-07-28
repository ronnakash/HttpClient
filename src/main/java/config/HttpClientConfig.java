package config;

import org.glassfish.jersey.client.ClientConfig;

public class HttpClientConfig extends ClientConfig {

	public HttpClientConfig() {
		super();
	}

	public HttpClientConfig(ClientConfig config) {
		super(config);
	}

}
