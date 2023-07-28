package config;

import client.ClientImpl;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.ExtendedConfig;
import org.glassfish.jersey.client.*;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.client.spi.Connector;
import org.glassfish.jersey.client.spi.ConnectorProvider;
import org.glassfish.jersey.internal.*;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.internal.inject.ProviderBinder;
import org.glassfish.jersey.internal.spi.AutoDiscoverable;
import org.glassfish.jersey.internal.util.collection.LazyValue;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.message.internal.MessageBodyFactory;
import org.glassfish.jersey.model.internal.CommonConfig;
import org.glassfish.jersey.model.internal.ComponentBag;
import org.glassfish.jersey.model.internal.ManagedObjectsFinalizer;
import org.glassfish.jersey.process.internal.RequestScope;

import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public class ClientConfigState implements Configurable<ClientConfigState>, ExtendedConfig {

	/**
	 * Strategy that returns the same state instance.
	 */
	private static final ClientConfigStateChangeStrategy IDENTITY = state -> state;
	/**
	 * Strategy that returns a copy of the state instance.
	 */
	private static final ClientConfigStateChangeStrategy COPY_ON_CHANGE = ClientConfigState::copy;

	private volatile ClientConfigStateChangeStrategy strategy;
	private final CommonConfig commonConfig;
	private final ClientImpl client;
	private volatile ConnectorProvider connectorProvider;
	private volatile ExecutorService executorService;
	private volatile ScheduledExecutorService scheduledExecutorService;

	private final LazyValue<ClientRuntime> runtime = Values.lazy((Value<ClientRuntime>) this::initRuntime);

	/**
	 * Configuration state change strategy.
	 */
	private interface ClientConfigStateChangeStrategy {

		/**
		 * Invoked whenever a mutator method is called in the given configuration
		 * state.
		 *
		 * @param state configuration state to be mutated.
		 * @return state instance that will be mutated and returned from the
		 * invoked configuration state mutator method.
		 */
		ClientConfigState onChange(final ClientConfigState state);
	}

	/**
	 * Default configuration state constructor with {@link ClientConfigState.StateChangeStrategy "identity"}
	 * state change strategy.
	 *
	 * @param client bound parent Jersey client.
	 */
	ClientConfigState(final JerseyClient client) {
		this.strategy = IDENTITY;
		this.commonConfig = new CommonConfig(RuntimeType.CLIENT, ComponentBag.EXCLUDE_EMPTY);
		this.client = client;
		final Iterator<ConnectorProvider> iterator = ServiceFinder.find(ConnectorProvider.class).iterator();
		if (iterator.hasNext()) {
			this.connectorProvider = iterator.next();
		} else {
			this.connectorProvider = new HttpUrlConnectorProvider();
		}
	}

	/**
	 * Copy the original configuration state while using the default state change
	 * strategy.
	 *
	 * @param client  new Jersey client parent for the state.
	 * @param original configuration strategy to be copied.
	 */
	private ClientConfigState(final JerseyClient client, final ClientConfigState original) {
		this.strategy = IDENTITY;
		this.client = client;
		this.commonConfig = new CommonConfig(original.commonConfig);
		this.connectorProvider = original.connectorProvider;
		this.executorService = original.executorService;
		this.scheduledExecutorService = original.scheduledExecutorService;
	}

	/**
	 * Create a copy of the configuration state within the same parent Jersey
	 * client instance scope.
	 *
	 * @return configuration state copy.
	 */
	 ClientConfigState copy() {
		return new ClientConfigState(this.client, this);
	}

	/**
	 * Create a copy of the configuration state in a scope of the given
	 * parent Jersey client instance.
	 *
	 * @param client parent Jersey client instance.
	 * @return configuration state copy.
	 */
	 ClientConfigState copy(final JerseyClient client) {
		return new ClientConfigState(client, this);
	}

	void markAsShared() {
		strategy = COPY_ON_CHANGE;
	}

	 ClientConfigState preInitialize() {
		final ClientConfigState state = strategy.onChange(this);
		state.strategy = COPY_ON_CHANGE;
		state.runtime.get().preInitialize();
		return state;

	}

	@Override
	public ClientConfigState property(final String name, final Object value) {
		final ClientConfigState state = strategy.onChange(this);
		state.commonConfig.property(name, value);
		return state;
	}

	public ClientConfigState loadFrom(final Configuration config) {
		final ClientConfigState state = strategy.onChange(this);
		state.commonConfig.loadFrom(config);
		return state;
	}

	@Override
	public ClientConfigState register(final Class<?> providerClass) {
		final ClientConfigState state = strategy.onChange(this);
		state.commonConfig.register(providerClass);
		return state;
	}

	@Override
	public ClientConfigState register(final Object provider) {
		final ClientConfigState state = strategy.onChange(this);
		state.commonConfig.register(provider);
		return state;
	}

	@Override
	public ClientConfigState register(final Class<?> providerClass, final int bindingPriority) {
		final ClientConfigState state = strategy.onChange(this);
		state.commonConfig.register(providerClass, bindingPriority);
		return state;
	}

	@Override
	public ClientConfigState register(final Class<?> providerClass, final Class<?>... contracts) {
		final ClientConfigState state = strategy.onChange(this);
		state.commonConfig.register(providerClass, contracts);
		return state;
	}

	@Override
	public ClientConfigState register(final Class<?> providerClass, final Map<Class<?>, Integer> contracts) {
		final ClientConfigState state = strategy.onChange(this);
		state.commonConfig.register(providerClass, contracts);
		return state;
	}

	@Override
	public ClientConfigState register(final Object provider, final int bindingPriority) {
		final ClientConfigState state = strategy.onChange(this);
		state.commonConfig.register(provider, bindingPriority);
		return state;
	}

	@Override
	public ClientConfigState register(final Object provider, final Class<?>... contracts) {
		final ClientConfigState state = strategy.onChange(this);
		state.commonConfig.register(provider, contracts);
		return state;
	}

	@Override
	public ClientConfigState register(final Object provider, final Map<Class<?>, Integer> contracts) {
		final ClientConfigState state = strategy.onChange(this);
		state.commonConfig.register(provider, contracts);
		return state;
	}

	 ClientConfigState connectorProvider(final ConnectorProvider provider) {
		if (provider == null) {
			throw new NullPointerException(LocalizationMessages.NULL_CONNECTOR_PROVIDER());
		}
		final ClientConfigState state = strategy.onChange(this);
		state.connectorProvider = provider;
		return state;
	}

	 ClientConfigState executorService(final ExecutorService executorService) {
		if (executorService == null) {
			throw new NullPointerException(LocalizationMessages.NULL_EXECUTOR_SERVICE());
		}
		final ClientConfigState state = strategy.onChange(this);
		state.executorService = executorService;
		return state;
	}

	 ClientConfigState scheduledExecutorService(final ScheduledExecutorService scheduledExecutorService) {
		if (scheduledExecutorService == null) {
			throw new NullPointerException(LocalizationMessages.NULL_SCHEDULED_EXECUTOR_SERVICE());
		}
		final ClientConfigState state = strategy.onChange(this);
		state.scheduledExecutorService = scheduledExecutorService;
		return state;
	}

	Connector getConnector() {
		// Get the connector only if the runtime has been initialized.
		return (runtime.isInitialized()) ? runtime.get().getConnector() : null;
	}

	ConnectorProvider getConnectorProvider() {
		return connectorProvider;
	}

	ExecutorService getExecutorService() {
		return executorService;
	}

	ScheduledExecutorService getScheduledExecutorService() {
		return scheduledExecutorService;
	}

	JerseyClient getClient() {
		return client;
	}

	@Override
	public ClientConfigState getConfiguration() {
		return this;
	}

	@Override
	public RuntimeType getRuntimeType() {
		return commonConfig.getConfiguration().getRuntimeType();
	}

	@Override
	public Map<String, Object> getProperties() {
		return commonConfig.getConfiguration().getProperties();
	}

	@Override
	public Object getProperty(final String name) {
		return commonConfig.getConfiguration().getProperty(name);
	}

	@Override
	public Collection<String> getPropertyNames() {
		return commonConfig.getConfiguration().getPropertyNames();
	}

	@Override
	public boolean isProperty(final String name) {
		return commonConfig.getConfiguration().isProperty(name);
	}

	@Override
	public boolean isEnabled(final Feature feature) {
		return commonConfig.getConfiguration().isEnabled(feature);
	}

	@Override
	public boolean isEnabled(final Class<? extends Feature> featureClass) {
		return commonConfig.getConfiguration().isEnabled(featureClass);
	}

	@Override
	public boolean isRegistered(final Object component) {
		return commonConfig.getConfiguration().isRegistered(component);
	}

	@Override
	public boolean isRegistered(final Class<?> componentClass) {
		return commonConfig.getConfiguration().isRegistered(componentClass);
	}

	@Override
	public Map<Class<?>, Integer> getContracts(final Class<?> componentClass) {
		return commonConfig.getConfiguration().getContracts(componentClass);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return commonConfig.getConfiguration().getClasses();
	}

	@Override
	public Set<Object> getInstances() {
		return commonConfig.getConfiguration().getInstances();
	}

	public void configureAutoDiscoverableProviders(InjectionManager injectionManager,
												 List<AutoDiscoverable> autoDiscoverables) {
		commonConfig.configureAutoDiscoverableProviders(injectionManager, autoDiscoverables, false);
	}

	public void configureForcedAutoDiscoverableProviders(InjectionManager injectionManager) {
		commonConfig.configureAutoDiscoverableProviders(injectionManager, Collections.emptyList(), true);
	}

	public void configureMetaProviders(InjectionManager injectionManager, ManagedObjectsFinalizer finalizer) {
		commonConfig.configureMetaProviders(injectionManager, finalizer);
	}

	public ComponentBag getComponentBag() {
		return commonConfig.getComponentBag();
	}

	/**
	 * Initialize the newly constructed client instance.
	 */
	@SuppressWarnings("MethodOnlyUsedFromInnerClass")
	private ClientRuntime initRuntime() {
		/*
		 * Ensure that any attempt to add a new provider, feature, binder or modify the connector
		 * will cause a copy of the current state.
		 */
		markAsShared();

		final ClientConfigState runtimeCfgState = this.copy();
		runtimeCfgState.markAsShared();

		InjectionManager injectionManager = Injections.createInjectionManager();
		injectionManager.register(new ClientBinder(runtimeCfgState.getProperties()));

		BootstrapBag bootstrapBag = new BootstrapBag();
		bootstrapBag.setManagedObjectsFinalizer(new ManagedObjectsFinalizer(injectionManager));
		List<BootstrapConfigurator> bootstrapConfigurators = Arrays.asList(
				new RequestScope.RequestScopeConfigurator(),
				new ClientConfig.RuntimeConfigConfigurator(runtimeCfgState),
				new ContextResolverFactory.ContextResolversConfigurator(),
				new MessageBodyFactory.MessageBodyWorkersConfigurator(),
				new ExceptionMapperFactory.ExceptionMappersConfigurator(),
				new JaxrsProviders.ProvidersConfigurator(),
				new AutoDiscoverableConfigurator(RuntimeType.CLIENT));
		bootstrapConfigurators.forEach(configurator -> configurator.init(injectionManager, bootstrapBag));

		// AutoDiscoverable.
		if (!CommonProperties.getValue(runtimeCfgState.getProperties(), RuntimeType.CLIENT,
				CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, Boolean.FALSE, Boolean.class)) {
			runtimeCfgState.configureAutoDiscoverableProviders(injectionManager, bootstrapBag.getAutoDiscoverables());
		} else {
			runtimeCfgState.configureForcedAutoDiscoverableProviders(injectionManager);
		}

		// Configure binders and features.
		runtimeCfgState.configureMetaProviders(injectionManager, bootstrapBag.getManagedObjectsFinalizer());

		// Bind providers.
		ProviderBinder.bindProviders(runtimeCfgState.getComponentBag(), RuntimeType.CLIENT, null, injectionManager);

		ClientExecutorProvidersConfigurator executorProvidersConfigurator =
				new ClientExecutorProvidersConfigurator(runtimeCfgState.getComponentBag(),
						runtimeCfgState.client,
						this.executorService,
						this.scheduledExecutorService);
		executorProvidersConfigurator.init(injectionManager, bootstrapBag);

		injectionManager.completeRegistration();

		bootstrapConfigurators.forEach(configurator -> configurator.postInit(injectionManager, bootstrapBag));

		final ClientConfig configuration = new ClientConfig(runtimeCfgState);
		final Connector connector = connectorProvider.getConnector(client, configuration);
		final ClientRuntime crt = new ClientRuntime(configuration, connector, injectionManager, bootstrapBag);

		client.registerShutdownHook(crt);
		return crt;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final ClientConfigState state = ( ClientConfigState) o;

		if (!Objects.equals(client, state.client)) {
			return false;
		}
		if (!commonConfig.equals(state.commonConfig)) {
			return false;
		}
		return Objects.equals(connectorProvider, state.connectorProvider);
	}

	@Override
	public int hashCode() {
		int result = commonConfig.hashCode();
		result = 31 * result + (client != null ? client.hashCode() : 0);
		result = 31 * result + (connectorProvider != null ? connectorProvider.hashCode() : 0);
		return result;
	}
}
