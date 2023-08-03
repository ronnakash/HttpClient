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

    public HttpWebTarget target(String uri) {
        return new HttpWebTarget(this.client.target(uri));
    }

    public HttpWebTarget target(URI uri) {
        return new HttpWebTarget(this.client.target(uri));
    }

    public HttpWebTarget target(UriBuilder uriBuilder) {
        return new HttpWebTarget(this.client.target(uriBuilder));
    }

//    public HttpWebTarget target(Link link) {
//        return this.client.target(link);
//    }

//    public Invocation.Builder invocation(Link link) {
//        return this.client.invocation(link);
//    }

    public SSLContext getSslContext() {
        return this.client.getSslContext();
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.client.getHostnameVerifier();
    }

    public Configuration getConfiguration() {
//        return this.client.getConfiguration();
        return config;
    }

//    public HttpClient property(String name, Object value) {
//        this.client.property(name, value);
//        return this;
//    }
//
//    public HttpClient register(Class<?> componentClass) {
//        this.client.register(componentClass);
//        return this;
//    }
//
//    public HttpClient register(Class<?> componentClass, int priority) {
//        this.client.register(componentClass, priority);
//        return this;
//    }
//
//    public HttpClient register(Class<?> componentClass, Class<?>... contracts) {
//        this.client.register(componentClass, contracts);
//        return this;
//    }
//
//    public HttpClient register(Class<?> componentClass, Map<Class<?>, Integer> contracts) {
//        this.client.register(componentClass, contracts);
//        return this;
//    }
//
//    public HttpClient register(Object component) {
//        this.client.register(component);
//        return this;
//    }
//
//    public HttpClient register(Object component, int priority) {
//        this.client.register(component, priority);
//        return this;
//    }
//
//    public HttpClient register(Object component, Class<?>... contracts) {
//        this.client.register(component, contracts);
//        return this;
//    }
//
//    public HttpClient register(Object component, Map<Class<?>, Integer> contracts) {
//        this.client.register(component, contracts);
//        return this;
//    }
}
