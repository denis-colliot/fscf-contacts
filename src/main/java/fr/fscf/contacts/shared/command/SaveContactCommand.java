package fr.fscf.contacts.shared.command;

import fr.fscf.contacts.shared.command.base.AbstractCommand;
import fr.fscf.contacts.shared.dto.ContactDTO;

/**
 * Saves a contact command.
 *
 * @author Denis
 */
public class SaveContactCommand extends AbstractCommand<ContactDTO> {

    private ContactDTO contact;

    public SaveContactCommand() {
        // Serialization.
    }

    public SaveContactCommand(ContactDTO contact) {
        this.contact = contact;
    }

    public ContactDTO getContact() {
        return contact;
    }
}
