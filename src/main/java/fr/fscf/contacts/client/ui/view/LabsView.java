package fr.fscf.contacts.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.ui.presenter.LabsPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractView;
import fr.fscf.contacts.client.ui.widget.button.Button;

/**
 * Labs view.
 *
 * @author Denis
 */
@Singleton
public class LabsView extends AbstractView implements LabsPresenter.View {

    @UiField
    Button blueButton;

    @UiField
    Button orangeButton;

    /**
     * {@link UiBinder} interface adapted to {@link LabsView}.
     */
    @UiTemplate("LabsView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, LabsView> {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        final ViewUiBinder binder = GWT.create(ViewUiBinder.class);
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public Button getBlueButton() {
        return blueButton;
    }

    @Override
    public Button getOrangeButton() {
        return orangeButton;
    }
}