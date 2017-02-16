package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.dao.FederationDAO;
import fr.fscf.contacts.server.dao.FunctionDAO;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.server.mapper.BeanMapper;
import fr.fscf.contacts.server.model.Function;
import fr.fscf.contacts.server.model.FunctionStructureType;
import fr.fscf.contacts.server.model.Structure;
import fr.fscf.contacts.shared.command.GetStructuresCommand;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.shared.dto.FunctionDTO;
import fr.fscf.contacts.shared.dto.StructureDTO;
import fr.fscf.contacts.shared.dto.referential.StructureType;
import org.apache.commons.collections4.CollectionUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Handler for {@link GetStructuresCommand}.
 *
 * @author Denis
 */
public class GetStructuresHandler extends AbstractCommandHandler<GetStructuresCommand, ListResult<StructureDTO>> {

    @Inject
    private FederationDAO federationDAO;

    @Inject
    private FunctionDAO functionDAO;

    @Inject
    private BeanMapper beanMapper;

    @Override
    protected ListResult<StructureDTO> execute(final GetStructuresCommand command,
                                               final UserDispatch.UserExecutionContext context) throws CommandException {

        final Function function = functionDAO.findById(command.getFunctionId());

        final List<StructureType> structureTypes = function.getStructureTypes().stream()
                .map(FunctionStructureType::getStructureType)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        final List<Structure> structures;
        if (CollectionUtils.isNotEmpty(structureTypes)) {
            structures = federationDAO.findUserAllStructures(context.getUser(), structureTypes);
        } else {
            structures = federationDAO.findUserAllStructures(context.getUser());
        }

        return new ListResult<>(beanMapper.mapCollection(structures, StructureDTO.class));
    }

}
