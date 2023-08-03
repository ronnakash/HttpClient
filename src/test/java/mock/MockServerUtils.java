package mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class MockServerUtils {
    private WireMockServer wireMockServer;

    private static final String host = "localhost";
    private static final int port = 9090;

    public void startServer() {
        wireMockServer = new WireMockServer(port);
        wireMockServer.start();
        WireMock.configureFor(host, port);

        // Stub the endpoints here
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/test/json/response"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[[1,2,3],[1,2,3],[1,2,3]]")));

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/test/xml/response"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBody("<data><row>1</row><row>2</row><row>3</row></data>")));

        WireMock.stubFor(WireMock.post(WireMock.urlPathEqualTo("/test/json/request"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalToJson("[[1,2,3],[1,2,3],[1,2,3]]"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)));

        WireMock.stubFor(WireMock.post(WireMock.urlPathEqualTo("/test/xml/request"))
                .withHeader("Content-Type", WireMock.equalTo("application/xml"))
                .withRequestBody(WireMock.equalToXml("<data><row>1</row><row>2</row><row>3</row></data>"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)));

    }

    public void stopServer() {
        wireMockServer.stop();
    }

    public int getPort() {
        return wireMockServer.port();
    }
}