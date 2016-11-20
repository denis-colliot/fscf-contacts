package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.config.Configuration;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.server.security.SecureSessionValidator;
import fr.fscf.contacts.shared.command.GetConfigCommand;
import fr.fscf.contacts.shared.command.result.MapResult;
import fr.fscf.contacts.shared.dispatch.CommandException;

import javax.inject.Inject;

/**
 * Handler securing navigation to specific page.
 *
 * @author Denis
 */
public class GetConfigHandler extends AbstractCommandHandler<GetConfigCommand, MapResult<String, String>> {

    /**
     * Injected {@link SecureSessionValidator}.
     */
    @Inject
    private Configuration configuration;

    @Override
    protected MapResult<String, String> execute(final GetConfigCommand command,
                                                final UserDispatch.UserExecutionContext context) throws CommandException {

        return new MapResult<>(configuration.all());
    }

}
