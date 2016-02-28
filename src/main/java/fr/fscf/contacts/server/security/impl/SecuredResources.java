package fr.fscf.contacts.server.security.impl;

import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.shared.command.SecureNavigationCommand;
import fr.fscf.contacts.shared.command.base.Command;
import fr.fscf.contacts.shared.servlet.Servlets;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility methods for secured resources.
 *
 * @author Denis
 */
final class SecuredResources {

    /**
     * Token representing a <em><b>any</b> missing feature</em>.<br/>
     * If a page feature is not declared, this token is used as a fallback (handy for development stage).
     */
    public static final String MISSING_TOKEN = "*";

    /**
     * Unchecked resources tokens (they are always granted).
     */
    public static final Set<String> grantedTokens = new HashSet<>();

    /**
     * Granted tokens that are always granted in order to optimize application processes.
     */
    static {
        grantedTokens.add(commandToken(SecureNavigationCommand.class));
    }

    /**
     * Return the <em>resource</em> token for the given servlet arguments.
     *
     * @param servlet
     *         The {@link Servlets.Servlet} name.
     * @param method
     *         The {@link Servlets.Servlet} method.
     * @return the <em>resource</em> token for the given servlet arguments, or {@code null}.
     */
    public static String servletToken(final Servlets.Servlet servlet, final Servlets.ServletMethod method) {
        if (servlet == null || method == null) {
            return null;
        }
        return servlet.name() + '#' + method.name();
    }

    /**
     * Return the <em>resource</em> token for the given {@code commandClass}.
     *
     * @param commandClass
     *         The {@link Command} class.
     * @return the <em>resource</em> token for the given {@code commandClass}, or {@code null}.
     */
    public static String commandToken(final Class<? extends Command> commandClass) {
        if (commandClass == null) {
            return null;
        }
        return commandClass.getName();
    }

    /**
     * Return the <em>resource</em> token for the given {@code page}.
     *
     * @param page
     *         The {@link Page} instance.
     * @return the <em>resource</em> token for the given {@code page}, or {@code null}.
     */
    public static String pageToken(final Page page) {
        if (page == null) {
            return null;
        }
        return page.getToken();
    }

    private SecuredResources() {
    }
}
