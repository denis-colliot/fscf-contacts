package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.dao.AuthenticationDAO;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.server.security.Authenticator;
import fr.fscf.contacts.shared.command.AuthenticateCommand;
import fr.fscf.contacts.shared.command.result.Authentication;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.shared.dto.LoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Handler authenticating user.
 *
 * @author Denis
 */
public class AuthenticatedHandler extends AbstractCommandHandler<AuthenticateCommand, Authentication> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatedHandler.class);

    @Inject
    private Authenticator authenticator;

    @Inject
    private AuthenticationDAO authenticationDAO;

    @Override
    protected Authentication execute(final AuthenticateCommand command, final UserDispatch.UserExecutionContext context) throws CommandException {

        final LoginDTO login = command.getLoginDTO();

        LOGGER.info("Authenticating login '{}'.", login.getLogin());

        final fr.fscf.contacts.server.model.Authentication authentication = authenticator.authenticate(
                login.getLogin(),
                login.getPassword());

        final Authentication result = new Authentication(
                authentication.getUser().getId(),
                authentication.getUser().getEmail(),
                authentication.getUser().getName(),
                authentication.getUser().getFirstName(),
                context.getLanguage());

        result.setAuthenticationToken(authentication.getId());

        return result;
    }

}
