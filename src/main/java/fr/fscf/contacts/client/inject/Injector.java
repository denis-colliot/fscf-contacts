package fr.fscf.contacts.client.inject;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import fr.fscf.contacts.client.event.bus.EventBus;
import fr.fscf.contacts.client.navigation.PageManager;
import fr.fscf.contacts.client.ui.presenter.*;
import fr.fscf.contacts.client.dispatch.DispatchAsync;
import fr.fscf.contacts.client.security.AuthenticationProvider;
import fr.fscf.contacts.client.ui.presenter.zone.AuthenticationZonePresenter;
import fr.fscf.contacts.client.validation.Validator;

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
    Validator getValidator();
    AuthenticationProvider getAuthenticationProvider();

    AuthenticationZonePresenter getAuthenticationZonePresenter();

    ApplicationPresenter getApplicationPresenter();
    LoginPresenter getLoginPresenter();
    HomePresenter getHomePresenter();
    LabsPresenter getLabsPresenter();

    ContactsPresenter getContactsPresenter();
    ContactPresenter getContactPresenter();
    AssociationPresenter getAssociationPresenter();

}