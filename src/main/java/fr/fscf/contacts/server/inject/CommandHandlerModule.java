package fr.fscf.contacts.server.inject;

import fr.fscf.contacts.server.handler.GetConfigHandler;
import fr.fscf.contacts.server.inject.dispatch.AbstractCommandHandlerModule;
import fr.fscf.contacts.server.handler.AuthenticatedHandler;
import fr.fscf.contacts.server.handler.SecureNavigationHandler;
import fr.fscf.contacts.shared.command.GetConfigCommand;
import fr.fscf.contacts.shared.command.AuthenticateCommand;
import fr.fscf.contacts.shared.command.SecureNavigationCommand;

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
        bindHandler(AuthenticateCommand.class, AuthenticatedHandler.class);
        bindHandler(GetConfigCommand.class, GetConfigHandler.class);
        bindHandler(SecureNavigationCommand.class, SecureNavigationHandler.class);
    }

}