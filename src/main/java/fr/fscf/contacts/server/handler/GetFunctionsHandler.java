package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.dao.FunctionDAO;
import fr.fscf.contacts.server.dispatch.impl.UserDispatch;
import fr.fscf.contacts.server.handler.base.AbstractCommandHandler;
import fr.fscf.contacts.server.mapper.BeanMapper;
import fr.fscf.contacts.server.model.Function;
import fr.fscf.contacts.shared.command.GetFunctionsCommand;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.shared.dto.FunctionDTO;

import javax.inject.Inject;
import java.util.List;

/**
 * Handler for {@link GetFunctionsCommand}.
 *
 * @author Denis
 */
public class GetFunctionsHandler extends AbstractCommandHandler<GetFunctionsCommand, ListResult<FunctionDTO>> {

    @Inject
    private FunctionDAO functionDAO;

    @Inject
    private BeanMapper beanMapper;

    @Override
    protected ListResult<FunctionDTO> execute(final GetFunctionsCommand command,
                                              final UserDispatch.UserExecutionContext context) throws CommandException {

        final List<Function> functions = functionDAO.findAll();

        return new ListResult<>(beanMapper.mapCollection(functions, FunctionDTO.class));
    }

}
