package fr.fscf.contacts.client.ui.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.ui.presenter.ContactPreviewPresenter;
import fr.fscf.contacts.client.ui.view.base.AbstractPopupView;
import fr.fscf.contacts.client.ui.widget.popup.IsPopupWidget;

/**
 * Contact preview popup view.
 *
 * @author Denis
 */
@Singleton
public class ContactPreviewView extends AbstractPopupView<> implements ContactPreviewPresenter.View {

    protected ContactPreviewView(final IsPopupWidget popup) {
        super(popup);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        initPopup(this);
    }

    /**
     * {@link UiBinder} interface adapted to {@link ContactPreviewView}.
     */
    @UiTemplate("ContactView.ui.xml")
    interface ViewUiBinder extends UiBinder<Widget, ContactPreviewView> {
    }
}