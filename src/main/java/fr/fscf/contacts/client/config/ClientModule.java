package fr.fscf.contacts.client.config;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.dispatch.DispatchAsync;
import fr.fscf.contacts.client.dispatch.ExceptionHandler;
import fr.fscf.contacts.client.event.bus.EventBus;
import fr.fscf.contacts.client.event.bus.EventBusImpl;
import fr.fscf.contacts.client.navigation.PageManager;
import fr.fscf.contacts.client.security.SecureDispatchAsync;
import fr.fscf.contacts.client.security.SecureExceptionHandler;

/**
 * GIN module to bind presenters and views.
 *
 * @author Denis
 */
public class ClientModule extends AbstractGinModule {

    @Override
    protected void configure() {

        // Navigation.
        bind(EventBus.class).to(EventBusImpl.class).in(Singleton.class);
        bind(PageManager.class).in(Singleton.class);

        // Dispatch & security.
        bind(ExceptionHandler.class).to(SecureExceptionHandler.class).in(Singleton.class);
        bind(DispatchAsync.class).to(SecureDispatchAsync.class).in(Singleton.class);

        // Presenters rely on "@ImplementedBy" annotation on their view interface.
    }

}