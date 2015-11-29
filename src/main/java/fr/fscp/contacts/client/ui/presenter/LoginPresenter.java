package fr.fscp.contacts.client.ui.presenter;

import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import fr.fscp.contacts.client.config.Injector;
import fr.fscp.contacts.client.navigation.Page;
import fr.fscp.contacts.client.navigation.PageRequest;
import fr.fscp.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscp.contacts.client.ui.view.LoginView;
import fr.fscp.contacts.client.ui.view.base.ViewInterface;

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
