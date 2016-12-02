package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.dao.FederationDAO;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.server.mapper.BeanMapper;
import fr.fscf.contacts.server.model.Structure;
import fr.fscf.contacts.shared.command.GetStructuresCommand;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.shared.dto.StructureDTO;

import javax.inject.Inject;
import java.util.List;

/**
 * Handler for {@link GetStructuresCommand}.
 *
 * @author Denis
 */
public class GetStructuresHandler extends AbstractCommandHandler<GetStructuresCommand, ListResult<StructureDTO>> {

    @Inject
    private FederationDAO federationDAO;

    @Inject
    private BeanMapper beanMapper;

    @Override
    protected ListResult<StructureDTO> execute(final GetStructuresCommand command,
                                               final UserDispatch.UserExecutionContext context) throws CommandException {

        final List<Structure> structures = federationDAO.findUserAllStructures(context.getUser());

        return new ListResult<>(beanMapper.mapCollection(structures, StructureDTO.class));
    }

}
