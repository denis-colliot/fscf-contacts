package fr.fscf.contacts.client.ui.widget.button;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import java.util.HashMap;

/**
 * Specific handler registration implementation for {@code Button} and {@code Link} widgets.
 *
 * @author Denis
 */
final class ButtonHandlerRegistration implements HandlerRegistration {

    /**
     * Map storing button/link handlers with their registration.
     *
     * @see ButtonHandlerRegistration
     */
    private final HashMap<ButtonHandlerRegistration, ClickHandler> handlers;

    /**
     * The inner handler registration.
     */
    private HandlerRegistration innerHandlerRegistration;

    /**
     * Initializes a new {@code ButtonHandlerRegistration} with the given {@code handlerRegistration}.
     *
     * @param handlers
     *         The handler registrations map reference.
     * @param handlerRegistration
     *         The handler registration.
     */
    ButtonHandlerRegistration(final HashMap<ButtonHandlerRegistration, ClickHandler> handlers, final HandlerRegistration handlerRegistration) {
        this.handlers = handlers;
        setHandlerRegistration(handlerRegistration);
    }

    /**
     * Sets the inner handler registration with the give {@code handlerRegistration}.
     *
     * @param handlerRegistration
     *         The new inner handler registration.
     */
    void setHandlerRegistration(final HandlerRegistration handlerRegistration) {
        this.innerHandlerRegistration = handlerRegistration;
    }

    /**
     * Returns the inner handler registration.
     *
     * @return the inner handler registration.
     */
    HandlerRegistration getInnerHandlerRegistration() {
        return innerHandlerRegistration;
    }

    /**
     * {@inheritDoc}<br/>
     * Also removes the current instance from internal {@link #handlers} map.
     */
    @Override
    public void removeHandler() {
        if (innerHandlerRegistration == null) {
            return;
        }
        innerHandlerRegistration.removeHandler();
        handlers.remove(this);
    }

}