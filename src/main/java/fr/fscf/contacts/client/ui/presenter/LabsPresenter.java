package fr.fscf.contacts.client.ui.presenter;

import com.google.gwt.user.client.Window;
import com.google.inject.ImplementedBy;
import fr.fscf.contacts.client.inject.Injector;
import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.client.navigation.PageRequest;
import fr.fscf.contacts.client.ui.presenter.base.AbstractPagePresenter;
import fr.fscf.contacts.client.ui.view.LabsView;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;
import fr.fscf.contacts.client.ui.widget.button.Button;

import javax.inject.Inject;

/**
 * Lab presenter used to test UI features.
 *
 * @author Denis
 */
public class LabsPresenter extends AbstractPagePresenter<LabsPresenter.View> {

    /**
     * View interface.
     */
    @ImplementedBy(LabsView.class)
    public interface View extends ViewInterface {

        Button getBlueButton();

        Button getOrangeButton();

    }

    @Inject
    protected LabsPresenter(final View view, final Injector injector) {
        super(view, injector);
    }

    @Override
    public Page getPage() {
        return Page.LABS;
    }

    @Override
    public void onBind() {

        view.getBlueButton().addClickHandler(event -> Window.alert("Click"));

        view.getOrangeButton().addClickHandler(event -> {
            view.getBlueButton().setEnabled(!view.getBlueButton().isEnabled());
            view.getBlueButton().setText(view.getBlueButton().isEnabled() ? "Enabled..." : "Not Enabled");
        });
    }

    @Override
    public void onPageRequest(final PageRequest request) {
        // TODO
    }
}
