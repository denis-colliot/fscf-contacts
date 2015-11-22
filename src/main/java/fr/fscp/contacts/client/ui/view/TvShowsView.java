package fr.fscp.contacts.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscp.contacts.client.ui.presenter.TvShowsPresenter;
import fr.fscp.contacts.client.ui.view.base.AbstractView;

/**
 * TV Shows view.
 *
 * @author Denis
 */
@Singleton
public class TvShowsView extends AbstractView implements TvShowsPresenter.View {

    /**
     * {@link UiBinder} interface adapted to {@link TvShowsView}.
     */
    @UiTemplate("TvShowsView.ui.xml")
    static interface ViewUiBinder extends UiBinder<Widget, TvShowsView> {
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