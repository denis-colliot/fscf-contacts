package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.dao.AffectationDAO;
import fr.fscf.contacts.server.dao.ContactDAO;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.server.mapper.BeanMapper;
import fr.fscf.contacts.server.model.Affectation;
import fr.fscf.contacts.server.model.Contact;
import fr.fscf.contacts.shared.command.GetContactCommand;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.shared.dto.ContactDTO;
import fr.fscf.contacts.shared.dto.FunctionDTO;
import fr.fscf.contacts.shared.dto.StructureDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Handler for {@link GetContactCommand}.
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

    @Inject
    private AffectationDAO affectationDAO;

    @Inject
    private BeanMapper beanMapper;

    @Override
    protected ContactDTO execute(final GetContactCommand command,
                                 final UserDispatch.UserExecutionContext context) throws CommandException {

        final Long contactId = command.getContactId();

        LOGGER.info("About to retrieve contact with id {}", contactId);

        final Optional<Contact> contact = contactDAO.findUserContact(context.getUser(), contactId);

        final List<Affectation> affectations = affectationDAO.findContactAffectations(contactId);

        LOGGER.info("Contact with id {} has {} affectation(s) ; using first one", contactId, affectations.size());

        final Optional<Affectation> affectation = affectations.stream().findFirst();

        final FunctionDTO functionDTO = affectation
                .map(Affectation::getFunction)
                .map(aff -> beanMapper.map(aff, FunctionDTO.class))
                .orElse(null);

        final StructureDTO structureDTO = affectation
                .map(Affectation::getStructure)
                .map(aff -> beanMapper.map(aff, StructureDTO.class))
                .orElse(null);

        return contact
                .map(c -> {
                    final ContactDTO contactDTO = beanMapper.map(c, ContactDTO.class);
                    contactDTO.setFunction(functionDTO);
                    contactDTO.setStructure(structureDTO);
                    return contactDTO;
                })
                .orElse(null);
    }

}
