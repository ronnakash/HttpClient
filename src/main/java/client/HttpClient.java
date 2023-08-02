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

public class HttpClient implements Client {

    private final Client client;
    HttpClientConfiguration config;

    public HttpClient(Client client) {
        this.client = client;
        this.config = new HttpClientConfiguration(client.getConfiguration());
    }

    @Override
    public void close() {
        this.client.close();
    }

    @Override
    public WebTarget target(String uri) {
        return this.client.target(uri);
    }

    @Override
    public WebTarget target(URI uri) {
        return this.client.target(uri);
    }

    @Override
    public WebTarget target(UriBuilder uriBuilder) {
        return this.client.target(uriBuilder);
    }

    @Override
    public WebTarget target(Link link) {
        return this.client.target(link);
    }

    @Override
    public Invocation.Builder invocation(Link link) {
        return this.client.invocation(link);
    }

    @Override
    public SSLContext getSslContext() {
        return this.client.getSslContext();
    }

    @Override
    public HostnameVerifier getHostnameVerifier() {
        return this.client.getHostnameVerifier();
    }

    @Override
    public Configuration getConfiguration() {
        return this.client.getConfiguration();
    }

    @Override
    public Client property(String name, Object value) {
        this.client.property(name, value);
        return this;
    }

    @Override
    public Client register(Class<?> componentClass) {
        this.client.register(componentClass);
        return this;
    }

    @Override
    public Client register(Class<?> componentClass, int priority) {
        this.client.register(componentClass, priority);
        return this;
    }

    @Override
    public Client register(Class<?> componentClass, Class<?>... contracts) {
        this.client.register(componentClass, contracts);
        return this;
    }

    @Override
    public Client register(Class<?> componentClass, Map<Class<?>, Integer> contracts) {
        this.client.register(componentClass, contracts);
        return this;
    }

    @Override
    public Client register(Object component) {
        this.client.register(component);
        return this;
    }

    @Override
    public Client register(Object component, int priority) {
        this.client.register(component, priority);
        return this;
    }

    @Override
    public Client register(Object component, Class<?>... contracts) {
        this.client.register(component, contracts);
        return this;
    }

    @Override
    public Client register(Object component, Map<Class<?>, Integer> contracts) {
        this.client.register(component, contracts);
        return this;
    }
}
