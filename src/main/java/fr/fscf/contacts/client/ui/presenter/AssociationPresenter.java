package fr.fscf.contacts.client.ui.presenter;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.config.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.AssociationView;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;

import javax.inject.Inject;

/**
 * Association presenter.
 *
 * @author Denis
 */
public class AssociationPresenter extends AbstractPagePresenter<AssociationPresenter.View> {

    /**
     * View interface.
     */
    @ImplementedBy(AssociationView.class)
    public interface View extends ViewInterface {

    }

    @Inject
    protected AssociationPresenter(final View view, final Injector injector) {
        super(view, injector);
    }

    @Override
    public Page getPage() {
        return Page.ASSOCIATION;
    }

    @Override
    public void onPageRequest(final PageRequest request) {
        // TODO
    }
}
