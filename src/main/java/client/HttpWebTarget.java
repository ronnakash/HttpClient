//package client;
//
//import org.glassfish.jersey.client.JerseyInvocation;
//
//import javax.ws.rs.client.Invocation;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.Configuration;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.UriBuilder;
//import java.net.URI;
//import java.util.Map;
//
//public class HttpWebTarget {
//
////    HttpClientConfiguration config;
//
////    public HttpWebTarget(String uri) {}
////
////    public HttpWebTarget(URI uri) {}
////
////    public HttpWebTarget(UriBuilder uriBuilder) {}
//
//    //TODO: make it possible to register mappers for specific requests,
//    // without configuring the while client to allow the same client to use multiple mappers,
//    // or maybe register multiple mappers with names and then allow picking which one to use
//
//    private final WebTarget target;
//
//    public HttpWebTarget(WebTarget target) {
//        this.target = target;
//    }
//
//    public Invocation.Builder request() {
//        return null;
//    }
//
//    public Invocation.Builder request(MediaType... acceptedResponseTypes) {
////        checkNotClosed();
//        JerseyInvocation.Builder b = new JerseyInvocation.Builder(this.target.getUri(), this.target.getConfiguration());
//        b.request().accept(acceptedResponseTypes);
//        return b;
//    }
//
////    public Configuration getConfiguration() {
////        return null;
////    }
//
////    public WebTarget property(String name, Object value) {
////        return null;
////    }
//
//}
