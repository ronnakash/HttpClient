package client;

import request.HttpRequestBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Configuration;

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


    public SSLContext getSslContext() {
        return this.client.getSslContext();
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.client.getHostnameVerifier();
    }

    public Configuration getConfiguration() {
        return config;
    }

//    public HttpRequestBuilder request() {
//        return new HttpRequestBuilder();
//    }

}
