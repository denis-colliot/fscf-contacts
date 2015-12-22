package fr.fscf.contacts.client.ui.widget.button;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import fr.fscf.contacts.client.ui.widget.Loadable;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.ui.MaterialButton;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Loadable} button implementation.
 *
 * @author Denis
 */
public class Button implements IsWidget, Loadable, HasClickHandlers {

    /**
     * Inner button widget.
     */
    private final MaterialButton innerButton;

    /**
     * Map storing button handlers with their registration.
     */
    private final Map<HandlerRegistration, ClickHandler> handlers;

    /**
     * Loading state of the button.
     */
    private boolean loading;

    /**
     * Initial enabled state of the button.
     */
    private boolean initialEnabledState;

    /**
     * Creates a new button.
     */
    public Button() {
        innerButton = new MaterialButton(ButtonType.RAISED);
        initialEnabledState = innerButton.isEnabled();
        handlers = new HashMap<>();
    }

    public Button(final String text, final ClickHandler handler) {
        innerButton = new MaterialButton(ButtonType.RAISED, text, null);
        initialEnabledState = innerButton.isEnabled();
        handlers = new HashMap<>();
        if (handler != null) {
            addClickHandler(handler);
        }
    }

    private void setHandlersEnabled(final boolean enabled) {
        if (enabled) {
            for (final ClickHandler handler : handlers.values()) {
                innerButton.addClickHandler(handler);
            }
        } else {
            for (final HandlerRegistration registration : handlers.keySet()) {
                registration.removeHandler();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return innerButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoading(final boolean loading) {

        if (!this.loading && loading) {
            innerButton.setEnabled(false);
            // TODO replaceIcon(IconImageBundle.ICONS.loading());
            setHandlersEnabled(false);

        } else if (this.loading && !loading) {
            innerButton.setEnabled(initialEnabledState);
            // TODO setIcon(getIcon());
            setHandlersEnabled(initialEnabledState);
        }

        this.loading = loading;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLoading() {
        return loading;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        final HandlerRegistration registration = innerButton.addClickHandler(handler);
        handlers.put(registration, handler);
        if (!isEnabled()) {
            registration.removeHandler();
        }
        return registration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fireEvent(final GwtEvent<?> event) {
        innerButton.fireEvent(event);
    }

    public boolean isEnabled() {
        return innerButton.isEnabled();
    }

    public void setEnabled(boolean enabled) {
        innerButton.setEnabled(enabled);
        initialEnabledState = enabled;
        setHandlersEnabled(enabled);
    }

    public String getText() {
        return innerButton.getText();
    }

    public void setText(String text) {
        innerButton.setText(text);
    }

    public String getBackgroundColor() {
        return innerButton.getBackgroundColor();
    }

    public void setBackgroundColor(String backgroundColor) {
        innerButton.setBackgroundColor(backgroundColor);
    }

}