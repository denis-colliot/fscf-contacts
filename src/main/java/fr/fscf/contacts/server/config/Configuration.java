package fr.fscf.contacts.server.config;

import com.google.inject.ImplementedBy;

import java.util.Map;

/**
 * Application configuration service.
 *
 * @author Denis
 */
@ImplementedBy(PropertiesConfiguration.class)
public interface Configuration {

    /**
     * Returns all the configurations.
     *
     * @return All the configurations.
     */
    Map<String, String> all();

    /**
     * Returns the given configuration key corresponding value.
     *
     * @param key The configuration key.
     * @return The configuration value or {@code null} if the key does not exist.
     */
    String get(String key);

    /**
     * Returns the given configuration key corresponding value.
     *
     * @param key          The configuration key.
     * @param defaultValue The default value.
     * @return The configuration value or {@code defaultValue} if the key does not exist.
     */
    String get(String key, String defaultValue);

    /**
     * Returns the given configuration key corresponding {@code Boolean} value.
     *
     * @param key The configuration key.
     * @return The configuration {@code Boolean} value or {@code null} if the key does not exist.
     */
    Boolean getBoolean(String key);

    /**
     * Returns the given configuration key corresponding {@code Boolean} value.
     *
     * @param key          The configuration key.
     * @param defaultValue The default value.
     * @return The configuration {@code Boolean} value or {@code defaultValue} if the key does not exist.
     */
    Boolean getBoolean(String key, Boolean defaultValue);

}
