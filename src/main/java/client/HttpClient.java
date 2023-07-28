package client;

import WebTarget.HttpWebTarget;
import config.HttpClientConfig;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.client.spi.DefaultSslContextProvider;
import org.glassfish.jersey.internal.guava.Preconditions;
import org.glassfish.jersey.internal.util.collection.UnsafeValue;
import org.glassfish.jersey.internal.util.collection.Values;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;


public class HttpClient extends JerseyClient {

	private static final DefaultSslContextProvider DEFAULT_SSL_CONTEXT_PROVIDER = () -> SslConfigurator.getDefaultContext();
	private static final UnsafeValue<SSLContext, IllegalStateException> SSL_CONTEXT_ILLEGAL_STATE_EXCEPTION_UNSAFE_VALUE =
			Values.lazy(new UnsafeValue<>() {
		@Override
		public SSLContext get() {
			return DEFAULT_SSL_CONTEXT_PROVIDER.getDefaultSslContext();
		}
	});


	public HttpClient(JerseyClient client) {
		super(
				client.getConfiguration(),
				SSL_CONTEXT_ILLEGAL_STATE_EXCEPTION_UNSAFE_VALUE,
				client.getHostnameVerifier(),
				client.getExecutorService(),
				client.getScheduledExecutorService()
		);
	}

	public HttpClient(HttpClientConfig config,
					  UnsafeValue<SSLContext, IllegalStateException> sslContextIllegalStateExceptionUnsafeValue,
					  HostnameVerifier hostnameVerifier,
					  ExecutorService executorService,
					  ScheduledExecutorService scheduledExecutorService) {
		super(config, sslContextIllegalStateExceptionUnsafeValue, hostnameVerifier, executorService, scheduledExecutorService);
	}

	public HttpClient(HttpClientConfig config, SSLContext sslContext, HostnameVerifier hostnameVerifier, Object executorService, ExecutorService executorService1, ScheduledExecutorService scheduledExecutorService) {
		super(config, sslContext == null ? null : Values.unsafe(sslContext), hostnameVerifier,
				DEFAULT_SSL_CONTEXT_PROVIDER, (ExecutorService) executorService, scheduledExecutorService);
	}

	@Override
	public HttpWebTarget target(String uri) {
		Preconditions.checkState(!super.isClosed(), LocalizationMessages.CLIENT_INSTANCE_CLOSED());
		Preconditions.checkNotNull(uri, LocalizationMessages.CLIENT_URI_TEMPLATE_NULL());
		return new HttpWebTarget(uri, this);
	}

	@Override
	public HttpWebTarget target(URI uri) {
		Preconditions.checkState(!super.isClosed(), LocalizationMessages.CLIENT_INSTANCE_CLOSED());
		Preconditions.checkNotNull(uri, LocalizationMessages.CLIENT_URI_TEMPLATE_NULL());
		return new HttpWebTarget(uri, this);

	}

	@Override
	public HttpWebTarget target(UriBuilder uriBuilder) {
		return super.target(uriBuilder);
	}

	@Override
	public HttpWebTarget target(Link link) {
		return super.target(link);
	}
}
