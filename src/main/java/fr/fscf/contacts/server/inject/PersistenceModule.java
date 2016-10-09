package fr.fscf.contacts.server.inject;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import fr.fscf.contacts.server.inject.persistence.PersistenceProperties;
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
class PersistenceModule extends AbstractModule {

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

        // Install JpaPersist module.
        install(new JpaPersistModule("app-datasource")
                .properties(new PersistenceProperties(getPersistencePropertiesFile())));

        // JSR-303 : bean validation.
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        bind(Validator.class).toInstance(validator);
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
