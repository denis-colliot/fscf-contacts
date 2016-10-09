package fr.fscf.contacts.server.inject.persistence;

import fr.fscf.contacts.server.inject.ConfigUtils;
import fr.fscf.contacts.server.inject.persistence.provider.CloudUnit;
import fr.fscf.contacts.server.inject.persistence.provider.Heroku;
import fr.fscf.contacts.server.inject.persistence.provider.PersistenceConfigurationProvider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Persistence properties builder.
 *
 * @author Denis
 */
@Singleton
public final class PersistenceProperties extends Properties {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceProperties.class);

    /**
     * <p>
     * Initializes the persistence properties.
     * </p>
     * <p>
     * Detects automatically local environment from production environment.
     * </p>
     *
     * @param localPersistencePropertiesFile The local persistence properties file (for development/test environment).
     */
    @Inject
    public PersistenceProperties(final String localPersistencePropertiesFile) {
        super();

        LOGGER.info("Initializing persistence properties ; System environment: {}", System.getenv());

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
            setProperty("hibernate.connection.url", configuration.getConnectionUrl());
            setProperty("hibernate.connection.username", configuration.getConnectionUsername());
            setProperty("hibernate.connection.password", configuration.getConnectionPassword());

            putAll(configuration.getProperties());

        } else {

            LOGGER.info("Development environment ; loading local persistence configuration.");

            putAll(ConfigUtils.loadProperties(localPersistencePropertiesFile));
        }

        LOGGER.info("Loaded persistence configuration: {}", this);
    }

}
