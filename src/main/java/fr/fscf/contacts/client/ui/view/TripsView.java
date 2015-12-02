package fr.fscf.contacts.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.ui.presenter.TripsPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import fr.fscf.contacts.client.ui.widget.button.Button;

/**
 * Trips view.
 *
 * @author Denis
 */
@Singleton
public class TripsView extends AbstractView implements TripsPresenter.View {

    @UiField
    Button blueButton;

    @UiField
    Button orangeButton;

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

        blueButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Window.alert("Click");
            }
        });

        orangeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                blueButton.setLoading(!blueButton.isLoading());
                blueButton.setText(blueButton.isLoading() ? "Loading..." : "Not loading");
            }
        });
    }

}