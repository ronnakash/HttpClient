package WebTarget;

import client.HttpClient;
import config.HttpClientConfig;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyWebTarget;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpWebTarget extends JerseyWebTarget {

	public HttpWebTarget(UriBuilder uriBuilder, HttpClient that) {
		super(uriBuilder, that);
	}

	public HttpWebTarget(UriBuilder uriBuilder, HttpClientConfig clientConfig) {
		super(uriBuilder, clientConfig);
	}

	public HttpWebTarget(String uri, HttpClient httpClient) {
		super();
	}

	public HttpWebTarget(URI uri, HttpClient httpClient) {
		this(UriBuilder.fromUri(uri, httpClient));
	}

	protected HttpWebTarget(UriBuilder uriBuilder, HttpClientConfig clientConfig) {
		assert(clientConfig != null);

		this.targetUri = uriBuilder;
		this.config = new HttpClientConfig(clientConfig);
	}

}
