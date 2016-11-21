package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.dao.ContactDAO;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.shared.command.GetContactsCommand;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.shared.dto.ContactDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Handler securing navigation to specific page.
 *
 * @author Denis
 */
public class GetContactsHandler extends AbstractCommandHandler<GetContactsCommand, ListResult<ContactDTO>> {

    static final List<ContactDTO> MOCK_DATA = new ArrayList<>();

    static {
        for (int i = 1; i <= 1000; i++) {
            final ContactDTO contactDTO = new ContactDTO();
            contactDTO.setId((long) i);
            contactDTO.setName("Name #" + i);
            contactDTO.setFirstName("Firstname #" + i);
            contactDTO.setEmail("contact_" + i + "@email.com");
            MOCK_DATA.add(contactDTO);
        }
    }

    @Inject
    private ContactDAO contactDAO;

    @Override
    protected ListResult<ContactDTO> execute(final GetContactsCommand command,
                                             final UserDispatch.UserExecutionContext context) throws CommandException {

        // TODO
        // final List<Contact> contacts = contactDAO.findUserContacts(context.getUser());

        final int start = command.getRange().getStart();
        final int length = command.getRange().getLength();
        final List<ContactDTO> contacts = new ArrayList<>(MOCK_DATA.subList(start,
                Math.min(MOCK_DATA.size(), start + length)));

        return new ListResult<>(contacts, MOCK_DATA.size());
    }

}
