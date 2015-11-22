package fr.fscp.contacts.server.handler;

import fr.fscp.contacts.server.dispatch.impl.UserDispatch;
import fr.fscp.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscp.contacts.server.model.User;
import fr.fscp.contacts.shared.command.SecureNavigationCommand;
import fr.fscp.contacts.shared.command.result.Authentication;
import fr.fscp.contacts.shared.command.result.SecureNavigationResult;
import fr.fscp.contacts.shared.dispatch.CommandException;

/**
 * Created on 08/07/15.
 *
 * @author Denis
 */
public class SecureNavigationHandler extends AbstractCommandHandler<SecureNavigationCommand, SecureNavigationResult> {

    @Override
    protected SecureNavigationResult execute(final SecureNavigationCommand command, final UserDispatch.UserExecutionContext context) throws CommandException {

        final User user = context.getUser();

        return new SecureNavigationResult(
                new Authentication(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getFirstName(),
                        context.getLanguage()
                ), true);
    }

}
