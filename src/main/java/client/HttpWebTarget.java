package client;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

public class HttpWebTarget {

//    HttpClientConfiguration config;

//    public HttpWebTarget(String uri) {}
//
//    public HttpWebTarget(URI uri) {}
//
//    public HttpWebTarget(UriBuilder uriBuilder) {}

    private final WebTarget target;

    public HttpWebTarget(WebTarget target) {
        this.target = target;
    }

    public Invocation.Builder request() {
        return null;
    }

    public Invocation.Builder request(MediaType... acceptedResponseTypes) {
        return null;
    }

//    public Configuration getConfiguration() {
//        return null;
//    }

//    public WebTarget property(String name, Object value) {
//        return null;
//    }

}
