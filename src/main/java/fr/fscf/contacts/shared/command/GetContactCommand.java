package fr.fscf.contacts.shared.command;

import fr.fscf.contacts.shared.command.base.AbstractCommand;
import fr.fscf.contacts.shared.dto.ContactDTO;

/**
 * Gets one contact command.
 *
 * @author Denis
 */
public class GetContactCommand extends AbstractCommand<ContactDTO> {

    private Long contactId;

    public GetContactCommand() {
        // Serialization.
    }

    public GetContactCommand(Long contactId) {
        this.contactId = contactId;
    }

    public Long getContactId() {
        return contactId;
    }
}
