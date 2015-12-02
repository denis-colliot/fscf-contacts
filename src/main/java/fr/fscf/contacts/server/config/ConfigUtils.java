package fr.fscf.contacts.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration utility methods.
 *
 * @author Denis
 */
public final class ConfigUtils {

    /**
     * Logger.
     */
    private static Logger LOGGER = LoggerFactory.getLogger(ConfigUtils.class);

    /**
     * Loads the given {@code file} and returns the corresponding properties.
     *
     * @param file
     *         The file to read.
     * @return The loaded properties.
     */
    public static Properties loadProperties(final String file) {
        try (final InputStream is = ConfigUtils.class.getClassLoader().getResourceAsStream(file)) {

            LOGGER.info("Loading properties from file '{}'.", file);

            final Properties properties = new Properties();
            properties.load(is);

            LOGGER.trace("Loaded properties: {}", properties);

            return properties;

        } catch (final Exception e) {
            LOGGER.error("Properties loading failure with file '" + file + "'.", e);
            throw new UnsupportedOperationException("Persistence configuration file '" + file + "' " +
                    "is missing or an error occurred while reading it.");
        }
    }

    /**
     * Returns if the given classpath {@code resource} exists.
     *
     * @param resource
     *         The classpath resource name.
     * @return {@code true} if the resource exists, {@code false} otherwise.
     */
    public static boolean isClasspathResourceExists(final String resource) {
        return ConfigUtils.class.getClassLoader().getResource(resource) != null;
    }

    private ConfigUtils() {
        // Utility class constructor.
    }

}
