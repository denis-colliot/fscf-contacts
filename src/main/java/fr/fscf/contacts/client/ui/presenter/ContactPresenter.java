package fr.fscf.contacts.client.ui.presenter;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.inject.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.ContactView;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;

import javax.inject.Inject;

/**
 * Contact presenter.
 *
 * @author Denis
 */
public class ContactPresenter extends AbstractPagePresenter<ContactPresenter.View> {

    /**
     * View interface.
     */
    @ImplementedBy(ContactView.class)
    public interface View extends ViewInterface {

    }

    @Inject
    protected ContactPresenter(final View view, final Injector injector) {
        super(view, injector);
    }

    @Override
    public Page getPage() {
        return Page.CONTACT;
    }

    @Override
    public void onPageRequest(final PageRequest request) {
        // TODO
    }
}
