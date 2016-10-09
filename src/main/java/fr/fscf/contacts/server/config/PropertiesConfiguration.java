package fr.fscf.contacts.server.config;

import fr.fscf.contacts.server.inject.ConfigUtils;
import fr.fscf.contacts.server.inject.persistence.PersistenceProperties;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Application configuration using {@code .properties} files.
 *
 * @author Denis
 */
final class PropertiesConfiguration implements Configuration {

    private final Properties properties;

    @Inject
    private PropertiesConfiguration(final PersistenceProperties persistenceProperties) {
        properties = new Properties();
        properties.putAll(ConfigUtils.loadProperties("configuration.properties"));
        properties.putAll(ConfigUtils.loadProperties("git.properties"));
        properties.putAll(persistenceProperties);
    }

    @Override
    public Map<String, String> all() {
        return properties.stringPropertyNames()
                .stream()
                .collect(Collectors.toMap(key -> key, properties::getProperty));
    }

    @Override
    public String get(String key) {
        return get(key, null);
    }

    @Override
    public String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }

    @Override
    public Boolean getBoolean(String key, Boolean defaultValue) {
        final Optional<String> raw = Optional.ofNullable(get(key));
        return raw.map(Boolean::parseBoolean).orElse(defaultValue);
    }
}
