package fr.fscf.contacts.client.ui.presenter;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.config.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.ContactsView;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;

import javax.inject.Inject;

/**
 * Contacts presenter.
 *
 * @author Denis
 */
public class ContactsPresenter extends AbstractPagePresenter<ContactsPresenter.View> {

    /**
     * View interface.
     */
    @ImplementedBy(ContactsView.class)
    public interface View extends ViewInterface {

    }

    @Inject
    protected ContactsPresenter(final View view, final Injector injector) {
        super(view, injector);
    }

    @Override
    public Page getPage() {
        return Page.CONTACTS;
    }

    @Override
    public void onPageRequest(final PageRequest request) {
        // TODO
    }
}
