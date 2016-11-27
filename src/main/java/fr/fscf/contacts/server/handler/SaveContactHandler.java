package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.dao.AffectationDAO;
import fr.fscf.contacts.server.dao.ContactDAO;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.server.mapper.BeanMapper;
import fr.fscf.contacts.server.model.Affectation;
import fr.fscf.contacts.server.model.Contact;
import fr.fscf.contacts.server.model.Function;
import fr.fscf.contacts.server.model.referential.AffectationStatus;
import fr.fscf.contacts.shared.command.SaveContactCommand;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.shared.dto.ContactDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Objects;

/**
 * Handler for {@link SaveContactCommand}.
 *
 * @author Denis
 */
public class SaveContactHandler extends AbstractCommandHandler<SaveContactCommand, ContactDTO> {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveContactHandler.class);

    @Inject
    private ContactDAO contactDAO;

    @Inject
    private AffectationDAO affectationDAO;

    @Inject
    private BeanMapper beanMapper;

    @Override
    protected ContactDTO execute(final SaveContactCommand command,
                                 final UserDispatch.UserExecutionContext context) throws CommandException {

        final ContactDTO contactDTO = command.getContact();

        if (Objects.isNull(contactDTO)) {
            throw new CommandException("Invalid contact");
        }

        Contact contact = beanMapper.map(contactDTO, Contact.class);

        LOGGER.info("About to persist following contact: {}", contact);

        contact = contactDAO.persist(contact, context.getUser());

        // TODO Check if contact already exists with a different id.

        if (Objects.isNull(contact.getId())) {
            LOGGER.info("About to persist contact affectation: {}", contact);

            final Affectation affectation = new Affectation();
            affectation.setFunction(beanMapper.map(contactDTO.getFunction(), Function.class));
            affectation.setContact(contact);
            affectation.setStatus(AffectationStatus.BENEVOLE);
//            affectation.setStructure();

            affectationDAO.persist(affectation, context.getUser());
        }

        return beanMapper.map(contact, ContactDTO.class);
    }

}
