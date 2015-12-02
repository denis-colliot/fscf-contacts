package fr.fscf.contacts.server.config.persistence.provider;

import java.util.Properties;

/**
 * Persistence configuration provider.
 *
 * @author Denis
 */
public interface PersistenceConfigurationProvider {

    /**
     * Persistence configuration interface.
     *
     * @author Denis
     */
    interface PersistenceConfiguration {

        Properties getProperties();

        String getConnectionUrl();

        String getConnectionUsername();

        String getConnectionPassword();

    }

    /**
     * Provides the persistence configuration.
     *
     * @return The persistence configuration.
     */
    PersistenceConfiguration getConfiguration();

}
