package fr.fscf.contacts.client.ui.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.dispatch.CommandResultHandler;
import fr.fscf.contacts.client.inject.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.navigation.RequestParameter;
import fr.fscf.contacts.client.ui.notification.N10N;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.ContactView;
import fr.fscf.contacts.client.ui.view.base.IsBeanEditor;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.client.ui.widget.button.Button;
import fr.fscf.contacts.shared.command.GetContactCommand;
import fr.fscf.contacts.shared.command.SaveContactCommand;
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
    public void onBind() {
        view.getFormSubmitButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onSubmit();
            }
        });
    }

    @Override
    public void onPageRequest(final PageRequest request) {

        final Long contactId = request.getParameterLong(RequestParameter.ID);

        if (contactId == null) {
            view.getDriver().edit(new ContactDTO());
            return;
        }

        dispatch.execute(new GetContactCommand(contactId),
                new CommandResultHandler<ContactDTO>() {
                    @Override
                    protected void onCommandSuccess(ContactDTO result) {
                        view.getDriver().edit(result);
                    }
                });
    }

    private void onSubmit() {
        if (!validator.validate(view)) {
            return;
        }

        final ContactDTO contactDTO = view.getDriver().flush();

        dispatch.execute(new SaveContactCommand(contactDTO), new CommandResultHandler<ContactDTO>() {
            @Override
            protected void onCommandSuccess(ContactDTO result) {
                N10N.validNotif("Le contact &laquo; " + result + " &raquo; a bien été enregistré");
                eventBus.navigate(Page.CONTACTS);
            }
        }, view.getFormSubmitButton());
    }

    /**
     * View interface.
     */
    @ImplementedBy(ContactView.class)
    public interface View extends ViewInterface, IsBeanEditor<ContactDTO> {

        Button getFormSubmitButton();

    }
}
