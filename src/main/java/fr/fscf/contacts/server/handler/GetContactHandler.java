package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.dao.ContactDAO;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.shared.command.GetContactCommand;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.shared.dto.ContactDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Objects;

/**
 * Handler securing navigation to specific page.
 *
 * @author Denis
 */
public class GetContactHandler extends AbstractCommandHandler<GetContactCommand, ContactDTO> {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveContactHandler.class);

    @Inject
    private ContactDAO contactDAO;

    @Override
    protected ContactDTO execute(final GetContactCommand command,
                                 final UserDispatch.UserExecutionContext context) throws CommandException {

        final Long contactId = command.getContactId();

        LOGGER.info("About to retrieve contact with id {}", contactId);

        // TODO
        // final List<Contact> contacts = contactDAO.findUserContacts(context.getUser());

        return GetContactsHandler.MOCK_DATA.parallelStream()
                .filter(Objects::nonNull)
                .filter(contact -> contact.getId().equals(contactId))
                .findFirst()
                .orElse(null);
    }

}
