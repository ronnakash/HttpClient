package WebTarget;

import client.HttpClient;
import config.HttpClientConfig;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyWebTarget;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpWebTarget extends JerseyWebTarget {

	public HttpWebTarget(String uri, HttpClient httpClient) throws URISyntaxException {
		super(UriBuilder.fromUri(new URI(uri)), httpClient.getConfiguration());
	}

//	protected HttpWebTarget(UriBuilder uriBuilder, HttpClientConfig clientConfig) {
//		super(uriBuilder, clientConfig);
//	}
//
//	protected HttpWebTarget (JerseyWebTarget target) {
//		super(target.getUriBuilder(), target.getConfiguration());
//	}
//
//
//	public HttpWebTarget(UriBuilder uriBuilder, HttpWebTarget that) {
//		super(uriBuilder, that);
//	}
//
//	public HttpWebTarget(UriBuilder uriBuilder, ClientConfig clientConfig) {
//		super(uriBuilder, clientConfig);
//	}


}
