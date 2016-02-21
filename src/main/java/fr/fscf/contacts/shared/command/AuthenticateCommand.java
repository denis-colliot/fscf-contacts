package fr.fscf.contacts.shared.command;

import fr.fscf.contacts.shared.command.base.AbstractCommand;
import fr.fscf.contacts.shared.command.result.Authentication;
import fr.fscf.contacts.shared.dto.LoginDTO;

/**
 * Authenticate command.
 *
 * @author Denis
 */
public class AuthenticateCommand extends AbstractCommand<Authentication> {

    private LoginDTO loginDTO;

    public AuthenticateCommand() {
        // Serialization.
    }

    public AuthenticateCommand(final LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }
}
