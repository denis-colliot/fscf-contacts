package fr.fscf.contacts.shared.command;

import com.google.gwt.view.client.Range;
import fr.fscf.contacts.shared.command.base.AbstractTableCommand;
import fr.fscf.contacts.shared.dto.ContactDTO;
import fr.fscf.contacts.shared.util.Sort;

/**
 * Gets contacts list command.
 *
 * @author Denis
 */
public class GetContactsCommand extends AbstractTableCommand<ContactDTO> {

    public GetContactsCommand() {
        // Serialization.
    }

    public GetContactsCommand(Range range, Sort sort) {
        super(range, sort);
    }

}
