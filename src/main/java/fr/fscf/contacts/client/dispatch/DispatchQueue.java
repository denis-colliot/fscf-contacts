package fr.fscf.contacts.client.dispatch;

import fr.fscf.contacts.client.security.SecureDispatchAsync;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.client.ui.widget.Loadable;
import fr.fscf.contacts.shared.command.base.Command;
import fr.fscf.contacts.shared.command.result.base.Result;
import fr.fscf.contacts.shared.dispatch.FunctionalException;
import fr.fscf.contacts.shared.util.ClientUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * <p>
 * Dispatch queue.
 * </p>
 * <p>
 * Enables parallel dispatch actions calls in an easy way.
 * </p>
 *
 * @author Denis
 */
public final class DispatchQueue {

    /**
     * Inner private class wrapping a dispatch command execution.
     *
     * @param <C> The dispatch command type.
     * @param <R> The dispatch command result type.
     */
    private class CommandWrapper<C extends Command<R>, R extends Result> {

        private final C command;
        private final CommandResultHandler<R> commandResultHandler;
        private final Collection<Loadable> loadables;

        private CommandWrapper(final C command, final CommandResultHandler<R> commandResultHandler,
                               final Collection<Loadable> loadables) {
            this.command = command;
            this.commandResultHandler = commandResultHandler;
            this.loadables = loadables;
        }

        /**
         * Executes the current wrapped command.
         */
        private void execute() {

            if (dispatch == null) {
                handleExecutionComplete();
                return;
            }

            dispatch.execute(command, new CommandResultHandler<R>() {

                @Override
                protected void onCommandSuccess(final R result) {
                    try {
                        commandResultHandler.onCommandSuccess(result);
                    } finally {
                        handleExecutionComplete();
                    }
                }

                @Override
                protected void onCommandFailure(final Throwable caught) {
                    try {
                        commandResultHandler.onCommandFailure(caught);
                    } finally {
                        handleExecutionComplete();
                    }
                }

                @Override
                protected void onFunctionalException(final FunctionalException exception) {
                    try {
                        commandResultHandler.onFunctionalException(exception);
                    } finally {
                        handleExecutionComplete();
                    }
                }

            }, loadables);
        }
    }

    private final DispatchAsync dispatch;
    private final Collection<CommandWrapper<?, ?>> actions;

    private int actionsCount;
    private boolean running;
    private OnCompleteHandler onCompleteHandler;

    /**
     * Initializes a new {@code DispatchQueue}.
     *
     * @param dispatch The {@code DispatchAsync} service (required).
     */
    public DispatchQueue(final DispatchAsync dispatch) {
        this.dispatch = dispatch;
        this.actions = new ArrayList<>();
    }

    /**
     * Adds a new {@code command} with its {@code commandResultHandler} to the current queue.
     *
     * @param command              The dispatch command.
     * @param commandResultHandler The dispatch command result handler.
     * @param view                 The view referencing {@code Loadable} elements (can be {@code null}).
     * @return the current queue.
     */
    public <C extends Command<R>, R extends Result> DispatchQueue add(final C command,
                                                                      final CommandResultHandler<R> commandResultHandler,
                                                                      final ViewInterface view) {
        return add(command, commandResultHandler, view != null ? view.getLoadables() : null);
    }

    /**
     * Adds a new {@code command} with its {@code commandResultHandler} to the current queue.
     *
     * @param command              The dispatch command.
     * @param commandResultHandler The dispatch command result handler.
     * @param loadables            The {@code Loadable} elements (can be {@code null}).
     * @return the current queue.
     */
    public <C extends Command<R>, R extends Result> DispatchQueue add(final C command,
                                                                      final CommandResultHandler<R> commandResultHandler,
                                                                      final Loadable... loadables) {
        final Collection<Loadable> collection;
        if (ClientUtils.isNotEmpty(loadables)) {
            collection = Arrays.asList(loadables);
        } else {
            collection = null;
        }

        return add(command, commandResultHandler, collection);
    }

    /**
     * Adds a new {@code command} with its {@code commandResultHandler} to the current queue.
     *
     * @param command              The dispatch command.
     * @param commandResultHandler The dispatch command result handler.
     * @param loadables            The {@code Loadable} elements collection (can be {@code null}).
     * @return the current queue.
     */
    public <C extends Command<R>, R extends Result> DispatchQueue add(final C command,
                                                                      final CommandResultHandler<R> commandResultHandler,
                                                                      final Collection<Loadable> loadables) {

        if (command == null || commandResultHandler == null || running) {
            return this;
        }

        actions.add(new CommandWrapper<C, R>(command, commandResultHandler, loadables));
        return this;
    }

    /**
     * Sets up a callback executed once <b>all</b> executed actions are complete (success or error).<br/>
     * This method does nothing once dispatch queue is started.
     *
     * @param onCompleteHandler The on complete handler implementation.
     * @return The current queue.
     */
    public DispatchQueue onCompleteHandler(final OnCompleteHandler onCompleteHandler) {

        if (running) {
            return this;
        }

        this.onCompleteHandler = onCompleteHandler;
        return this;
    }

    /**
     * Starts the queue actions (no new command should be added after this call).
     */
    public void start() {

        if (running) {
            return;
        }

        running = true;
        actionsCount = actions.size();

        if (actionsCount == 0) {
            actionsCount++;
            handleExecutionComplete();
            return;
        }

        for (final CommandWrapper<?, ?> action : actions) {
            action.execute();
        }
    }

    /**
     * Handled command execution complete.
     */
    private void handleExecutionComplete() {
        actionsCount--;

        if (actionsCount != 0) {
            return;
        }

        // "try/finally" in case cutom implementation fails.
        if (onCompleteHandler != null) {
            try {

                onCompleteHandler.onComplete();

            } finally {
                running = false;
            }
        }
    }

    public interface OnCompleteHandler {

        void onComplete();

    }

}