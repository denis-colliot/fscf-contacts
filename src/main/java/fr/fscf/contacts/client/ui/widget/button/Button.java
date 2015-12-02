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
     *
     * @see ButtonHandlerRegistration
     */
    private final HashMap<ButtonHandlerRegistration, ClickHandler> handlers;

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

            for (final ButtonHandlerRegistration registration : handlers.keySet()) {
                registration.getInnerHandlerRegistration().removeHandler();
            }

        } else if (this.loading && !loading) {
            innerButton.setEnabled(initialEnabledState);
            // TODO setIcon(getIcon());

            for (final Map.Entry<ButtonHandlerRegistration, ClickHandler> entry : handlers.entrySet()) {
                final ButtonHandlerRegistration handlerRegistration = entry.getKey();
                final ClickHandler handler = entry.getValue();

                // Updates handler registration.
                handlerRegistration.setHandlerRegistration(innerButton.addClickHandler(handler));
            }
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
    public ButtonHandlerRegistration addClickHandler(final ClickHandler handler) {
        final HandlerRegistration registration;
        if (isEnabled()) {
            // Activates handler if button is enabled.
            registration = innerButton.addClickHandler(handler);

        } else {
            // Does not activate handler if button is disabled (it will be activated when button is enabled).
            registration = null;
        }
        final ButtonHandlerRegistration buttonHandlerRegistration = new ButtonHandlerRegistration(handlers, registration);
        handlers.put(buttonHandlerRegistration, handler);
        return buttonHandlerRegistration;
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