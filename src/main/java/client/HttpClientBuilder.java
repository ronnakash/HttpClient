package client;

import org.glassfish.jersey.client.JerseyClientBuilder;
import providers.DefaultJsonBodyReader;
import providers.DefaultXmlBodyReader;
import providers.JsonBodyReader;
import providers.XmlBodyReader;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.ext.MessageBodyReader;
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

    //TODO: figure out how i want to use the body readers
    // 1. just the bodyReader function
    // 2. jsonBodyReader and XmlBodyReader, with body reader that throws exception
    // 3. just all of them, no exception

    public HttpClientBuilder XmlBodyReader(XmlBodyReader<?> bodyReader) {
        this.jerseyClientBuilder.register(bodyReader);
        return this;
    }

    public HttpClientBuilder jsonBodyReader(JsonBodyReader<?> bodyReader) {
        this.jerseyClientBuilder.register(bodyReader);
        return this;
    }

    public HttpClientBuilder bodyReader(MessageBodyReader<?> bodyReader) {
//        if (bodyReader instanceof JsonBodyReader<?> || bodyReader instanceof XmlBodyReader<?>)
//            throw new UnsupportedOperationException(); // other exception???
        this.jerseyClientBuilder.register(bodyReader);
        return this;
    }

    //TODO: if we add body parsers to config, read them
    public HttpClientBuilder withConfig(HttpClientConfiguration config) {
        this.jerseyClientBuilder.withConfig(config.getJerseyConfig());
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

    public Client build() {
        return this.jerseyClientBuilder.build();
    }

    public HttpClientConfiguration getConfiguration() {
        return configuration;
    }

    // static defaults

    public static Client defaultClient() {
        return new JerseyClientBuilder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.MINUTES)
                .register(new DefaultXmlBodyReader<>())
                .register(new DefaultJsonBodyReader<>())
                .build();
    }

    public static Client defaultClient(HttpClientConfiguration configuration) {
        return new JerseyClientBuilder()
                .withConfig(configuration.getJerseyConfig()) // TODO: probably should change this
                .register(new DefaultXmlBodyReader<>())
                .register(new DefaultJsonBodyReader<>())
                .build();
    }

}
