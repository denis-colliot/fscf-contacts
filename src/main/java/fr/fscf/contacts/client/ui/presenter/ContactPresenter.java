package fr.fscf.contacts.client.ui.presenter;

import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.dispatch.CommandResultHandler;
import fr.fscf.contacts.client.dispatch.DispatchQueue;
import fr.fscf.contacts.client.i18n.I18N;
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
import fr.fscf.contacts.shared.command.GetFunctionsCommand;
import fr.fscf.contacts.shared.command.GetStructuresCommand;
import fr.fscf.contacts.shared.command.SaveContactCommand;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.dto.ContactDTO;
import fr.fscf.contacts.shared.dto.ContactDTO.RequiredDetailedFunctionGroup;
import fr.fscf.contacts.shared.dto.FunctionDTO;
import fr.fscf.contacts.shared.dto.StructureDTO;

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
        // Save button click handler.
        view.getFormSubmitButton().addClickHandler(event -> onSubmit());

        // Other function selection.
        view.getFunction().addValueChangeHandler(event -> onFunctionChange());
    }

    @Override
    public void onPageRequest(final PageRequest request) {

        // Clearing form.
        view.getDriver().edit(new ContactDTO());

        new DispatchQueue(dispatch)
                // Populating functions field.
                .add(new GetFunctionsCommand(), new CommandResultHandler<ListResult<FunctionDTO>>() {
                    @Override
                    protected void onCommandSuccess(final ListResult<FunctionDTO> result) {
                        result.getList().add(FunctionDTO.getOther());
                        view.getFunction().setAcceptableValues(result.getList());
                    }
                })
                // Populating structures field.
                .add(new GetStructuresCommand(), new CommandResultHandler<ListResult<StructureDTO>>() {
                    @Override
                    protected void onCommandSuccess(final ListResult<StructureDTO> result) {
                        view.getStructure().setAcceptableValues(result.getList());
                    }
                })
                .start(() -> {
                    // Loading contact data (once previous commands have completed).
                    final Long contactId = request.getParameterLong(RequestParameter.ID);
                    if (contactId != null) {
                        dispatch.execute(new GetContactCommand(contactId), new CommandResultHandler<ContactDTO>() {
                            @Override
                            protected void onCommandSuccess(ContactDTO result) {
                                view.getDriver().edit(result);
                                onFunctionChange();
                            }
                        });
                    }
                });
    }

    private void onSubmit() {
        if (!validator.validate(view, FunctionDTO.isOther(view.getFunction().getValue()) ?
                RequiredDetailedFunctionGroup.class : null)) {
            return;
        }

        final ContactDTO contactDTO = view.getDriver().flush();

        dispatch.execute(new SaveContactCommand(contactDTO), new CommandResultHandler<ContactDTO>() {
            @Override
            protected void onCommandSuccess(ContactDTO result) {
                N10N.validNotif(I18N.MESSAGES.contacts_save_ok(result.toString()));
                eventBus.navigate(Page.CONTACTS);
            }
        }, view.getFormSubmitButton());
    }

    private void onFunctionChange() {
        final FunctionDTO selectedFunction = view.getFunction().getValue();

        view.setDetailedFunctionGroupVisible(FunctionDTO.isOther(selectedFunction));

        // Reloading structures.
        view.setStructureGroupVisible(false);
        dispatch.execute(new GetStructuresCommand(), new CommandResultHandler<ListResult<StructureDTO>>() {
            @Override
            protected void onCommandSuccess(final ListResult<StructureDTO> result) {
                view.getStructure().setAcceptableValues(result.getList());
                view.setStructureGroupVisible(true);
            }
        });
    }


    /**
     * View interface.
     */
    @ImplementedBy(ContactView.class)
    public interface View extends ViewInterface, IsBeanEditor<ContactDTO> {

        Button getFormSubmitButton();

        void setDetailedFunctionGroupVisible(boolean visible);

        void setStructureGroupVisible(boolean visible);

        HasConstrainedValue<FunctionDTO> getFunction();

        HasConstrainedValue<StructureDTO> getStructure();

    }
}
