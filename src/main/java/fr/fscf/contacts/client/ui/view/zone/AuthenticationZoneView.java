package fr.fscf.contacts.client.ui.view.zone;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import fr.fscf.contacts.client.ui.presenter.zone.AuthenticationZonePresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import org.gwtbootstrap3.client.ui.AnchorButton;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.ListDropDown;
import org.gwtbootstrap3.client.ui.constants.Toggle;

/**
 * Authentication zone view (not a real view, just a widgets set).
 *
 * @author Denis
 */
public class AuthenticationZoneView extends AbstractView implements AuthenticationZonePresenter.View {

    /**
     * {@link UiBinder} interface adapted to {@link AuthenticationZoneView}.
     */
    @UiTemplate("AuthenticationZoneView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, AuthenticationZoneView> {
    }

    @UiField
    protected AnchorButton usernameLabel;

    @UiField
    protected ListDropDown authenticatedMenu;

    @UiField
    protected AnchorListItem loginLink;

    @UiField
    protected AnchorListItem logoutLink;

    @UiField
    protected AnchorListItem email;

    @Override
    public void initialize() {
        final ViewUiBinder binder = GWT.create(ViewUiBinder.class);
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public IsWidget getAuthenticatedMenu() {
        return authenticatedMenu;
    }

    @Override
    public void setUsername(final String username) {
        usernameLabel.setText(username);
        usernameLabel.setDataToggle(Toggle.DROPDOWN);
    }

    @Override
    public AnchorListItem getLoginHandler() {
        return loginLink;
    }

    @Override
    public AnchorListItem getLogoutHandler() {
        return logoutLink;
    }

    @Override
    public AnchorListItem getEmail() {
        return email;
    }
}