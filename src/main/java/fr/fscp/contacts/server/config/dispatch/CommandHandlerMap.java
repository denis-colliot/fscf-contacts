package fr.fscp.contacts.server.config.dispatch;

import fr.fscp.contacts.server.dispatch.CommandHandler;
import fr.fscp.contacts.shared.command.base.Command;
import fr.fscp.contacts.shared.command.result.base.Result;

/**
 * Command-Handler map interface.
 *
 * @param <C>
 *         The command type.
 * @param <R>
 *         The command result type.
 * @author Denis
 */
interface CommandHandlerMap<C extends Command<R>, R extends Result> {

    /**
     * Returns the command class.
     *
     * @return the command class.
     */
    Class<C> getCommandClass();

    /**
     * Returns the commandHandler class.
     *
     * @return the commandHandler class.
     */
    Class<? extends CommandHandler<C, R>> getCommandHandlerClass();

}