package fr.fscf.contacts.client.dispatch;

import com.google.gwt.user.client.rpc.AsyncCallback;
import fr.fscf.contacts.shared.command.base.Command;
import fr.fscf.contacts.shared.command.result.base.Result;
import fr.fscf.contacts.shared.dispatch.FunctionalException;
import fr.fscf.contacts.client.ui.notification.N10N;
import fr.fscf.contacts.shared.command.result.VoidResult;

/**
 * Abstract class handling {@link Command} execution callback.
 *
 * @param <R> The command result type.
 * @author Denis
 */
public abstract class CommandResultHandler<R extends Result> implements AsyncCallback<R> {

    /**
     * A default void result instance.
     */
    public static final CommandResultHandler<VoidResult> Void = voidResult();

    /**
     * Builds a new empty result handler instance.<br>
     * Can be cast to the appropriate type if necessary.
     */
    public static <T extends Result> CommandResultHandler<T> voidResult() {
        return new CommandResultHandler<T>() {

            @Override
            public void onCommandSuccess(final T result) {

            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void onSuccess(final R result) {
        onCommandSuccess(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void onFailure(final Throwable caught) {

        if (caught instanceof FunctionalException) {
            // Functional exception.
            onFunctionalException((FunctionalException) caught);

        } else {
            onCommandFailure(caught);
        }
    }

    // --------------------------------------------------------------------------------
    //
    // OVERRIDABLE METHODS.
    //
    // --------------------------------------------------------------------------------

    /**
     * Callback executed on command execution success.
     *
     * @param result The command execution result.
     */
    protected abstract void onCommandSuccess(final R result);

    /**
     * Callback executed if server-side process throws an exception.<br>
     * <em>Default implementation simply throws a {@link RuntimeException}.</em>
     *
     * @param caught The exception.
     */
    protected void onCommandFailure(final Throwable caught) {
        // Default behaviour that can be overrided by child implementation.
        throw new RuntimeException(caught);
    }

    /**
     * Method called when the server throws a <b>functional</b> exception.<br>
     * The default implementation displays a <b>warning</b> message.
     *
     * @param exception The functional exception (cannot be {@code null}).
     * @see FunctionalException
     */
    protected void onFunctionalException(final FunctionalException exception) {
        // Default implementation displays a warning message.
        N10N.warn(exception.getTitle(), exception.getMessage());
    }

}