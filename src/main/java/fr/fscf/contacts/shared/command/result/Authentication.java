package fr.fscf.contacts.shared.command.result;

import fr.fscf.contacts.client.security.AuthenticationProvider;
import fr.fscf.contacts.client.util.ToStringBuilder;
import fr.fscf.contacts.server.servlet.base.ServletExecutionContext;
import fr.fscf.contacts.shared.Language;
import fr.fscf.contacts.shared.command.result.base.Result;

/**
 * <p>
 * Encapsulates authenticated user identity. If no user is authenticated, this object encapsulates <em>anonymous</em>
 * user identity, see {@link ServletExecutionContext#ANONYMOUS_USER ANONYMOUS_USER}.
 * </p>
 * <p>
 * This object is managed by {@link AuthenticationProvider}.
 * </p>
 *
 * @author Denis
 * @see AuthenticationProvider
 * @see ServletExecutionContext#ANONYMOUS_USER
 */
public class Authentication implements Result {

    /**
     * The authentication token required for calls to the command dispatch service.
     */
    private String authenticationToken;

    /**
     * The currently authenticated user's unique id.
     */
    private Long userId;

    /**
     * The currently authenticated user's email.
     */
    private String userEmail;

    /**
     * The currently authenticated user's last name.
     */
    private String userName;

    /**
     * The currently authenticated user's first name.
     */
    private String userFirstName;

    /**
     * The currently authenticated user's language.
     */
    private Language language;

    /**
     * Necessary constructor for serialization.
     */
    public Authentication() {
        // Serialization.
    }

    /**
     * Initializes a new {@code Authentication} instance with the given {@code language}.
     *
     * @param language
     *         The language.
     */
    public Authentication(Language language) {
        this.language = language;
    }

    /**
     * Initializes a new {@code Authentication} instance.
     *
     * @param userId
     *         The user's id (from the server's database).
     * @param userEmail
     *         The user's email.
     * @param userName
     *         The user's last name.
     * @param userFirstName
     *         The user's first name.
     * @param language
     *         The user's language.
     */
    public Authentication(Long userId, String userEmail, String userName, String userFirstName, Language language) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.language = language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        final ToStringBuilder builder = new ToStringBuilder(this);

        builder.append("userId", userId);
        builder.append("userEmail", userEmail);
        builder.append("userName", userName);
        builder.append("userFirstName", userFirstName);
        builder.append("language", language);

        return builder.toString();
    }

    // ------------------------------------------------------------
    //
    // GETTERS & SETTERS.
    //
    // ------------------------------------------------------------

    /**
     * Returns the server authentication token.
     *
     * @return The server authentication token.
     */
    public String getAuthenticationToken() {
        return authenticationToken;
    }

    /**
     * Sets the authentication token.
     * </p>
     * <p>
     * <em>Should <b>only</b> be called by {@link fr.fscf.contacts.shared.command.AuthenticateCommand} or {@link
     * AuthenticationProvider}.</em>
     * </p>
     *
     * @param authenticationToken
     *         The authentication token.
     */
    // Only required setter.
    public void setAuthenticationToken(final String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    /**
     * Returns the authenticated user id or {@code null} if anonymous.
     *
     * @return The authenticated user id or {@code null} if anonymous.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Returns the authenticated user email or {@code null} if anonymous.
     *
     * @return The authenticated user email or {@code null} if anonymous.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Returns the authenticated user last name or {@code null} if anonymous.
     *
     * @return The authenticated user last name or {@code null} if anonymous.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the authenticated user first name or {@code null} if anonymous.
     *
     * @return The authenticated user first name or {@code null} if anonymous.
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    public Language getLanguage() {
        return language;
    }
}