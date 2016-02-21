package fr.fscf.contacts.server.security;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.model.Authentication;
import fr.fscf.contacts.server.model.User;
import fr.fscf.contacts.server.security.impl.DatabaseAuthenticator;
import fr.fscf.contacts.shared.security.AuthenticationException;

/**
 * Authenticator service interface.
 *
 * @author Denis
 */
@ImplementedBy(DatabaseAuthenticator.class)
public interface Authenticator {

    /**
     * Authenticates the given credentials.
     *
     * @param login
     *         The user login.
     * @param password
     *         The user plain text password.
     * @return The authenticated {@link User} (never {@code null}).
     * @throws AuthenticationException
     *         If the given credentials cannot authenticate a valid active user.
     */
    Authentication authenticate(final String login, final String password) throws AuthenticationException;

    /**
     * Hashes the given {@code plainTextPassword}.
     *
     * @param plainTextPassword
     *         The plain text password.
     * @return The hashed password.
     */
    String hashPassword(final String plainTextPassword);

    /**
     * Generates a random password.<br>
     * The generated password contains 8 characters with 2 caps, 1 number and 1 special character.
     *
     * @return The generated random password.
     */
    String generatePassword();

}