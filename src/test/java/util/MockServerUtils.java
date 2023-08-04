package util;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;

public class MockServerUtils {
    private WireMockServer wireMockServer;

    private static final String host = "localhost";
    private static final int port = 9090;

    public void startServer() {

        wireMockServer = new WireMockServer(port);
        wireMockServer.start();
        WireMock.configureFor(host, port);

        // Endpoint: /test/json/response
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/test/json/response"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[[1,2,3],[1,2,3],[1,2,3]]")));

        // Endpoint: /test/xml/response
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/test/xml/response"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBody(getXmlResponseBody())));

        // Endpoint: /test/json/request
        WireMock.stubFor(WireMock.post(WireMock.urlPathEqualTo("/test/json/request"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalToJson(getJsonRequestBody()))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)));

        // Endpoint: /test/xml/request
        WireMock.stubFor(WireMock.post(WireMock.urlPathEqualTo("/test/xml/request"))
                .withHeader("Content-Type", WireMock.equalTo("application/xml"))
                .withRequestBody(WireMock.equalToXml(getXmlRequestBody()))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBody("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result>SUCCESS</result>")));
    }

    public void stopServer() {
        wireMockServer.stop();
    }

    public int getPort() {
        return wireMockServer.port();
    }

    private String getJsonRequestBody() {
        return "[{\"innerObjects\":[{\"leafObjects\":[{\"value\":1},{\"value\":2},{\"value\":3}]},"
                + "{\"leafObjects\":[{\"value\":1},{\"value\":2},{\"value\":3}]},"
                + "{\"leafObjects\":[{\"value\":1},{\"value\":2},{\"value\":3}]}]}]";
    }

    private String getXmlRequestBody() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><nestedObject>"
                + "<innerObjects><innerObject><leafObjects><leafObject><value>1</value></leafObject>"
                + "<leafObject><value>2</value></leafObject><leafObject><value>3</value></leafObject>"
                + "</leafObjects></innerObject><innerObject><leafObjects><leafObject><value>1</value>"
                + "</leafObject><leafObject><value>2</value></leafObject><leafObject><value>3</value>"
                + "</leafObject></leafObjects></innerObject><innerObject><leafObjects><leafObject>"
                + "<value>1</value></leafObject><leafObject><value>2</value></leafObject>"
                + "<leafObject><value>3</value></leafObject></leafObjects></innerObject>"
                + "</innerObjects></nestedObject>";
    }

    private String getXmlResponseBody() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><nestedObject>"
                + "<innerObjects><innerObject><leafObjects><leafObject><value>1</value></leafObject>"
                + "<leafObject><value>2</value></leafObject><leafObject><value>3</value></leafObject>"
                + "</leafObjects></innerObject><innerObject><leafObjects><leafObject><value>1</value>"
                + "</leafObject><leafObject><value>2</value></leafObject><leafObject><value>3</value>"
                + "</leafObject></leafObjects></innerObject><innerObject><leafObjects><leafObject>"
                + "<value>1</value></leafObject><leafObject><value>2</value></leafObject>"
                + "<leafObject><value>3</value></leafObject></leafObjects></innerObject>"
                + "</innerObjects></nestedObject>";
    }

//    protected static void requestReceived(Request request, Response response) {
//        LOGGER.info("Received WireMock request:");
//        LOGGER.info("URL: {}", request.getUrl());
//        LOGGER.info("Method: {}", request.getMethod());
//        LOGGER.info("Headers: \n{}", request.getHeaders().all());
//        LOGGER.info("Body: \n{}", request.getBodyAsString());
//    }

}
