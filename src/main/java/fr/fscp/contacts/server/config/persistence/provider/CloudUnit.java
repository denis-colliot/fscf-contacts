package fr.fscp.contacts.server.config.persistence.provider;

import fr.fscp.contacts.server.config.ConfigUtils;
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

        final String host = System.getenv(properties.getProperty("env.database.host"));
        final String username = System.getenv(properties.getProperty("env.database.user"));
        final String password = System.getenv(properties.getProperty("env.database.password"));
        final String dbName = System.getenv(properties.getProperty("env.database.name"));

        final String url = properties.getProperty("jdbc.connection.url");

        LOGGER.info("Host: '{}' ; Username: '{}' ; Password: '{}' ; DbName: '{}'", host, username, password, dbName);

        return new PersistenceConfiguration() {

            @Override
            public Properties getProperties() {
                return properties;
            }

            @Override
            public String getConnectionUrl() {
                return url.replace("{host}", host).replace("{dbName}", dbName);
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
