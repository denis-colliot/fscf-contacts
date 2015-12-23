package fr.fscf.contacts.client.ui.widget.button;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import fr.fscf.contacts.client.ui.widget.Enablable;
import fr.fscf.contacts.client.ui.widget.Loadable;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Loadable} button implementation.
 *
 * @author Denis
 */
public class Button implements IsWidget, Loadable, Enablable, HasClickHandlers {

    /**
     * Inner button widget.
     */
    private final org.gwtbootstrap3.client.ui.Button innerButton;

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
        innerButton = new org.gwtbootstrap3.client.ui.Button();
        initialEnabledState = innerButton.isEnabled();
        handlers = new HashMap<>();
    }

    public Button(final String text, final ClickHandler handler) {
        innerButton = new org.gwtbootstrap3.client.ui.Button(text);
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
            innerButton.state().loading();
            setHandlersEnabled(false);

        } else if (this.loading && !loading) {
            innerButton.setEnabled(initialEnabledState);
            innerButton.state().reset();
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

    @Override
    public boolean isEnabled() {
        return innerButton.isEnabled();
    }

    @Override
    public void setEnabled(boolean enabled) {
        innerButton.setEnabled(enabled);
        initialEnabledState = enabled;
        setHandlersEnabled(enabled);
    }

    public void setText(String text) {
        innerButton.setText(text);
    }

    public void setBackgroundColor(String backgroundColor) {
        innerButton.setColor(backgroundColor);
    }

    public void setType(ButtonType type) {
        innerButton.setType(type);
    }

    public void setIcon(IconType icon) {
        innerButton.setIcon(icon);
    }

}