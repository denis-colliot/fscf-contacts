package fr.fscf.contacts.server.config;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import fr.fscf.contacts.server.config.persistence.PersistenceProperties;
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
    protected final void configure() {

        LOGGER.info("Initializing persistence module.");

        // Installs the JPA module.
        install(new JpaPersistModule("app-datasource")
                .properties(PersistenceProperties.init(getPersistencePropertiesFile())));

        // JSR-303 : bean validation.
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        bind(Validator.class).toInstance(validator);

        onJpaModuleInstalled();
    }

    /**
     * Callback executed once JPA Service Module has been installed.
     */
    protected void onJpaModuleInstalled() {
        // Default implementation does nothing.
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
