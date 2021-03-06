package fr.fscf.contacts.server.dispatch;

import fr.fscf.contacts.shared.command.result.base.Result;
import fr.fscf.contacts.client.security.SecureDispatchAsync;
import fr.fscf.contacts.server.model.User;
import fr.fscf.contacts.shared.command.base.Command;
import fr.fscf.contacts.shared.dispatch.DispatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * Executes commands and returns the results.
 *
 * @author Denis
 */
public interface Dispatch {

    /**
     * Executes the specified {@code command} and returns the appropriate result.
     *
     * @param <C>
     *         The command type.
     * @param <R>
     *         The {@link Result} type returned by {@code command} execution.
     * @param commandExecution
     *         The command execution (containing {@link Command} to execute).
     * @param user
     *         The user executing the command.
     * @param request
     *         The servlet HTTP request.
     * @return The command's result.
     * @throws DispatchException
     *         If the command execution failed.
     */
    <C extends Command<R>, R extends Result> R execute(final SecureDispatchAsync.CommandExecution<C, R> commandExecution, final User user,
                                                       final HttpServletRequest request) throws DispatchException;

}