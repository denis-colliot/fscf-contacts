package fr.fscf.contacts.client.ui.widget.button;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import fr.fscf.contacts.client.ui.widget.Enablable;
import fr.fscf.contacts.client.ui.widget.Loadable;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Loadable} button implementation.
 *
 * @author Denis
 */
public class Button extends org.gwtbootstrap3.client.ui.Button implements Loadable, Enablable {

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
        super();
        initialEnabledState = super.isEnabled();
        handlers = new HashMap<>();
    }

    public Button(final String text, final ClickHandler handler) {
        super(text);
        initialEnabledState = super.isEnabled();
        handlers = new HashMap<>();
        if (handler != null) {
            addClickHandler(handler);
        }
    }

    private void setHandlersEnabled(final boolean enabled) {
        if (enabled) {
            for (final ClickHandler handler : handlers.values()) {
                super.addClickHandler(handler);
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
    public boolean isLoading() {
        return loading;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoading(final boolean loading) {

        if (!this.loading && loading) {
            super.setEnabled(false);
//            innerButton.state().loading();
            setHandlersEnabled(false);

        } else if (this.loading && !loading) {
            super.setEnabled(initialEnabledState);
//            innerButton.state().reset();
            setHandlersEnabled(initialEnabledState);
        }

        this.loading = loading;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        final HandlerRegistration registration = super.addClickHandler(handler);
        handlers.put(registration, handler);
        if (!isEnabled()) {
            registration.removeHandler();
        }
        return registration;
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        initialEnabledState = enabled;
        setHandlersEnabled(enabled);
    }

}