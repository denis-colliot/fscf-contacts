package fr.fscp.contacts.client.ui.presenter;

import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import fr.fscp.contacts.client.config.Injector;
import fr.fscp.contacts.client.navigation.Page;
import fr.fscp.contacts.client.navigation.PageRequest;
import fr.fscp.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscp.contacts.client.ui.view.TvShowsView;
import fr.fscp.contacts.client.ui.view.base.ViewInterface;

/**
 * TV Shows presenter.
 *
 * @author Denis
 */
public class TvShowsPresenter extends AbstractPagePresenter<TvShowsPresenter.View> {

    /**
     * View interface.
     */
    @ImplementedBy(TvShowsView.class)
    public static interface View extends ViewInterface {

    }

    @Inject
    protected TvShowsPresenter(final View view, final Injector injector) {
        super(view, injector);
    }

    @Override
    public Page getPage() {
        return Page.TV_SHOWS;
    }

    @Override
    public void onPageRequest(final PageRequest request) {
        // TODO
    }
}
