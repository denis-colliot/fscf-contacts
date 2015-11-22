package fr.fscp.contacts.server.config;

import fr.fscp.contacts.server.config.dispatch.AbstractCommandHandlerModule;
import fr.fscp.contacts.server.handler.SecureNavigationHandler;
import fr.fscp.contacts.shared.command.SecureNavigationCommand;

/**
 * <p>
 * Command-Handler module. Installs automatically dispatch module.
 * </p>
 * <p>
 * Simply bind command classes to their corresponding handler class.
 * </p>
 *
 * @author Denis
 */
public class CommandHandlerModule extends AbstractCommandHandlerModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureHandlers() {
        // Thank you for maintaining alphabetical order.
        bindHandler(SecureNavigationCommand.class, SecureNavigationHandler.class);
    }

}