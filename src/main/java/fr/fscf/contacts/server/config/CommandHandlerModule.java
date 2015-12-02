package fr.fscf.contacts.server.config;

import fr.fscf.contacts.server.handler.SecureNavigationHandler;
import fr.fscf.contacts.shared.command.SecureNavigationCommand;
import fr.fscf.contacts.server.config.dispatch.AbstractCommandHandlerModule;

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