package request;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

public class HttpRequestConfiguration {

    //TODO: add the ability to add mappers here and not to the client

    MediaType responseAcceptedMediaType;
    MediaType requestBodyMediaType; //TODO: change?
    String httpMethod;

    public MediaType getResponseAcceptedMediaType() {
        return responseAcceptedMediaType;
    }

    public void setResponseAcceptedMediaType(MediaType responseAcceptedMediaType) {
        this.responseAcceptedMediaType = responseAcceptedMediaType;
    }

    public MediaType getRequestBodyMediaType() {
        return requestBodyMediaType;
    }

    public void setRequestBodyMediaType(MediaType requestBodyMediaType) {
        this.requestBodyMediaType = requestBodyMediaType;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}
