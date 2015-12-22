package fr.fscf.contacts.server.config.dispatch;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import fr.fscf.contacts.server.dispatch.CommandHandler;
import fr.fscf.contacts.server.dispatch.CommandHandlerRegistry;

import javax.inject.Inject;
import java.util.List;

/**
 * This class links any registered {@link CommandHandler} instances with the default
 * {@link CommandHandlerRegistry}.
 *
 * @author Denis
 */
final class CommandHandlerLinker {

    private CommandHandlerLinker() {
        // Only provides static methods.
    }

    /**
     * Links the command handlers.
     *
     * @param injector
     *         The guice injector.
     * @param registry
     *         The command-handler registry.
     */
    @Inject
    @SuppressWarnings({
            "unchecked",
            "rawtypes"
    })
    static void linkHandlers(final Injector injector, final CommandHandlerRegistry registry) {

        final List<Binding<CommandHandlerMap>> bindings = injector.findBindingsByType(TypeLiteral.get(CommandHandlerMap.class));

        for (final Binding<CommandHandlerMap> binding : bindings) {
            final CommandHandlerMap map = binding.getProvider().get();
            registry.addHandlerClass(map.getCommandClass(), map.getCommandHandlerClass());
        }
    }

}