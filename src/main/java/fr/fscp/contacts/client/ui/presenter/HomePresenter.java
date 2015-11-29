package fr.fscp.contacts.client.ui.presenter;

import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import fr.fscp.contacts.client.config.Injector;
import fr.fscp.contacts.client.navigation.Page;
import fr.fscp.contacts.client.navigation.PageRequest;
import fr.fscp.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscp.contacts.client.ui.view.HomeView;
import fr.fscp.contacts.client.ui.view.base.ViewInterface;

/**
 * Home presenter.
 *
 * @author Denis
 */
public class HomePresenter extends AbstractPagePresenter<HomePresenter.View> {

    /**
     * View interface.
     */
    @ImplementedBy(HomeView.class)
    public interface View extends ViewInterface {

    }

    @Inject
    protected HomePresenter(final View view, final Injector injector) {
        super(view, injector);
    }

    @Override
    public Page getPage() {
        return Page.HOME;
    }

    @Override
    public void onPageRequest(final PageRequest request) {
        // TODO
    }
}
