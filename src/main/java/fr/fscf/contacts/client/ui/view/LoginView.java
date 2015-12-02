package fr.fscf.contacts.client.ui.view;

import com.google.gwt.user.client.ui.HTML;
import fr.fscf.contacts.client.ui.presenter.LoginPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;

/**
 * Login view.
 *
 * @author Denis
 */
public class LoginView extends AbstractView implements LoginPresenter.View {

    @Override
    public void initialize() {
        initWidget(new HTML("Login view"));
    }
}
