package fr.fscf.contacts.client.ui.presenter;

import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import fr.fscf.contacts.client.config.Injector;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.TripsView;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;

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
    public interface View extends ViewInterface {

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
