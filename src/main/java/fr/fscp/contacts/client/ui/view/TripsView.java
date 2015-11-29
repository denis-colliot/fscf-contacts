package fr.fscp.contacts.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscp.contacts.client.ui.presenter.TripsPresenter;
import fr.fscp.contacts.client.ui.view.base.AbstractView;

/**
 * Trips view.
 *
 * @author Denis
 */
@Singleton
public class TripsView extends AbstractView implements TripsPresenter.View {

    /**
     * {@link UiBinder} interface adapted to {@link TripsView}.
     */
    @UiTemplate("TripsView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, TripsView> {
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