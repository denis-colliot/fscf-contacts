package fr.fscf.contacts.client.ui.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.inject.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.AssociationView;
import fr.fscf.contacts.client.ui.view.base.IsBeanEditor;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.shared.dto.AssociationDTO;

import javax.inject.Inject;

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

    @Inject
    protected AssociationPresenter(final View view, final Injector injector) {
        super(view, injector);
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
                if (!validator.validate(view)) {
                    return;
                }
                final AssociationDTO dto = view.getDriver().flush();
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
