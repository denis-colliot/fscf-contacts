package fr.fscf.contacts.shared.command;

import fr.fscf.contacts.shared.command.base.AbstractCommand;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.dto.FunctionDTO;

/**
 * Gets functions command.
 *
 * @author Denis
 */
public class GetFunctionsCommand extends AbstractCommand<ListResult<FunctionDTO>> {

    public GetFunctionsCommand() {
        // Serialization.
    }

}
