package builder;

import client.HttpClient;
import config.HttpClientConfig;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.collection.UnsafeValue;
import org.glassfish.jersey.internal.util.collection.Values;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.core.Configuration;
import java.security.KeyStore;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HttpClientBuilder {

	private final HttpClientConfig config;
	private HostnameVerifier hostnameVerifier;
	private SslConfigurator sslConfigurator;
	private SSLContext sslContext;
	private ExecutorService executorService;
	private ScheduledExecutorService scheduledExecutorService;

	/**
	 * Create a new custom-configured {@link HttpClient} instance.
	 *
	 * @return new configured Jersey client instance.
	 * @since 2.5
	 */
	public static HttpClient createClient() {
		return new HttpClientBuilder().build();
	}

	public static HttpClient createClient(Configuration configuration) {
		return new HttpClientBuilder().withConfig(configuration).build();
	}

	/**
	 * Create new Jersey client builder instance.
	 */
	public HttpClientBuilder() {
		this.config = new HttpClientConfig();
	}


	public HttpClientBuilder sslContext(SSLContext sslContext) {
		if (sslContext == null) {
			throw new NullPointerException(LocalizationMessages.NULL_SSL_CONTEXT());
		}
		this.sslContext = sslContext;
		sslConfigurator = null;
		return this;
	}

	public HttpClientBuilder keyStore(KeyStore keyStore, char[] password) {
		if (keyStore == null) {
			throw new NullPointerException(LocalizationMessages.NULL_KEYSTORE());
		}
		if (password == null) {
			throw new NullPointerException(LocalizationMessages.NULL_KEYSTORE_PASWORD());
		}
		if (sslConfigurator == null) {
			sslConfigurator = SslConfigurator.newInstance();
		}
		sslConfigurator.keyStore(keyStore);
		sslConfigurator.keyPassword(password);
		sslContext = null;
		return this;
	}

	public HttpClientBuilder trustStore(KeyStore trustStore) {
		if (trustStore == null) {
			throw new NullPointerException(LocalizationMessages.NULL_TRUSTSTORE());
		}
		if (sslConfigurator == null) {
			sslConfigurator = SslConfigurator.newInstance();
		}
		sslConfigurator.trustStore(trustStore);
		sslContext = null;
		return this;
	}

	public HttpClientBuilder hostnameVerifier(HostnameVerifier hostnameVerifier) {
		this.hostnameVerifier = hostnameVerifier;
		return this;
	}

	public HttpClientBuilder executorService(ExecutorService executorService) {
		this.executorService = executorService;
		return this;
	}

	public HttpClientBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
		this.scheduledExecutorService = scheduledExecutorService;
		return this;
	}

	public HttpClientBuilder connectTimeout(long timeout, TimeUnit unit) {
		if (timeout < 0) {
			throw new IllegalArgumentException("Negative timeout.");
		}

		this.property(ClientProperties.CONNECT_TIMEOUT, Math.toIntExact(unit.toMillis(timeout)));
		return this;
	}

	public HttpClientBuilder readTimeout(long timeout, TimeUnit unit) {
		if (timeout < 0) {
			throw new IllegalArgumentException("Negative timeout.");
		}

		this.property(ClientProperties.READ_TIMEOUT, Math.toIntExact(unit.toMillis(timeout)));
		return this;
	}

	public HttpClientConfig getConfiguration() {
		return config;
	}

	public HttpClientBuilder property(String name, Object value) {
		this.config.property(name, value);
		return this;
	}

	public HttpClientBuilder register(Class<?> componentClass) {
		this.config.register(componentClass);
		return this;
	}

	public HttpClientBuilder register(Class<?> componentClass, int priority) {
		this.config.register(componentClass, priority);
		return this;
	}

	public HttpClientBuilder register(Class<?> componentClass, Class<?>... contracts) {
		this.config.register(componentClass, contracts);
		return this;
	}

	public HttpClientBuilder register(Class<?> componentClass, Map<Class<?>, Integer> contracts) {
		this.config.register(componentClass, contracts);
		return this;
	}

	public HttpClientBuilder register(Object component) {
		this.config.register(component);
		return this;
	}

	public HttpClientBuilder register(Object component, int priority) {
		this.config.register(component, priority);
		return this;
	}

	public HttpClientBuilder register(Object component, Class<?>... contracts) {
		this.config.register(component, contracts);
		return this;
	}

	public HttpClientBuilder register(Object component, Map<Class<?>, Integer> contracts) {
		this.config.register(component, contracts);
		return this;
	}

	public HttpClientBuilder withConfig(Configuration config) {
		this.config.loadFrom(config);
		return this;
	}

	public HttpClient build() {
		if (sslContext != null) {
			return new HttpClient(config, sslContext, hostnameVerifier, null, executorService,
					scheduledExecutorService);
		} else if (sslConfigurator != null) {
			final SslConfigurator sslConfiguratorCopy = sslConfigurator.copy();
			return new HttpClient(
					config,
					Values.lazy((UnsafeValue<SSLContext, IllegalStateException>) sslConfiguratorCopy::createSSLContext),
					hostnameVerifier, executorService, scheduledExecutorService);
		} else {
			return new HttpClient(config, null, hostnameVerifier,
					executorService, scheduledExecutorService);
		}
	}

}
