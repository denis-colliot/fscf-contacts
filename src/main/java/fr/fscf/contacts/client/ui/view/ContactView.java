package fr.fscf.contacts.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.i18n.I18N;
import fr.fscf.contacts.client.ui.presenter.ContactPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import fr.fscf.contacts.client.ui.widget.button.Button;
import fr.fscf.contacts.shared.dto.ContactDTO;
import fr.fscf.contacts.shared.dto.FunctionDTO;
import fr.fscf.contacts.shared.dto.StructureDTO;
import fr.fscf.contacts.shared.util.ClientUtils;
import org.gwtbootstrap3.client.ui.FormLabel;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.ValueListBox;

import java.io.IOException;

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

    @UiField(provided = true)
    protected ValueListBox<FunctionDTO> function;

    @UiField
    @Ignore
    protected FormLabel detailedFunctionLabel;

    @UiField
    protected Input detailedFunction;

    @UiField
    @Ignore
    protected Heading structureGroupTitle;

    @UiField
    @Ignore
    protected HasVisibility structureFormGroup;

    @UiField
    @Ignore
    protected HasText structureFormLabel;

    @UiField(provided = true)
    protected ValueListBox<StructureDTO> structure;

    @UiField
    protected Button formSubmitButton;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {

        function = new ValueListBox<>(new Renderer<FunctionDTO>() {
            @Override
            public String render(FunctionDTO function) {
                return ClientUtils.defaultIfBlank(function != null ? function.getName() : null, "");
            }

            @Override
            public void render(FunctionDTO function, Appendable appendable) throws IOException {
                appendable.append(render(function));
            }
        }, FunctionDTO.keyProvider());

        structure = new ValueListBox<>(new Renderer<StructureDTO>() {
            @Override
            public String render(StructureDTO structure) {
                return ClientUtils.defaultIfBlank(structure != null ? structure.getName() : null, "");
            }

            @Override
            public void render(StructureDTO structure, Appendable appendable) throws IOException {
                appendable.append(render(structure));
            }
        }, StructureDTO.keyProvider());

        final ViewUiBinder binder = GWT.create(ViewUiBinder.class);
        initWidget(binder.createAndBindUi(this));
        DRIVER.initialize(this);
    }

    @Override
    public void onViewRevealed() {
        name.setFocus(true);
    }

    @Override
    public SimpleBeanEditorDriver<ContactDTO, ?> getDriver() {
        return DRIVER;
    }

    @Override
    public Button getFormSubmitButton() {
        return formSubmitButton;
    }

    @Override
    public void setDetailedFunctionMandatory(boolean mandatory) {
        detailedFunctionLabel.setShowRequiredIndicator(mandatory);
        if (mandatory) {
            detailedFunction.setFocus(true);
        } else {
            detailedFunction.reset();
        }
    }

    @Override
    public void setStructureView(StructureViewMode mode) {
        structureGroupTitle.setVisible(mode != StructureViewMode.NONE);
        structureFormGroup.setVisible(mode != StructureViewMode.NONE);

        switch (mode) {
            case STRUCTURE:
                structureGroupTitle.setText(I18N.CONSTANTS.contact_edit_form_section_structure());
                structureFormLabel.setText(I18N.CONSTANTS.contact_edit_form_structure());
                break;
            case ASSOCIATION:
                structureGroupTitle.setText(I18N.CONSTANTS.contact_edit_form_section_association());
                structureFormLabel.setText(I18N.CONSTANTS.contact_edit_form_association());
                break;
            case BOTH:
                final String title = I18N.CONSTANTS.contact_edit_form_section_structure() + " / "
                        + I18N.CONSTANTS.contact_edit_form_section_association();
                final String label = I18N.CONSTANTS.contact_edit_form_structure() + " / "
                        + I18N.CONSTANTS.contact_edit_form_association();
                structureGroupTitle.setText(title);
                structureFormLabel.setText(label);
                break;
        }
    }

    @Override
    public HasConstrainedValue<FunctionDTO> getFunction() {
        return function;
    }

    @Override
    public HasConstrainedValue<StructureDTO> getStructure() {
        return structure;
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