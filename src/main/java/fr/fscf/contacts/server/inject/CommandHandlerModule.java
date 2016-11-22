package fr.fscf.contacts.server.inject;

import fr.fscf.contacts.server.handler.*;
import fr.fscf.contacts.server.inject.dispatch.AbstractCommandHandlerModule;
import fr.fscf.contacts.shared.command.*;

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
        bindHandler(GetContactCommand.class, GetContactHandler.class);
        bindHandler(GetContactsCommand.class, GetContactsHandler.class);
        bindHandler(SaveContactCommand.class, SaveContactHandler.class);
        bindHandler(SecureNavigationCommand.class, SecureNavigationHandler.class);
    }

}