package fr.fscp.contacts.client.ui.presenter;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import fr.fscp.contacts.client.config.Injector;
import fr.fscp.contacts.client.navigation.Page;
import fr.fscp.contacts.client.navigation.PageRequest;
import fr.fscp.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscp.contacts.client.ui.view.TripsView;
import fr.fscp.contacts.client.ui.view.base.ViewInterface;

/**
 * Trips presenter.
 *
 * @author Denis
 */
public class TripsPresenter extends AbstractPagePresenter<TripsPresenter.View> {

    /**
     * View interface.
     */
    @ImplementedBy(TripsView.class)
    public static interface View extends ViewInterface {

    }

    @Inject
    protected TripsPresenter(final View view, final Injector injector) {
        super(view, injector);
    }

    @Override
    public Page getPage() {
        return Page.TRIPS;
    }

    @Override
    public void onPageRequest(final PageRequest request) {
        // TODO
    }
}
