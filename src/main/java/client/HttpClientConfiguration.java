package client;

import javax.ws.rs.core.Configuration;

//TODO: make this class take our body parsers

public class HttpClientConfiguration {

    private Configuration config;

    public HttpClientConfiguration(Configuration config) {
        this.config = config;
    }

    public HttpClientConfiguration() {
    }

    protected Configuration getJerseyConfig() {
        return config;
    }

}
