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
                        .withBody(
                                "<nestedObject>" +
                                    "<innerObjects>" +
                                        "<innerObject>" +
                                            "<leafObjects>" +
                                                "<leafObject>" +
                                                    "<value>1</value>" +
                                                "</leafObject>" +
                                                "<leafObject>" +
                                                    "<value>2</value>" +
                                                "</leafObject>" +
                                                "<leafObject>" +
                                                    "<value>3</value>" +
                                                "</leafObject>" +
                                            "</leafObjects>" +
                                        "</innerObject>" +
                                        "<innerObject>" +
                                            "<leafObjects>" +
                                                "<leafObject>" +
                                                    "<value>1</value>" +
                                                "</leafObject>" +
                                                "<leafObject>" +
                                                    "<value>2</value>" +
                                                "</leafObject>" +
                                                "<leafObject>" +
                                                    "<value>3</value>" +
                                                "</leafObject>" +
                                            "</leafObjects>" +
                                        "</innerObject>" +
                                        "<innerObject>" +
                                            "<leafObjects>" +
                                                "<leafObject>" +
                                                    "<value>1</value>" +
                                                "</leafObject>" +
                                                "<leafObject>" +
                                                    "<value>2</value>" +
                                                "</leafObject>" +
                                                "<leafObject>" +
                                                    "<value>3</value>" +
                                                "</leafObject>" +
                                            "</leafObjects>" +
                                        "</innerObject>" +
                                    "</innerObjects>" +
                                "</nestedObject>")));

        WireMock.stubFor(WireMock.post(WireMock.urlPathEqualTo("/test/json/request"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalToJson("[[1,2,3],[1,2,3],[1,2,3]]"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)));

        WireMock.stubFor(WireMock.post(WireMock.urlPathEqualTo("/test/xml/request"))
                .withHeader("Content-Type", WireMock.equalTo("application/xml"))
                .withRequestBody(WireMock.equalToXml("" +
                        "<data>" +
                        "<row>" +
                        "<item>1</item>" +
                        "<item>2</item>" +
                        "<item>3</item>" +
                        "</row>" +
                        "<row>" +
                        "<item>1</item>" +
                        "<item>2</item>" +
                        "<item>3</item>" +
                        "</row>" +
                        "<row>" +
                        "<item>1</item>" +
                        "<item>2</item>" +
                        "<item>3</item>" +
                        "</row>" +
                        "</data>"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBody("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                                "<result>" +
                                "<list>" +
                                "<item>1</item>" +
                                "<item>2</item>" +
                                "<item>3</item>" +
                                "</list>" +
                                "<list>" +
                                "<item>1</item>" +
                                "<item>2</item>" +
                                "<item>3</item>" +
                                "</list>" +
                                "<list>" +
                                "<item>1</item>" +
                                "<item>2</item>" +
                                "<item>3</item>" +
                                "</list>" +
                                "</result>")));


    }

    public void stopServer() {
        wireMockServer.stop();
    }

    public int getPort() {
        return wireMockServer.port();
    }
}