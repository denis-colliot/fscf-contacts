package fr.fscf.contacts.client.ui.presenter;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.config.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.AssociationView;
import fr.fscf.contacts.client.ui.view.base.IsBeanEditor;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.shared.dto.AssociationDTO;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;

/**
 * Association presenter.
 *
 * @author Denis
 */
public class AssociationPresenter extends AbstractPagePresenter<AssociationPresenter.View> {

    /**
     * View interface.
     */
    @ImplementedBy(AssociationView.class)
    public interface View extends ViewInterface, IsBeanEditor<AssociationDTO> {

        HasClickHandlers getFormSubmitButton();

    }

    private final Validator validator;

    @Inject
    protected AssociationPresenter(final View view, final Injector injector, final Validator validator) {
        super(view, injector);
        this.validator = validator;
    }

    @Override
    public Page getPage() {
        return Page.ASSOCIATION;
    }

    @Override
    public void onBind() {
        view.getFormSubmitButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final AssociationDTO dto = view.getDriver().flush();
                final Set<ConstraintViolation<AssociationDTO>> violations = validator.validate(dto);

                Log.info("Violations: " + violations);
                Log.info("Has errors: " + view.getDriver().hasErrors());

                view.getDriver().setConstraintViolations(new ArrayList<ConstraintViolation<?>>(violations));
            }
        });
    }

    @Override
    public void onPageRequest(final PageRequest request) {
        // TODO
        AssociationDTO a = new AssociationDTO();
        a.setName("Toto");
        view.getDriver().edit(a);
    }
}
