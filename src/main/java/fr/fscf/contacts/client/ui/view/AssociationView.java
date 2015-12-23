package fr.fscf.contacts.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.ui.presenter.AssociationPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import fr.fscf.contacts.shared.dto.AssociationDTO;
import org.gwtbootstrap3.client.ui.Input;

/**
 * Assocation view.
 *
 * @author Denis
 */
@Singleton
public class AssociationView extends AbstractView implements AssociationPresenter.View {

    /**
     * {@link UiBinder} interface adapted to {@link AssociationView}.
     */
    @UiTemplate("AssociationView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, AssociationView> {
    }

    public interface AssociationDriver extends SimpleBeanEditorDriver<AssociationDTO, AssociationView> {
    }

    private static final AssociationDriver DRIVER = GWT.create(AssociationDriver.class);

    @UiField
    protected Input name;

    @UiField
    @Ignore
    protected Input address;

    @UiField
    @Ignore
    protected Input zipCode;

    @UiField
    @Ignore
    protected HasClickHandlers formSubmitButton;

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
    public AssociationDriver getDriver() {
        return DRIVER;
    }

    @Override
    public HasClickHandlers getFormSubmitButton() {
        return formSubmitButton;
    }
}