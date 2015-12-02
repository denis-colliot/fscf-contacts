package fr.fscf.contacts.server.dispatch;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.security.SecureDispatchAsync;
import fr.fscf.contacts.client.security.SecureDispatchService;
import fr.fscf.contacts.server.security.SecureSessionValidator;
import fr.fscf.contacts.shared.command.result.base.Result;
import fr.fscf.contacts.shared.dispatch.CommandException;
import fr.fscf.contacts.shared.security.InvalidSessionException;
import fr.fscf.contacts.shared.security.UnauthorizedAccessException;
import fr.fscf.contacts.server.model.User;
import fr.fscf.contacts.server.servlet.util.Servlets;
import fr.fscf.contacts.shared.command.base.Command;
import fr.fscf.contacts.shared.dispatch.DispatchException;
import fr.fscf.contacts.shared.dispatch.FunctionalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolationException;

/**
 * Abstract secure dispatch servlet.
 *
 * @author Denis
 */
@Singleton
public class SecureDispatchServlet extends RemoteServiceServlet implements SecureDispatchService {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 8811128792493692516L;

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SecureDispatchServlet.class);

    /**
     * Injected {@link SecureSessionValidator} instance.
     */
    private final SecureSessionValidator sessionValidator;

    /**
     * Injected server-side {@link Dispatch} instance.
     */
    private final Dispatch dispatch;

    /**
     * Initializes the {@code SecureDispatchServlet} that uses injected arguments.
     *
     * @param sessionValidator
     *         The secure session validator service.
     * @param dispatch
     *         The dispatch service.
     */
    @Inject
    public SecureDispatchServlet(final SecureSessionValidator sessionValidator, final Dispatch dispatch) {
        this.sessionValidator = sessionValidator;
        this.dispatch = dispatch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void log(final String msg) {
        this.log(msg, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void log(final String message, final Throwable t) {
        if (t != null) {
            if (LOG.isErrorEnabled()) {
                LOG.error(message, t);
            }
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug(message);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <C extends Command<R>, R extends Result> R execute(final SecureDispatchAsync.CommandExecution<C, R> commandExecution) throws DispatchException {

        if (LOG.isTraceEnabled()) {
            LOG.trace("Executing dispatch command.");
        }

        if (sessionValidator == null) {
            throw new CommandException("No session validator found for servlet '" + getServletName() + "'. Please verify your server-side configuration.");
        }

        if (dispatch == null) {
            throw new CommandException("No dispatch found for servlet '" + getServletName() + "'. Please verify your server-side configuration.");
        }

        User user = null;
        final String authToken = commandExecution.getAuthenticationToken();

        // Roles validation case.
        try {

            // Validates the user session and user access.
            final SecureSessionValidator.Access access = sessionValidator.validate(authToken, commandExecution);
            user = access.getUser();

            switch (access.getAccessType()) {

                case INVALID_SESSION:

                    if (LOG.isDebugEnabled()) {
                        LOG.debug("COMMAND EXECUTION FAILED - Command execution: '{}' ; User: '{}' ; Error: Invalid auth token '{}'.", commandExecution,
                                Servlets.logUser(user), authToken);
                    }

                    throw new InvalidSessionException();

                case UNAUTHORIZED_ACCESS:

                    if (LOG.isDebugEnabled()) {
                        LOG
                                .debug("COMMAND EXECUTION FAILED - Command execution: '{}' ; User: '{}' ; Error: Unauthorized access.", commandExecution, Servlets.logUser(user));
                    }

                    throw new UnauthorizedAccessException();

                default:

                    // Access granted.
                    if (LOG.isTraceEnabled()) {
                        LOG.trace("COMMAND EXECUTION GRANTED - Command execution: '{}' ; User: '{}'.", commandExecution, Servlets.logUser(user));
                    }

                    // Command execution.
                    return dispatch.execute(commandExecution, user, getThreadLocalRequest());
            }

        } catch (final FunctionalException e) {

            // Functional exception.
            if (LOG.isWarnEnabled()) {
                LOG.warn(
                        "COMMAND EXECUTION ABORTED: A functional exception has been raised - Command execution: '"
                                + commandExecution
                                + "' ; User: '"
                                + Servlets.logUser(user)
                                + "'.", e);
            }

            throw e;

        } catch (final CommandException e) {

            // Command execution exception.
            if (LOG.isErrorEnabled()) {
                LOG.error("COMMAND EXECUTION FAILED - Command execution: '" + commandExecution + "' ; User: '" + Servlets.logUser(user) + "'.", e);
            }

            throw e;

        } catch (final ConstraintViolationException e) {

            // Bean validation failed.
            if (LOG.isErrorEnabled()) {
                LOG.error("COMMAND EXECUTION FAILED - Command execution: '"
                        + commandExecution
                        + "' ; User: '"
                        + Servlets.logUser(user)
                        + "' ; Error: A bean validation failed while executing '"
                        + commandExecution.getCommand()
                        + "'. Consider performing the validation on client-side.\n"
                        + Servlets.logConstraints(e.getConstraintViolations()), e);
            }

            throw new CommandException("A bean validation failed while executing '" + commandExecution.getCommand() + "'.", e);

        } catch (final Throwable e) {

            // Server unknown error.
            if (LOG.isErrorEnabled()) {
                LOG.error("COMMAND EXECUTION FAILED - Command execution: '"
                        + commandExecution
                        + "' ; User: '"
                        + Servlets.logUser(user)
                        + "' ; RuntimeException while executing.", e);
            }

            throw new CommandException("Server error.", e);
        }
    }

}