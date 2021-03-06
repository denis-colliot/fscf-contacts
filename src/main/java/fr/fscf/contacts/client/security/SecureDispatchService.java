package fr.fscf.contacts.client.security;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import fr.fscf.contacts.shared.command.result.base.Result;
import fr.fscf.contacts.shared.command.base.Command;
import fr.fscf.contacts.shared.dispatch.DispatchException;

/**
 * <p>
 * Secure dispatch service.
 * </p>
 * <p>
 * This service is used to execute {@link Command}s on server-side through {@code RPC} protocol.
 * </p>
 *
 * @author Denis
 * @see SecureDispatchServiceAsync
 */
@RemoteServiceRelativePath(SecureDispatchService.REMOTE_SERVICE_RELATIVE_PATH)
public interface SecureDispatchService extends RemoteService {

    /**
     * Remote service relative path.
     */
    static final String REMOTE_SERVICE_RELATIVE_PATH = "dispatch";

    /**
     * Executes the given {@code commandExecution}.
     *
     * @param commandExecution
     *         The command excution wrapper (containing command to execute, auth token, etc.).
     * @param <C>
     *         Command type.
     * @param <R>
     *         Result type.
     * @return the command execution result.
     * @throws DispatchException
     *         If the command execution fails.
     */
    <C extends Command<R>, R extends Result> R execute(final SecureDispatchAsync.CommandExecution<C, R> commandExecution) throws DispatchException;

}