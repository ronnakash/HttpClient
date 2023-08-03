package request;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

public abstract class HttpRequestBuilder {

    private final HttpRequestConfiguration config;

    public HttpRequestBuilder(MediaType responseAcceptedMediaType) {
        this.config = new HttpRequestConfiguration();
        config.setResponseAcceptedMediaType(responseAcceptedMediaType);
    }

    //TODO: more http methods??

    public HttpRequestBuilder get(){
        return this.setHttpMethod(HttpMethod.GET, null);
    }

    public HttpRequestBuilder post(MediaType bodyMediaType){
        return this.setHttpMethod(HttpMethod.POST, bodyMediaType);
    }

    public HttpRequestBuilder put(MediaType bodyMediaType){
        return this.setHttpMethod(HttpMethod.PUT, bodyMediaType);
    }

    public HttpRequestBuilder delete(MediaType bodyMediaType){
        return this.setHttpMethod(HttpMethod.DELETE, bodyMediaType);
    }

    private HttpRequestBuilder setHttpMethod(String httpMethod, MediaType bodyMediaType){
        config.setHttpMethod(httpMethod);
        config.setRequestBodyMediaType(bodyMediaType);
        return this;
    }

}
