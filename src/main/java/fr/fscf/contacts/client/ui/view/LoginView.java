package fr.fscf.contacts.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import fr.fscf.contacts.client.ui.presenter.LoginPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import fr.fscf.contacts.client.ui.widget.button.Button;
import fr.fscf.contacts.shared.dto.LoginDTO;
import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.Input;

/**
 * Login view.
 *
 * @author Denis
 */
public class LoginView extends AbstractView implements LoginPresenter.View {

    /**
     * {@link UiBinder} interface adapted to {@link AssociationView}.
     */
    @UiTemplate("LoginView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, LoginView> {
    }

    public interface LoginDriver extends SimpleBeanEditorDriver<LoginDTO, LoginView> {
    }

    private static final LoginDriver DRIVER = GWT.create(LoginDriver.class);

    @UiField
    protected Input login;

    @UiField
    protected Input password;

    @UiField
    @Ignore
    protected Form form;

    @UiField
    @Ignore
    protected Button loginButton;

    @Override
    public void initialize() {
        final ViewUiBinder binder = GWT.create(ViewUiBinder.class);
        initWidget(binder.createAndBindUi(this));
        DRIVER.initialize(this);
    }

    @Override
    public LoginDriver getDriver() {
        return DRIVER;
    }

    @Override
    public Button getLoginButton() {
        return loginButton;
    }

    @Override
    public Form getLoginForm() {
        return form;
    }

    @Override
    public void onViewRevealed() {
        login.setFocus(true);
    }
}
