package fr.fscf.contacts.shared.command;

import fr.fscf.contacts.shared.command.base.AbstractCommand;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.dto.ContactDTO;

/**
 * Gets contacts list command.
 *
 * @author Denis
 */
public class GetContactsCommand extends AbstractCommand<ListResult<ContactDTO>> {

    public GetContactsCommand() {
        // Serialization.
    }

}
