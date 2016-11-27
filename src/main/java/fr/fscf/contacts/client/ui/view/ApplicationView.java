package fr.fscf.contacts.client.ui.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.navigation.Zone;
import fr.fscf.contacts.client.ui.presenter.ApplicationPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.client.ui.widget.Loadable;
import fr.fscf.contacts.client.util.MessageType;

import javax.inject.Inject;
import java.util.Map;

/**
 * <p>
 * Application frame view.
 * </p>
 * <p>
 * This is the only <em>view</em> that does not inherit {@link AbstractView}.
 * </p>
 *
 * @author Denis
 */
@Singleton
public final class ApplicationView extends AbstractView implements ApplicationPresenter.View {

    /**
     * {@link com.google.gwt.uibinder.client.UiBinder} interface adapted to {@link ApplicationView}.
     */
    @UiTemplate("ApplicationView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    protected HasClickHandlers brand;

    @UiField
    protected HasWidgets.ForIsWidget navbar;

    @UiField
    protected HTML message;

    @UiField
    protected HasOneWidget container;

    @UiField
    protected HasClickHandlers contactsLink;

    @UiField
    protected HasClickHandlers contactLink;

    @UiField
    protected HasClickHandlers associationLink;

    @UiField
    protected HasText footerText;

    /**
     * Instantiates the application frame.
     */
    @Inject
    public ApplicationView(final ViewUiBinder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        RootPanel.get().add(this);
    }

    @Override
    public Widget asWidget() {
        return RootPanel.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        // Not used (everything is initialized in the constructor).
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewRevealed() {
        // Not used.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Loadable[] getLoadables() {
        // Not used.
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFullPage() {
        // Not used.
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideLoadingPanel() {

        final RootPanel panel = RootPanel.get("loading");

        if (panel != null) {
            panel.setVisible(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initZones(final Map<Zone, ViewInterface> zoneViews) {

        // TODO init zones.
        navbar.add(zoneViews.get(Zone.AUTH_BANNER));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showPresenter(final IsWidget presenterWidget, boolean fullPage) {
        container.setWidget(presenterWidget.asWidget());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPageMessageVisible(boolean visible) {
        message.setVisible(visible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPageMessage(String html, MessageType type) {
        message.setHTML(html);
        MessageType.applyStyleName(message, type);
    }

    @Override
    public HasClickHandlers getNavLinkBrand() {
        return brand;
    }

    @Override
    public HasClickHandlers getNavLinkContacts() {
        return contactsLink;
    }

    @Override
    public HasClickHandlers getNavLinkContact() {
        return contactLink;
    }

    @Override
    public HasClickHandlers getNavLinkAssociation() {
        return associationLink;
    }

    @Override
    public HasText getFooterText() {
        return footerText;
    }
}