package client;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

public class HttpClient {

    private final Client client;
    HttpClientConfiguration config;

    public HttpClient(Client client) {
        this.client = client;
        this.config = new HttpClientConfiguration(client.getConfiguration());
    }

    public void close() {
        this.client.close();
    }

    public WebTarget target(String uri) {
        return this.client.target(uri);
    }

    public WebTarget target(URI uri) {
        return this.client.target(uri);
    }

    public WebTarget target(UriBuilder uriBuilder) {
        return this.client.target(uriBuilder);
    }

    public WebTarget target(Link link) {
        return this.client.target(link);
    }

    public SSLContext getSslContext() {
        return this.client.getSslContext();
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.client.getHostnameVerifier();
    }

    public Configuration getConfiguration() {
        return config;
    }

}
