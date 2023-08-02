package client;

import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

//TODO: make this class take our body parsers
// and when doing so, add them to the appropriate function responses

public class HttpClientConfiguration implements Configuration {

    private final Configuration config;

    public HttpClientConfiguration(Configuration config) {
        this.config = config;
    }

//    public HttpClientConfiguration() {
//    }

    protected Configuration getJerseyConfig() {
        return config;
    }

    @Override
    public RuntimeType getRuntimeType() {
        return config.getRuntimeType();
    }

    @Override
    public Map<String, Object> getProperties() {
        return config.getProperties();
    }

    @Override
    public Object getProperty(String name) {
        return config.getProperty(name);
    }

    @Override
    public Collection<String> getPropertyNames() {
        return config.getPropertyNames();
    }

    @Override
    public boolean isEnabled(Feature feature) {
        return config.isEnabled(feature);
    }

    @Override
    public boolean isEnabled(Class<? extends Feature> featureClass) {
        return config.isEnabled(featureClass);
    }

    @Override
    public boolean isRegistered(Object component) {
        return config.isRegistered(component);
    }

    @Override
    public boolean isRegistered(Class<?> componentClass) {
        return config.isRegistered(componentClass);
    }

    @Override
    public Map<Class<?>, Integer> getContracts(Class<?> componentClass) {
        return config.getContracts(componentClass);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return config.getClasses();
    }

    @Override
    public Set<Object> getInstances() {
        return config.getInstances();
    }
}
