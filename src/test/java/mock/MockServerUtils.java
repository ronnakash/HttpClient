package mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class MockServerUtils {
    private WireMockServer wireMockServer;

    public void startServer() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());

        // Stub the endpoints here
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/test/json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[[1,2,3],[1,2,3],[1,2,3]]")));

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/test/xml"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBody("<data><row>1</row><row>2</row><row>3</row></data>")));
    }

    public void stopServer() {
        wireMockServer.stop();
    }

    public int getPort() {
        return wireMockServer.port();
    }
}