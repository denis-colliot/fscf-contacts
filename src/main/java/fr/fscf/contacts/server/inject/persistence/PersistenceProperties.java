package fr.fscf.contacts.server.inject.persistence;

import fr.fscf.contacts.server.inject.ConfigUtils;
import fr.fscf.contacts.server.inject.persistence.provider.CloudUnit;
import fr.fscf.contacts.server.inject.persistence.provider.Heroku;
import fr.fscf.contacts.server.inject.persistence.provider.PersistenceConfigurationProvider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.Properties;

/**
 * Persistence properties initializer.
 *
 * @author Denis
 */
public final class PersistenceProperties {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceProperties.class);

    private PersistenceProperties() {
        // Only provides static methods.
    }

    /**
     * <p>
     * Initializes the persistence properties.
     * </p>
     * <p>
     * Detects automatically local environment from production environment.
     * </p>
     *
     * @param localPersistencePropertiesFile The local persistence properties file (for development/test environment).
     * @return The persistence properties.
     */
    public static Properties init(final String localPersistencePropertiesFile) {

        LOGGER.info("Initializing persistence properties ; System environment: {}", System.getenv());

        final Properties persistenceProperties = new Properties();

        if (!ConfigUtils.isClasspathResourceExists(localPersistencePropertiesFile)) {

            LOGGER.info("Production environment ; loading appropriate persistence configuration.");

            final Properties paasProperties = ConfigUtils.loadProperties("env/paas.properties");
            final String paasType = paasProperties.getProperty("paas.type");

            LOGGER.info("PAAS type: '{}'.", paasType);

            final PersistenceConfigurationProvider provider;
            switch (StringUtils.trimToEmpty(paasType).toLowerCase()) {
                case "heroku":
                    provider = new Heroku();
                    break;
                case "cloudunit":
                    provider = new CloudUnit();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid PAAS type: " + paasType);
            }

            final PersistenceConfigurationProvider.PersistenceConfiguration configuration = provider.getConfiguration();
            persistenceProperties.setProperty("hibernate.connection.url", configuration.getConnectionUrl());
            persistenceProperties.setProperty("hibernate.connection.username", configuration.getConnectionUsername());
            persistenceProperties.setProperty("hibernate.connection.password", configuration.getConnectionPassword());

            copyHibernateProperties(configuration.getProperties(), persistenceProperties);

        } else {

            LOGGER.info("Development environment ; loading local persistence configuration.");

            final Properties localProperties = ConfigUtils.loadProperties(localPersistencePropertiesFile);

            copyHibernateProperties(localProperties, persistenceProperties);

        }

        LOGGER.info("Loaded persistence configuration: {}", persistenceProperties);

        return persistenceProperties;
    }

    /**
     * Copies the hibernate properties from {@code srcProperties} to {@code destProperties}.
     *
     * @param srcProperties  The source properties.
     * @param destProperties The destination properties.
     */
    private static void copyHibernateProperties(final Properties srcProperties, final Properties destProperties) {
        final Enumeration<String> propertyNames = (Enumeration<String>) srcProperties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            final String propertyName = propertyNames.nextElement();
            if (StringUtils.startsWith(propertyName, "hibernate.")) {
                destProperties.setProperty(propertyName, srcProperties.getProperty(propertyName));
            }
        }
    }

}
