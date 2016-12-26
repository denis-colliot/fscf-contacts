package fr.fscf.contacts.client.ui.presenter;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.dispatch.CommandResultHandler;
import fr.fscf.contacts.client.inject.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.LoginView;
import fr.fscf.contacts.client.ui.view.base.IsBeanEditor;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.client.ui.widget.button.Button;
import fr.fscf.contacts.shared.command.AuthenticateCommand;
import fr.fscf.contacts.shared.command.result.Authentication;
import fr.fscf.contacts.shared.dto.LoginDTO;
import org.gwtbootstrap3.client.ui.Form;

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
    public interface View extends ViewInterface, IsBeanEditor<LoginDTO> {

        Form getLoginForm();

        Button getLoginButton();

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
    public void onBind() {
        view.getLoginForm().addSubmitHandler(event -> {
            final NativeEvent clickEvent = Document.get().createClickEvent(0, 0, 0, 0, 0, false, false, false, false);
            DomEvent.fireNativeEvent(clickEvent, view.getLoginButton());
        });

        view.getLoginButton().addClickHandler(event -> onFormSubmit());
    }

    @Override
    public void onPageRequest(final PageRequest request) {
        view.getDriver().edit(new LoginDTO());
    }

    private void onFormSubmit() {
        if (!validator.validate(view)) {
            return;
        }
        final LoginDTO dto = view.getDriver().flush();
        dispatch.execute(new AuthenticateCommand(dto), new CommandResultHandler<Authentication>() {
            @Override
            protected void onCommandSuccess(Authentication result) {
                injector.getAuthenticationProvider().login(result);
                eventBus.navigate(null, view.getLoginButton());
            }
        }, view.getLoginButton());
    }
}
