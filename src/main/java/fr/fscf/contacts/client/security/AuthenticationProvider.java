package fr.fscf.contacts.client.security;

import com.google.gwt.user.client.Cookies;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.event.bus.EventBus;
import fr.fscf.contacts.shared.Language;
import fr.fscf.contacts.shared.command.result.Authentication;
import fr.fscf.contacts.shared.util.ClientUtils;

import java.util.Date;

/**
 * Provides the {@link Authentication}.
 *
 * @author Denis
 */
@Singleton
public class AuthenticationProvider implements Provider<Authentication> {

    /**
     * <p>
     * The cached authenticated user data.<br/>
     * This data is updated at each page access ; see {@link EventBus EventBus}.
     * </p>
     * <p>
     * <em>Should never be {@code null}.</em>
     * </p>
     */
    private Authentication authentication = new Authentication();

    /**
     * Returns the current authentication.<br/>
     * If no user is currently authenticated, the method returns an empty {@link Authentication} instance (with
     * {@code null} token).
     *
     * @return The current authentication or empty authentication (never returns {@code null}).
     */
    @Override
    public Authentication get() {

        if (isAnonymous()) {
            clearAuthentication();

        } else {
            final String token = Cookies.getCookie(fr.fscf.contacts.shared.util.Cookies.AUTH_TOKEN_COOKIE);
            authentication.setAuthenticationToken(token);
        }

        return authentication;
    }

    /**
     * <p>
     * Logins the given {@code authentication} by setting cookies and updating cached data.
     * </p>
     * <p>
     * <em>Should be called <b>exclusively</b> by {@code LoginPresenter}.</em>
     * </p>
     *
     * @param authentication
     *         The authentication. Its {@code authenticationToken} <b>must</b> be set.
     */
    public void login(final Authentication authentication) {

        if (authentication == null) {
            clearAuthentication();
            return;
        }

        // Cookies properties.
        final String path = fr.fscf.contacts.shared.util.Cookies.COOKIE_PATH;
        final String domain = fr.fscf.contacts.shared.util.Cookies.COOKIE_DOMAIN;
        final boolean secure = fr.fscf.contacts.shared.util.Cookies.COOKIE_SECURED;

        // Sets the cookies.
        Cookies.setCookie(fr.fscf.contacts.shared.util.Cookies.AUTH_TOKEN_COOKIE, authentication.getAuthenticationToken(), oneDayLater(), domain, path, secure);
        Cookies.setCookie(fr.fscf.contacts.shared.util.Cookies.LANGUAGE_COOKIE, authentication.getLanguage().getLocale(), oneDayLater(), domain, path, secure);

        // Caches the authentication data.
        this.authentication = authentication;
    }

    /**
     * <p>
     * Updates the cached {@link Authentication} instance.
     * </p>
     * <p>
     * <em>Should be called <b>exclusively</b> by {@link EventBus}.</em>
     * </p>
     *
     * @param authentication
     *         The authentication. Its {@code authenticationToken} is automatically updated with the one set in cookie.
     */
    public void updateCache(final Authentication authentication) {

        if (authentication == null) {
            clearAuthentication();
            return;
        }

        // Caches the authentication data.
        authentication.setAuthenticationToken(Cookies.getCookie(fr.fscf.contacts.shared.util.Cookies.AUTH_TOKEN_COOKIE));
        this.authentication = authentication;
    }

    /**
     * <p>
     * Clears the current authentication (cookies + cached authentication data).
     * </p>
     * <p>
     * Maintains the {@link Language} previously set.
     * </p>
     *
     * @return {@code true} if the authentication has been successfully cleared.
     */
    public boolean clearAuthentication() {

        Cookies.removeCookie(fr.fscf.contacts.shared.util.Cookies.AUTH_TOKEN_COOKIE, fr.fscf.contacts.shared.util.Cookies.COOKIE_PATH);

        authentication = new Authentication(authentication.getLanguage());

        return true;
    }

    /**
     * Checks if no user is currently authenticated.
     *
     * @return {@code true} if no user is currently authenticated, {@code false} otherwise.
     */
    public boolean isAnonymous() {

        final boolean anonymous = ClientUtils.isBlank(Cookies.getCookie(fr.fscf.contacts.shared.util.Cookies.AUTH_TOKEN_COOKIE));

        if (anonymous) {
            // Just to be sure that all cookies are properly cleared.
            clearAuthentication();
        }

        return anonymous;
    }

    /**
     * Returns a date corresponding to present time plus a full day (24h).
     *
     * @return a date corresponding to present time plus a full day (24h).
     */
    private static Date oneDayLater() {
        return new Date(new Date().getTime() + 1000 * 60 * 60 * 24);
    }

}