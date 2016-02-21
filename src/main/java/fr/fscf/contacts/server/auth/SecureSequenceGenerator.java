package fr.fscf.contacts.server.auth;

import fr.fscf.contacts.server.model.Authentication;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import java.io.Serializable;

/**
 * <p>
 * Identifier Generator that generates unique IDs for our authentication that are sufficiently random so that they
 * cannot be guessed.
 * </p>
 * <p>
 * <em>This class is referenced by name by the {@link Authentication} domain object.</em>
 * </p>
 *
 * @author Denis
 */
// This class is referenced by name by the Authentication domain object.
public class SecureSequenceGenerator implements org.hibernate.id.IdentifierGenerator {

    /**
     * {@inheritDoc}
     */
    @Override
    public Serializable generate(final SessionImplementor session, final Object object) throws HibernateException {
        return SecureTokenGenerator.generate();
    }

}