package fr.fscp.contacts.server.config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;
import fr.fscp.contacts.server.config.persistence.PersistenceProperties;
import fr.fscp.contacts.server.dao.AuthenticationDAO;
import fr.fscp.contacts.server.dao.PostDAO;
import fr.fscp.contacts.server.dao.impl.AuthenticationDAOImpl;
import fr.fscp.contacts.server.dao.impl.PostDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by Denis on 24/04/15.
 *
 * @author Denis
 */
public class PersistenceModule extends AbstractModule {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceModule.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {

        LOGGER.info("Initializing persistence module.");

        // Installs the JPA module.
        install(new JpaPersistModule("app-datasource")
                .properties(PersistenceProperties.init(getPersistencePropertiesFile())));

        // JSR-303 : bean validation.
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        bind(Validator.class).toInstance(validator);

        bind(AuthenticationDAO.class).to(AuthenticationDAOImpl.class).in(Singleton.class);
        bind(PostDAO.class).to(PostDAOImpl.class).in(Singleton.class);
    }

    /**
     * Returns the local persistence properties file name.
     *
     * @return The local persistence properties file name.
     */
    protected String getPersistencePropertiesFile() {
        return "env/local.properties";
    }

}
