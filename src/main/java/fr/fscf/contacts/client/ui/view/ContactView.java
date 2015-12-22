package fr.fscf.contacts.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.ui.presenter.ContactPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;

/**
 * Contact view.
 *
 * @author Denis
 */
@Singleton
public class ContactView extends AbstractView implements ContactPresenter.View {

    /**
     * {@link UiBinder} interface adapted to {@link ContactView}.
     */
    @UiTemplate("ContactView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, ContactView> {
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