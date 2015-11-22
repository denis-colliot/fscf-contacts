package fr.fscp.contacts.shared.command.result;

import fr.fscp.contacts.shared.command.result.base.Result;
import fr.fscp.contacts.shared.command.SecureNavigationCommand;

/**
 * {@link SecureNavigationCommand} corresponding result.
 *
 * @author Denis
 */
public class SecureNavigationResult implements Result {

    private Authentication authentication;

    private boolean granted;

    public SecureNavigationResult() {
        // Serialization.
    }

    public SecureNavigationResult(Authentication authentication, boolean granted) {
        this.authentication = authentication;
        this.granted = granted;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public boolean isGranted() {
        return granted;
    }

}
