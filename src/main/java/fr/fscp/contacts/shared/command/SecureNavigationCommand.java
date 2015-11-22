package fr.fscp.contacts.shared.command;

import fr.fscp.contacts.client.navigation.Page;
import fr.fscp.contacts.shared.command.base.AbstractCommand;
import fr.fscp.contacts.shared.command.result.SecureNavigationResult;

/**
 * Secure navigation command.
 *
 * @author Denis
 */
public class SecureNavigationCommand extends AbstractCommand<SecureNavigationResult> {

    private Page page;

    public SecureNavigationCommand() {
        // Serialization.
    }

    public SecureNavigationCommand(final Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

}
