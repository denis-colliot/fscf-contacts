package fr.fscf.contacts.server.inject.persistence.provider;

import fr.fscf.contacts.server.inject.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created on 26/11/15.
 *
 * @author Denis
 */
public final class CloudUnit implements PersistenceConfigurationProvider {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CloudUnit.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public PersistenceConfiguration getConfiguration() {

        LOGGER.info("CloudUnit production environment ; using system environment properties.");

        final Properties properties = ConfigUtils.loadProperties("env/cloudunit.properties");
        final String connectionUrlTemplate = properties.getProperty("jdbc.connection.url");

        final String host = System.getenv(properties.getProperty("env.database.host"));
        final String username = System.getenv(properties.getProperty("env.database.user"));
        final String password = System.getenv(properties.getProperty("env.database.password"));
        final String dbName = System.getenv(properties.getProperty("env.database.name"));


        LOGGER.info("Host: '{}' ; Username: '{}' ; Password: '{}' ; DbName: '{}'", host, username, password, dbName);

        return new PersistenceConfiguration() {

            @Override
            public Properties getProperties() {
                return properties;
            }

            @Override
            public String getConnectionUrl() {
                return connectionUrlTemplate.replace("{host}", host).replace("{dbName}", dbName);
            }

            @Override
            public String getConnectionUsername() {
                return username;
            }

            @Override
            public String getConnectionPassword() {
                return password;
            }
        };
    }

}
