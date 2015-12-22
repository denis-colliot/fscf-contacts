package fr.fscf.contacts.client.ui.presenter;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.config.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.LoginView;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;

import javax.inject.Inject;

/**
 * Login presenter.
 *
 * @author Denis
 */
public class LoginPresenter extends AbstractPagePresenter<LoginPresenter.View> {

    /**
     * Home view.
     */
    @ImplementedBy(LoginView.class)
    public interface View extends ViewInterface {

    }

    @Inject
    protected LoginPresenter(final View view, final Injector injector) {
        super(view, injector);
    }

    @Override
    public Page getPage() {
        return Page.LOGIN;
    }

    @Override
    public void onPageRequest(final PageRequest request) {
        // TODO
    }
}
