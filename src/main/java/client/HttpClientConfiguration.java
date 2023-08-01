package client;

import javax.ws.rs.core.Configuration;

//TODO: make this class take our body parsers

public class HttpClientConfiguration {
    private Configuration config;

    protected Configuration getJerseyConfig() {
        return config;
    }

}
