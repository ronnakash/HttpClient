package client;

import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Configuration;
import java.security.KeyStore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HttpClientBuilder {

    JerseyClientBuilder jerseyClientBuilder;
    HttpClientConfiguration configuration;

    public HttpClientBuilder(JerseyClientBuilder jerseyClientBuilder) {
        this.jerseyClientBuilder = jerseyClientBuilder;
    }

    public HttpClientBuilder withConfig(HttpClientConfiguration config) {
        this.jerseyClientBuilder.withConfig(config.getConfig());
        return this;
    }

    public HttpClientBuilder sslContext(SSLContext sslContext) {
        this.jerseyClientBuilder.sslContext(sslContext);
        return this;
    }

    public HttpClientBuilder keyStore(KeyStore keyStore, char[] password) {
        this.jerseyClientBuilder.keyStore(keyStore, password);
        return this;
    }

    public HttpClientBuilder trustStore(KeyStore trustStore) {
        this.jerseyClientBuilder.trustStore(trustStore);
        return this;
    }

    public HttpClientBuilder hostnameVerifier(HostnameVerifier verifier) {
        this.jerseyClientBuilder.hostnameVerifier(verifier);
        return this;
    }

    public HttpClientBuilder executorService(ExecutorService executorService) {
        this.jerseyClientBuilder.executorService(executorService);
        return this;
    }

    public HttpClientBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.jerseyClientBuilder.scheduledExecutorService(scheduledExecutorService);
        return this;
    }

    public HttpClientBuilder connectTimeout(long timeout, TimeUnit unit) {
        this.jerseyClientBuilder.connectTimeout(timeout, unit);
        return this;
    }

    public HttpClientBuilder readTimeout(long timeout, TimeUnit unit) {
        this.jerseyClientBuilder.readTimeout(timeout, unit);
        return this;
    }

    public HttpClientBuilder build() {
        return this;
    }

    public HttpClientConfiguration getConfiguration() {
        return configuration;
    }
}
