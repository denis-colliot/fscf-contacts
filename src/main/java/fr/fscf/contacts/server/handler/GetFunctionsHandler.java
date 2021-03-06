package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.dao.FunctionDAO;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.server.mapper.BeanMapper;
import fr.fscf.contacts.server.model.Function;
import fr.fscf.contacts.server.model.FunctionStructureType;
import fr.fscf.contacts.shared.command.GetFunctionsCommand;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.shared.dto.FunctionDTO;
import fr.fscf.contacts.shared.dto.referential.StructureType;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Handler for {@link GetFunctionsCommand}.
 *
 * @author Denis
 */
public class GetFunctionsHandler extends AbstractCommandHandler<GetFunctionsCommand, ListResult<FunctionDTO>> {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveContactHandler.class);

    @Inject
    private FunctionDAO functionDAO;

    @Inject
    private BeanMapper beanMapper;

    @Override
    protected ListResult<FunctionDTO> execute(final GetFunctionsCommand command,
                                              final UserDispatch.UserExecutionContext context) throws CommandException {

        LOGGER.info("Retrieving functions list");

        final List<Function> functions = functionDAO.findAll();

        return new ListResult<>(beanMapper.mapCollection(functions, FunctionDTO.class, (function, functionDTO) -> function.ifPresent(f -> {

            final boolean hasAssociation = Optional.ofNullable(f.getStructureTypes())
                    .map(Collection::stream)
                    .orElse(Stream.empty())
                    .map(FunctionStructureType::getStructureType)
                    .anyMatch(structureType -> structureType == StructureType.ASSOCIATION);

            functionDTO.get().setAssociationFunction(hasAssociation);
        })));
    }

}
