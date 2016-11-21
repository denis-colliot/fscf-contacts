package fr.fscf.contacts.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.ui.presenter.ContactPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import fr.fscf.contacts.shared.dto.ContactDTO;
import org.gwtbootstrap3.client.ui.Input;

/**
 * Contact view.
 *
 * @author Denis
 */
@Singleton
public class ContactView extends AbstractView implements ContactPresenter.View {

    private static final Driver DRIVER = GWT.create(Driver.class);

    @UiField
    protected Input name;

    @UiField
    protected Input firstName;

    @UiField
    protected Input email;

    @UiField
    protected Input phone;

    @UiField
    protected Input address;

    @UiField
    protected Input additionalAddress;

    @UiField
    protected Input zipCode;

    @UiField
    protected Input city;

    @UiField
    protected HasClickHandlers formSubmitButton;

    @Override
    public SimpleBeanEditorDriver<ContactDTO, ?> getDriver() {
        return DRIVER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        final ViewUiBinder binder = GWT.create(ViewUiBinder.class);
        initWidget(binder.createAndBindUi(this));
        DRIVER.initialize(this);
    }

    @Override
    public HasClickHandlers getFormSubmitButton() {
        return formSubmitButton;
    }

    /**
     * {@link UiBinder} interface adapted to {@link ContactView}.
     */
    @UiTemplate("ContactView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, ContactView> {
    }

    interface Driver extends SimpleBeanEditorDriver<ContactDTO, ContactView> {
    }

}