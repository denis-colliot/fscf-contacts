package fr.fscf.contacts.server.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import fr.fscf.contacts.server.security.SecureSessionValidator;
import fr.fscf.contacts.server.security.impl.AuthenticationSecureSessionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Security module.
 *
 * @author Denis
 */
public class SecurityModule extends AbstractModule {

    /**
     * Log.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SecurityModule.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {

        if (LOG.isInfoEnabled()) {
            LOG.info("Installing security module.");
        }

        bind(SecureSessionValidator.class).to(AuthenticationSecureSessionValidator.class).in(Singleton.class);
        //bind(Authenticator.class).to(DatabaseAuthenticator.class).in(Singleton.class);
    }

}