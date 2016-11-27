package fr.fscf.contacts.shared.command.result;

import fr.fscf.contacts.client.util.ToStringBuilder;
import fr.fscf.contacts.shared.command.SecureNavigationCommand;
import fr.fscf.contacts.shared.command.result.base.Result;

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

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("authentication", authentication);
        builder.append("granted", granted);
        return builder.toString();
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public boolean isGranted() {
        return granted;
    }

}
