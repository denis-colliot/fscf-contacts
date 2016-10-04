package fr.fscf.contacts.server.config;

import fr.fscf.contacts.server.inject.ConfigUtils;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Properties;

/**
 * Application configuration using {@code .properties} files.
 *
 * @author Denis
 */
final class PropertiesConfiguration implements Configuration {

    private final Properties properties;

    @Inject
    private PropertiesConfiguration() {
        properties = new Properties();
        properties.putAll(ConfigUtils.loadProperties("configuration.properties"));
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
