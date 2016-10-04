package fr.fscf.contacts.server.inject.persistence.provider;

import fr.fscf.contacts.server.inject.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created on 26/11/15.
 *
 * @author Denis
 */
public final class Heroku implements PersistenceConfigurationProvider {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Heroku.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public PersistenceConfiguration getConfiguration() {

        LOGGER.info("Heroku production environment ; using system environment properties.");

        final Properties properties = ConfigUtils.loadProperties("env/heroku.properties");
        final String envURI = System.getenv(properties.getProperty("env.database.uri"));
        final String connectionUrlTemplate = properties.getProperty("jdbc.connection.url");

        final URI dbUri;
        try {

            dbUri = new URI(envURI);

        } catch (final URISyntaxException e) {
            throw new IllegalArgumentException("Invalid heroku environment URI: " + envURI);
        }

        final String host = dbUri.getHost();
        final String port = String.valueOf(dbUri.getPort());
        final String username = dbUri.getUserInfo().split(":")[0];
        final String password = dbUri.getUserInfo().split(":")[1];
        final String path = dbUri.getPath();

        LOGGER.info("Host: '{}:{}' ; Username: '{}' ; Password: '{}' ; Path: '{}'", host, port, username, password, path);

        return new PersistenceConfiguration() {

            @Override
            public Properties getProperties() {
                return properties;
            }

            @Override
            public String getConnectionUrl() {
                return connectionUrlTemplate.replace("{host}", host).replace("{port}", port).replace("{path}", path);
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
