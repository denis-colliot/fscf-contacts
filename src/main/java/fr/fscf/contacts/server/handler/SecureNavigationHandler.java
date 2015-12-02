package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.shared.command.SecureNavigationCommand;
import fr.fscf.contacts.shared.command.result.SecureNavigationResult;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.model.User;
import fr.fscf.contacts.shared.command.result.Authentication;

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
