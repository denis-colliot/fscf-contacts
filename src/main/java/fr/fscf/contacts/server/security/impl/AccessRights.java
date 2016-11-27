package fr.fscf.contacts.server.security.impl;

import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.server.model.User;
import fr.fscf.contacts.server.model.referential.GrantType;
import fr.fscf.contacts.server.servlet.base.ServletExecutionContext;
import fr.fscf.contacts.shared.command.SecureNavigationCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static fr.fscf.contacts.server.security.impl.SecuredResources.commandToken;
import static fr.fscf.contacts.server.security.impl.SecuredResources.pageToken;

/**
 * Access rights configuration.
 *
 * @author Denis
 * @deprecated The access rights grant system should rely on database configuration,
 * see {@link AuthenticationSecureSessionValidator} implementation.
 */
@Deprecated
final class AccessRights {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AccessRights.class);

    /**
     * Permissions map linking a secured token to its grant type.
     */
    private static final Map<String, GrantType> permissions = new HashMap<>();

    /**
     * Unchecked resources tokens (they are always granted).
     */
    private static final Set<String> grantedTokens = new HashSet<>();

    /**
     * Token representing <em>missing tokens</em>.<br/>
     * If a token is not declared among security permissions, this token is used.
     */
    private static final String MISSING_TOKEN = "*";

    /**
     * Permissions configuration.
     */
    // TODO Complete permissions.
    static {

        // FIXME For the time being, all missing tokens are considered NOT secured. This line should be deleted in production.
        sperm(MISSING_TOKEN, GrantType.BOTH);

        // Pages.
        sperm(pageToken(Page.LOGIN), GrantType.ANONYMOUS_ONLY);
        sperm(pageToken(Page.HOME), GrantType.AUTHENTICATED_ONLY);
        sperm(pageToken(Page.ASSOCIATIONS), GrantType.AUTHENTICATED_ONLY);
        sperm(pageToken(Page.ASSOCIATION), GrantType.AUTHENTICATED_ONLY);
        sperm(pageToken(Page.CONTACTS), GrantType.AUTHENTICATED_ONLY);
        sperm(pageToken(Page.CONTACT), GrantType.AUTHENTICATED_ONLY);

        // Commands.
        // sperm(commandToken(AddOrgUnit.class), GrantType.AUTHENTICATED_ONLY);

        // Servlet methods.
        // sperm(servletToken(Servlet.FILE, ServletMethod.DOWNLOAD_LOGO), GrantType.AUTHENTICATED_ONLY);
    }

    /**
     * Granted tokens that are always granted in order to optimize application processes.
     */
    static {
        grantedTokens.add(commandToken(SecureNavigationCommand.class));
    }

    /**
     * Grants or refuse {@code user} access to the given {@code token}.
     *
     * @param user
     *         The user (authenticated or anonymous).
     * @param token
     *         The resource token (page, command, servlet method, etc.).
     * @param originPageToken
     *         The origin page token <em>(TODO Not used yet)</em>.
     * @return {@code true} if the user is granted, {@code false} otherwise.
     * @deprecated See {@link AccessRights} deprecated javadoc.
     */
    @Deprecated
    static boolean isGranted(final User user, final String token, final String originPageToken) {

        if (grantedTokens.contains(token)) {
            // Granted tokens ; avoids profile aggregation if user is authenticated.
            return true;
        }

        if (!permissions.containsKey(token)) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("No security permission can be found for token '{}'. Did you forget to declare corresponding 'sperm'?", token);
            }
            return isGranted(user, MISSING_TOKEN, originPageToken);
        }

        final GrantType grantType = permissions.get(token);

        final boolean granted;

        if (user == null || ServletExecutionContext.ANONYMOUS_USER.equals(user)) {
            // Anonymous user.
            granted = grantType != null && grantType != GrantType.AUTHENTICATED_ONLY;

        } else {
            // Authenticated user.
            granted = grantType == null || grantType != GrantType.ANONYMOUS_ONLY;
        }

        return granted;
    }

    // -------------------------------------------------------------------------------------
    //
    // UTILITY METHODS.
    //
    // -------------------------------------------------------------------------------------

    /**
     * <p>
     * Registers a new <u>S</u>ecurity <u>PERM</u>ission for the given {@code token}.
     * </p>
     * <p>
     * ;-)
     * </p>
     *
     * @param token
     *         The resource token.
     * @param grantType
     *         The grant type, see {@link GrantType}.
     */
    private static void sperm(final String token, final GrantType grantType) {
        permissions.put(token, grantType);
    }

    /**
     * Utility class constructor.
     */
    private AccessRights() {
        // Only provides static constants.
    }

}