package config;

import org.glassfish.jersey.ExtendedConfig;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.internal.BootstrapBag;
import org.glassfish.jersey.internal.BootstrapConfigurator;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.InjectionManager;

import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ClientConfigImpl implements Configurable<ClientConfigImpl>, ExtendedConfig {

	private static class RuntimeConfigConfigurator implements BootstrapConfigurator {

		private final  ClientConfigState runtimeConfig;

		private RuntimeConfigConfigurator( ClientConfigState runtimeConfig) {
			this.runtimeConfig = runtimeConfig;
		}

		@Override
		public void init(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
			bootstrapBag.setConfiguration(runtimeConfig);
			injectionManager.register(Bindings.service(runtimeConfig).to(Configuration.class));
		}
	}


	@Override
	public Configuration getConfiguration() {
		return null;
	}

	@Override
	public  ClientConfigImpl property(String name, Object value) {
		return null;
	}

	@Override
	public  ClientConfigImpl register(Class<?> componentClass) {
		return null;
	}

	@Override
	public  ClientConfigImpl register(Class<?> componentClass, int priority) {
		return null;
	}

	@Override
	public  ClientConfigImpl register(Class<?> componentClass, Class<?>... contracts) {
		return null;
	}

	@Override
	public  ClientConfigImpl register(Class<?> componentClass, Map<Class<?>, Integer> contracts) {
		return null;
	}

	@Override
	public  ClientConfigImpl register(Object component) {
		return null;
	}

	@Override
	public  ClientConfigImpl register(Object component, int priority) {
		return null;
	}

	@Override
	public  ClientConfigImpl register(Object component, Class<?>... contracts) {
		return null;
	}

	@Override
	public  ClientConfigImpl register(Object component, Map<Class<?>, Integer> contracts) {
		return null;
	}

	@Override
	public boolean isProperty(String name) {
		return false;
	}

	@Override
	public RuntimeType getRuntimeType() {
		return null;
	}

	@Override
	public Map<String, Object> getProperties() {
		return null;
	}

	@Override
	public Object getProperty(String name) {
		return null;
	}

	@Override
	public Collection<String> getPropertyNames() {
		return null;
	}

	@Override
	public boolean isEnabled(Feature feature) {
		return false;
	}

	@Override
	public boolean isEnabled(Class<? extends Feature> featureClass) {
		return false;
	}

	@Override
	public boolean isRegistered(Object component) {
		return false;
	}

	@Override
	public boolean isRegistered(Class<?> componentClass) {
		return false;
	}

	@Override
	public Map<Class<?>, Integer> getContracts(Class<?> componentClass) {
		return null;
	}

	@Override
	public Set<Class<?>> getClasses() {
		return null;
	}

	@Override
	public Set<Object> getInstances() {
		return null;
	}
}
