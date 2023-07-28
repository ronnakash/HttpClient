//package client;
//
//import org.glassfish.jersey.SslConfigurator;
//import org.glassfish.jersey.client.*;
//import org.glassfish.jersey.client.internal.LocalizationMessages;
//import org.glassfish.jersey.client.spi.DefaultSslContextProvider;
//import org.glassfish.jersey.internal.ServiceFinder;
//import org.glassfish.jersey.internal.util.collection.UnsafeValue;
//import org.glassfish.jersey.internal.util.collection.Values;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLContext;
//import javax.ws.rs.core.Configuration;
//import javax.ws.rs.core.Link;
//import javax.ws.rs.core.UriBuilder;
//import java.lang.ref.Reference;
//import java.lang.ref.ReferenceQueue;
//import java.lang.ref.WeakReference;
//import java.net.URI;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.LinkedBlockingDeque;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import static org.glassfish.jersey.internal.guava.Preconditions.checkNotNull;
//import static org.glassfish.jersey.internal.guava.Preconditions.checkState;
//
//public class BaseHttpClient implements javax.ws.rs.client.Client, Initializable<BaseHttpClient> {
//	private static final Logger LOG = Logger.getLogger(BaseHttpClient.class.getName());
//
//	private static final DefaultSslContextProvider DEFAULT_SSL_CONTEXT_PROVIDER = new DefaultSslContextProvider() {
//		@Override
//		public SSLContext getDefaultSslContext() {
//			return SslConfigurator.getDefaultContext();
//		}
//	};
//
//	protected final AtomicBoolean closedFlag = new AtomicBoolean(false);
//	protected final boolean isDefaultSslContext;
//	protected final ClientConfig config;
//	protected final HostnameVerifier hostnameVerifier;
//	protected final UnsafeValue<SSLContext, IllegalStateException> sslContext;
//	protected final LinkedBlockingDeque<WeakReference<BaseHttpClient.ShutdownHook>> shutdownHooks =
//			new LinkedBlockingDeque<WeakReference<BaseHttpClient.ShutdownHook>>();
//	private final ReferenceQueue<BaseHttpClient.ShutdownHook> shReferenceQueue = new ReferenceQueue<BaseHttpClient.ShutdownHook>();
//	protected final ExecutorService executorService;
//	protected final ScheduledExecutorService scheduledExecutorService;
//
//	/**
//	 * Client instance shutdown hook.
//	 */
//	interface ShutdownHook {
//		/**
//		 * Invoked when the client instance is closed.
//		 */
//		public void onShutdown();
//	}
//
//	/**
//	 * Create a new Jersey client instance using a default configuration.
//	 */
//	protected BaseHttpClient() {
//		this(null, (UnsafeValue<SSLContext, IllegalStateException>) null, null, null);
//	}
//
//	/**
//	 * Create a new Jersey client instance.
//	 *
//	 * @param config     jersey client configuration.
//	 * @param sslContext jersey client SSL context.
//	 * @param verifier   jersey client host name verifier.
//	 */
//	protected BaseHttpClient(final Configuration config,
//						   final SSLContext sslContext,
//						   final HostnameVerifier verifier) {
//
//		this(config, sslContext, verifier, null);
//	}
//
//	/**
//	 * Create a new Jersey client instance.
//	 *
//	 * @param config                    jersey client configuration.
//	 * @param sslContext                jersey client SSL context.
//	 * @param verifier                  jersey client host name verifier.
//	 * @param defaultSslContextProvider default SSL context provider.
//	 */
//	protected BaseHttpClient(final Configuration config,
//						   final SSLContext sslContext,
//						   final HostnameVerifier verifier,
//						   final DefaultSslContextProvider defaultSslContextProvider) {
//		this(config, sslContext == null ? null : Values.unsafe(sslContext), verifier,
//				defaultSslContextProvider);
//	}
//
//	/**
//	 * Create a new Jersey client instance.
//	 *
//	 * @param config             jersey client configuration.
//	 * @param sslContextProvider jersey client SSL context provider.
//	 * @param verifier           jersey client host name verifier.
//	 */
//	protected BaseHttpClient(final Configuration config,
//						   final UnsafeValue<SSLContext, IllegalStateException> sslContextProvider,
//						   final HostnameVerifier verifier) {
//		this(config, sslContextProvider, verifier, null);
//	}
//
//	/**
//	 * Create a new Jersey client instance.
//	 *
//	 * @param config                    jersey client configuration.
//	 * @param sslContext                jersey client SSL context.
//	 * @param verifier                  jersey client host name verifier.
//	 * @param defaultSslContextProvider default SSL context provider.
//	 */
//	protected BaseHttpClient(final Configuration config,
//						   final SSLContext sslContext,
//						   final HostnameVerifier verifier,
//						   final DefaultSslContextProvider defaultSslContextProvider,
//						   ExecutorService executorService,
//						   ScheduledExecutorService scheduledExecutorService) {
//		this(config, sslContext == null ? null : Values.unsafe(sslContext), verifier,
//				defaultSslContextProvider, executorService, scheduledExecutorService);
//	}
//
//	/**
//	 * Create a new Jersey client instance.
//	 *
//	 * @param config             jersey client configuration.
//	 * @param sslContextProvider jersey client SSL context provider.
//	 * @param verifier           jersey client host name verifier.
//	 */
//	protected BaseHttpClient(final Configuration config,
//						   final UnsafeValue<SSLContext, IllegalStateException> sslContextProvider,
//						   final HostnameVerifier verifier,
//						   ExecutorService executorService,
//						   ScheduledExecutorService scheduledExecutorService) {
//		this(config, sslContextProvider, verifier, null, executorService, scheduledExecutorService);
//	}
//
//	/**
//	 * Create a new Jersey client instance.
//	 *
//	 * @param config                    jersey client configuration.
//	 * @param sslContextProvider        jersey client SSL context provider. Non {@code null} provider is expected to
//	 *                                  return non-default value.
//	 * @param verifier                  jersey client host name verifier.
//	 * @param defaultSslContextProvider default SSL context provider.
//	 */
//	protected BaseHttpClient(final Configuration config,
//						   final UnsafeValue<SSLContext, IllegalStateException> sslContextProvider,
//						   final HostnameVerifier verifier,
//						   final DefaultSslContextProvider defaultSslContextProvider) {
//		this(config, sslContextProvider, verifier, defaultSslContextProvider, null, null);
//	}
//
//	protected BaseHttpClient(final Configuration config,
//						   final UnsafeValue<SSLContext, IllegalStateException> sslContextProvider,
//						   final HostnameVerifier verifier,
//						   final DefaultSslContextProvider defaultSslContextProvider,
//						   ExecutorService executorService,
//						   ScheduledExecutorService scheduledExecutorService) {
//		this.config = config == null ? new ClientConfig(this) : new ClientConfig(this, config);
//
//		if (sslContextProvider == null) {
//			this.isDefaultSslContext = true;
//
//			if (defaultSslContextProvider != null) {
//				this.sslContext = createLazySslContext(defaultSslContextProvider);
//			} else {
//				final DefaultSslContextProvider lookedUpSslContextProvider;
//
//				final Iterator<DefaultSslContextProvider> iterator =
//						ServiceFinder.find(DefaultSslContextProvider.class).iterator();
//
//				if (iterator.hasNext()) {
//					lookedUpSslContextProvider = iterator.next();
//				} else {
//					lookedUpSslContextProvider = DEFAULT_SSL_CONTEXT_PROVIDER;
//				}
//
//				this.sslContext = createLazySslContext(lookedUpSslContextProvider);
//			}
//		} else {
//			this.isDefaultSslContext = false;
//			this.sslContext = Values.lazy(sslContextProvider);
//		}
//
//		this.hostnameVerifier = verifier;
//		this.executorService = executorService;
//		this.scheduledExecutorService = scheduledExecutorService;
//	}
//
//	@Override
//	public void close() {
//		if (closedFlag.compareAndSet(false, true)) {
//			release();
//		}
//	}
//
//	private void release() {
//		Reference<BaseHttpClient.ShutdownHook> listenerRef;
//		while ((listenerRef = shutdownHooks.pollFirst()) != null) {
//			BaseHttpClient.ShutdownHook listener = listenerRef.get();
//			if (listener != null) {
//				try {
//					listener.onShutdown();
//				} catch (Throwable t) {
//					LOG.log(Level.WARNING, LocalizationMessages.ERROR_SHUTDOWNHOOK_CLOSE(listenerRef.getClass().getName()), t);
//				}
//			}
//		}
//	}
//
//	private UnsafeValue<SSLContext, IllegalStateException> createLazySslContext(final DefaultSslContextProvider provider) {
//		return Values.lazy(new UnsafeValue<SSLContext, IllegalStateException>() {
//			@Override
//			public SSLContext get() {
//				return provider.getDefaultSslContext();
//			}
//		});
//	}
//
//	/**
//	 * Register a new client shutdown hook.
//	 *
//	 * @param shutdownHook client shutdown hook.
//	 */
//	/* package */ void registerShutdownHook(final BaseHttpClient.ShutdownHook shutdownHook) {
//		checkNotClosed();
//		shutdownHooks.push(new WeakReference<BaseHttpClient.ShutdownHook>(shutdownHook, shReferenceQueue));
//		cleanUpShutdownHooks();
//	}
//
//	/**
//	 * Clean up shutdown hooks that have been garbage collected.
//	 */
//	private void cleanUpShutdownHooks() {
//
//		Reference<? extends BaseHttpClient.ShutdownHook> reference;
//
//		while ((reference = shReferenceQueue.poll()) != null) {
//
//			shutdownHooks.remove(reference);
//
//			final BaseHttpClient.ShutdownHook shutdownHook = reference.get();
//			if (shutdownHook != null) { // close this one off if still accessible
//				shutdownHook.onShutdown();
//			}
//		}
//	}
//
//	private ScheduledExecutorService getDefaultScheduledExecutorService() {
//		return Executors.newScheduledThreadPool(8);
//	}
//
//	/**
//	 * Check client state.
//	 *
//	 * @return {@code true} if current {@code BaseHttpClient} instance is closed, otherwise {@code false}.
//	 *
//	 * @see #close()
//	 */
//	public boolean isClosed() {
//		return closedFlag.get();
//	}
//
//	/**
//	 * Check that the client instance has not been closed.
//	 *
//	 * @throws IllegalStateException in case the client instance has been closed already.
//	 */
//	void checkNotClosed() {
//		checkState(!closedFlag.get(), LocalizationMessages.CLIENT_INSTANCE_CLOSED());
//	}
//
//	/**
//	 * Get information about used {@link SSLContext}.
//	 *
//	 * @return {@code true} when used {@code SSLContext} is acquired from {@link SslConfigurator#getDefaultContext()},
//	 * {@code false} otherwise.
//	 */
//	public boolean isDefaultSslContext() {
//		return isDefaultSslContext;
//	}
//
//	@Override
//	public JerseyWebTarget target(final String uri) {
//		checkNotClosed();
//		checkNotNull(uri, LocalizationMessages.CLIENT_URI_TEMPLATE_NULL());
//		return new JerseyWebTarget(uri, this);
//	}
//
//	@Override
//	public JerseyWebTarget target(final URI uri) {
//		checkNotClosed();
//		checkNotNull(uri, LocalizationMessages.CLIENT_URI_NULL());
//		return new JerseyWebTarget(uri, this);
//	}
//
//	@Override
//	public JerseyWebTarget target(final UriBuilder uriBuilder) {
//		checkNotClosed();
//		checkNotNull(uriBuilder, LocalizationMessages.CLIENT_URI_BUILDER_NULL());
//		return new JerseyWebTarget(uriBuilder, this);
//	}
//
//	@Override
//	public JerseyWebTarget target(final Link link) {
//		checkNotClosed();
//		checkNotNull(link, LocalizationMessages.CLIENT_TARGET_LINK_NULL());
//		return new JerseyWebTarget(link, this);
//	}
//
//	@Override
//	public JerseyInvocation.Builder invocation(final Link link) {
//		checkNotClosed();
//		checkNotNull(link, LocalizationMessages.CLIENT_INVOCATION_LINK_NULL());
//		final JerseyWebTarget t = new JerseyWebTarget(link, this);
//		final String acceptType = link.getType();
//		return (acceptType != null) ? t.request(acceptType) : t.request();
//	}
//
//	@Override
//	public BaseHttpClient register(final Class<?> providerClass) {
//		checkNotClosed();
//		config.register(providerClass);
//		return this;
//	}
//
//	@Override
//	public BaseHttpClient register(final Object provider) {
//		checkNotClosed();
//		config.register(provider);
//		return this;
//	}
//
//	@Override
//	public BaseHttpClient register(final Class<?> providerClass, final int bindingPriority) {
//		checkNotClosed();
//		config.register(providerClass, bindingPriority);
//		return this;
//	}
//
//	@Override
//	public BaseHttpClient register(final Class<?> providerClass, final Class<?>... contracts) {
//		checkNotClosed();
//		config.register(providerClass, contracts);
//		return this;
//	}
//
//	@Override
//	public BaseHttpClient register(final Class<?> providerClass, final Map<Class<?>, Integer> contracts) {
//		checkNotClosed();
//		config.register(providerClass, contracts);
//		return this;
//	}
//
//	@Override
//	public BaseHttpClient register(final Object provider, final int bindingPriority) {
//		checkNotClosed();
//		config.register(provider, bindingPriority);
//		return this;
//	}
//
//	@Override
//	public BaseHttpClient register(final Object provider, final Class<?>... contracts) {
//		checkNotClosed();
//		config.register(provider, contracts);
//		return this;
//	}
//
//	@Override
//	public BaseHttpClient register(final Object provider, final Map<Class<?>, Integer> contracts) {
//		checkNotClosed();
//		config.register(provider, contracts);
//		return this;
//	}
//
//	@Override
//	public BaseHttpClient property(final String name, final Object value) {
//		checkNotClosed();
//		config.property(name, value);
//		return this;
//	}
//
//	@Override
//	public ClientConfig getConfiguration() {
//		checkNotClosed();
//		return config.getConfiguration();
//	}
//
//	@Override
//	public SSLContext getSslContext() {
//		return sslContext.get();
//	}
//
//	@Override
//	public HostnameVerifier getHostnameVerifier() {
//		return hostnameVerifier;
//	}
//
//	public ExecutorService getExecutorService() {
//		return executorService;
//	}
//
//	public ScheduledExecutorService getScheduledExecutorService() {
//		return scheduledExecutorService;
//	}
//
//	@Override
//	public BaseHttpClient preInitialize() {
//		config.preInitialize();
//		return this;
//	}
//}