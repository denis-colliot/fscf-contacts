package fr.fscf.contacts.client.ui.presenter;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.dispatch.CommandResultHandler;
import fr.fscf.contacts.client.inject.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.navigation.RequestParameter;
import fr.fscf.contacts.client.ui.notification.N10N;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.ContactPreviewView;
import fr.fscf.contacts.client.ui.view.ContactView;
import fr.fscf.contacts.client.ui.view.base.IsBeanEditor;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.shared.command.GetContactCommand;
import fr.fscf.contacts.shared.dto.ContactDTO;

import javax.inject.Inject;

/**
 * Contact preview presenter.
 *
 * @author Denis
 */
public class ContactPreviewPresenter extends AbstractPagePresenter<ContactPreviewPresenter.View> {

    @Inject
    protected ContactPreviewPresenter(final View view, final Injector injector) {
        super(view, injector);
    }

    @Override
    public Page getPage() {
        return Page.CONTACT;
    }

    @Override
    public void onBind() {
        // Nothing to bind here.
    }

    @Override
    public void onPageRequest(final PageRequest request) {

        final Long contactId = request.getParameterLong(RequestParameter.ID);

        dispatch.execute(new GetContactCommand(contactId), new CommandResultHandler<ContactDTO>() {
            @Override
            protected void onCommandSuccess(ContactDTO result) {
                if (result == null) {
                    N10N.warn("Le contact #" + contactId + " n'existe pas."); // TODO i18n
                    eventBus.navigate(null);
                    return;
                }
                view.getDriver().edit(result);
            }
        });
    }

    /**
     * View interface.
     */
    @ImplementedBy(ContactPreviewView.class)
    public interface View extends ViewInterface {

    }
}
