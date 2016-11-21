package fr.fscf.contacts.client.ui.presenter;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.dispatch.CommandResultHandler;
import fr.fscf.contacts.client.inject.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.navigation.RequestParameter;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.ContactView;
import fr.fscf.contacts.client.ui.view.base.IsBeanEditor;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.shared.command.GetContactCommand;
import fr.fscf.contacts.shared.dto.ContactDTO;

import javax.inject.Inject;

/**
 * Contact presenter.
 *
 * @author Denis
 */
public class ContactPresenter extends AbstractPagePresenter<ContactPresenter.View> {

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
        dispatch.execute(new GetContactCommand(request.getParameterLong(RequestParameter.ID)),
                new CommandResultHandler<ContactDTO>() {
                    @Override
                    protected void onCommandSuccess(ContactDTO result) {
                        view.getDriver().edit(result);
                    }
                });
    }

    /**
     * View interface.
     */
    @ImplementedBy(ContactView.class)
    public interface View extends ViewInterface, IsBeanEditor<ContactDTO> {

    }
}
