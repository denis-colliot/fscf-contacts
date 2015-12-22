package fr.fscf.contacts.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.ui.presenter.AssociationPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        final ViewUiBinder binder = GWT.create(ViewUiBinder.class);
        initWidget(binder.createAndBindUi(this));
    }

}