package fr.fscp.contacts.client.config;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import fr.fscp.contacts.client.dispatch.DispatchAsync;
import fr.fscp.contacts.client.event.bus.EventBus;
import fr.fscp.contacts.client.navigation.PageManager;
import fr.fscp.contacts.client.security.AuthenticationProvider;
import fr.fscp.contacts.client.ui.presenter.*;

/**
 * GIN injector.
 *
 * @author Denis
 */
@GinModules(value = {
        ClientModule.class
})
public interface Injector extends Ginjector {

    EventBus getEventBus();
    DispatchAsync getDispatch();
    PageManager getPageManager();
    AuthenticationProvider getAuthenticationProvider();

    ApplicationPresenter getApplicationPresenter();
    LoginPresenter getLoginPresenter();
    HomePresenter getHomePresenter();

    TripsPresenter getTripsPresenter();
    TvShowsPresenter getTvShowsPresenter();

}