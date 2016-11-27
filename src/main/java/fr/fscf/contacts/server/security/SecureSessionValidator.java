package fr.fscf.contacts.server.security;

import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.security.SecureDispatchAsync;
import fr.fscf.contacts.client.security.SecureDispatchService;
import fr.fscf.contacts.server.model.User;
import fr.fscf.contacts.shared.servlet.Servlets;
import fr.fscf.contacts.shared.command.base.Command;
import fr.fscf.contacts.shared.command.result.base.Result;

/**
 * Implementors must provide an implementation of this interface and provide it to the {@link
 * SecureDispatchService} implementation so that it can check for valid authentication
 * tokens.
 *
 * @author Denis
 */
public interface SecureSessionValidator {

    /**
     * Defines the different result types of the session validation.
     *
     * @author Denis
     */
    public static enum AccessType {

        /**
         * The session is invalid (no token or corrupted token).
         */
        INVALID_SESSION,

        /**
         * The user doesn't have a sufficient role.
         */
        UNAUTHORIZED_ACCESS,

        /**
         * The validation succeed.
         */
        ACCESS_GRANTED;

    }

    /**
     * The validation result.
     *
     * @author Denis
     */
    public static final class Access {

        /**
         * The access type.
         */
        private final AccessType accessType;

        /**
         * The user for the current session id.
         */
        private final User user;

        public Access(final AccessType accessType, final User user) {
            this.accessType = accessType;
            this.user = user;
        }

        /**
         * Gets the access type.
         *
         * @return The access type.
         */
        public AccessType getAccessType() {
            return accessType;
        }

        /**
         * Gets the {@link User} for the current session id.
         *
         * @return The {@link User} for the current session id.
         */
        public User getUser() {
            return user;
        }
    }

    /**
     * Validates the access to the given {@code servlet} resource for the {@code authenticationToken}.
     *
     * @param authenticationToken
     *         The authentication token.
     * @param servlet
     *         The servlet name.
     * @param method
     *         The servlet method.
     * @param originPageToken
     *         The origin page token.
     * @return The validation access result.
     * @see SecureSessionValidator.Access
     */
    Access validate(final String authenticationToken, final Servlets.Servlet servlet, final Servlets.ServletMethod method, final String originPageToken);

    /**
     * Validates the access to the given {@code commandExecution} resource for the {@code authenticationToken}.
     *
     * @param authenticationToken
     *         The authentication token.
     * @param commandExecution
     *         The command execution (containing command and origin page token).
     * @return The validation access result.
     * @see SecureSessionValidator.Access
     */
    Access validate(final String authenticationToken, final SecureDispatchAsync.CommandExecution<? extends Command<?>, ? extends Result> commandExecution);

    /**
     * Returns the grant access to the given {@code page} resource for the {@code user}.
     *
     * @param user
     *         The user.
     * @param page
     *         The {@link Page} resource to secure.
     * @return {@code true} if the {@code user} is granted to access {@code page} resource, {@code false} otherwise.
     */
    boolean isUserGranted(final User user, final Page page);

}