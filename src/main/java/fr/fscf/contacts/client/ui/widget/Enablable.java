package fr.fscf.contacts.client.ui.widget;

/**
 * An element which can be set in a enabled state.
 *
 * @author Denis
 */
public interface Enablable {

    /**
     * Set the button enabled state.
     *
     * @param enabled
     *         The new enabled state.
     */
    void setEnabled(boolean enabled);

    /**
     * Returns the button enabled state.
     *
     * @return The button enabled state.
     */
    boolean isEnabled();

}