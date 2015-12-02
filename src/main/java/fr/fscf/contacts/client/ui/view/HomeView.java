package fr.fscf.contacts.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import fr.fscf.contacts.client.ui.presenter.HomePresenter;

/**
 * Home view.
 *
 * @author Denis
 */
public class HomeView extends AbstractView implements HomePresenter.View {

    /**
     * {@link UiBinder} interface adapted to {@link HomeView}.
     */
    @UiTemplate("HomeView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, HomeView> {
    }

    @Override
    public void initialize() {
        final ViewUiBinder binder = GWT.create(ViewUiBinder.class);
        initWidget(binder.createAndBindUi(this));
    }
}
