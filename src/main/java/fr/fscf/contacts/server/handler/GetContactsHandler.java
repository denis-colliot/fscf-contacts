package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.dao.ContactDAO;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.server.model.Contact;
import fr.fscf.contacts.shared.command.GetContactsCommand;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.command.result.MapResult;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.shared.dto.ContactDTO;

import javax.inject.Inject;
import java.util.List;

/**
 * Handler securing navigation to specific page.
 *
 * @author Denis
 */
public class GetContactsHandler extends AbstractCommandHandler<GetContactsCommand, ListResult<ContactDTO>> {

    @Inject
    private ContactDAO contactDAO;

    @Override
    protected ListResult<ContactDTO> execute(final GetContactsCommand command,
                                             final UserDispatch.UserExecutionContext context) throws CommandException {

        final List<Contact> contacts = contactDAO.findUserContacts(context.getUser());

        // TODO

        return new ListResult<ContactDTO>();
    }

}
