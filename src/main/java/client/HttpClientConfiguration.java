package client;

import javax.ws.rs.core.Configuration;

public class HttpClientConfiguration {
    private Configuration config;

    protected Configuration getJerseyConfig() {
        return config;
    }
}
