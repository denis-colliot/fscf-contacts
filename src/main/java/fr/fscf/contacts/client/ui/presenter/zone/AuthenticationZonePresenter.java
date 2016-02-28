package fr.fscf.contacts.client.ui.presenter.zone;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.config.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.Zone;
import fr.fscf.contacts.client.navigation.ZoneRequest;
import fr.fscf.contacts.client.ui.presenter.base.AbstractZonePresenter;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.client.ui.view.zone.AuthenticationZoneView;
import fr.fscf.contacts.shared.command.result.Authentication;
import fr.fscf.contacts.shared.util.ClientUtils;
import org.gwtbootstrap3.client.ui.AnchorListItem;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Authentication banner presenter displaying user's name and logout.
 *
 * @author Denis
 */
@Singleton
public class AuthenticationZonePresenter extends AbstractZonePresenter<AuthenticationZonePresenter.View> {

    /**
     * View interface.
     */
    @ImplementedBy(AuthenticationZoneView.class)
    public interface View extends ViewInterface {

        void setUsername(String username);

        AnchorListItem getLoginHandler();

        IsWidget getAuthenticatedMenu();

        AnchorListItem getLogoutHandler();

        HasText getEmail();

    }

    @Inject
    public AuthenticationZonePresenter(View view, Injector injector) {
        super(view, injector);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Zone getZone() {
        return Zone.AUTH_BANNER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBind() {

        // Login navigation.
        view.getLoginHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                eventBus.navigate(Page.LOGIN);
            }
        });

        // Logout action.
        view.getLogoutHandler().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.logout();
            }

        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onZoneRequest(final ZoneRequest zoneRequest) {

        final boolean anonymous = injector.getAuthenticationProvider().isAnonymous();

        final String name, email;
        if (anonymous) {
            name = null;
            email = null;
        } else {
            final Authentication authentication = injector.getAuthenticationProvider().get();
            name = authentication.getUserFirstName() + ' ' + authentication.getUserName();
            email = authentication.getUserEmail();
        }

        ClientUtils.setDisplay(view.getLoginHandler(), anonymous);
        ClientUtils.setDisplay(view.getAuthenticatedMenu(), !anonymous);

        view.setUsername(name);
        view.getEmail().setText(email);
    }

}