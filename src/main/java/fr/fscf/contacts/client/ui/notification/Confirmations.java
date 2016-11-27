package fr.fscf.contacts.client.ui.notification;

import fr.fscf.contacts.client.util.MessageType;
import fr.fscf.contacts.shared.util.ClientUtils;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.bootbox.client.options.BootboxSize;
import org.gwtbootstrap3.extras.bootbox.client.options.ConfirmOptions;

/**
 * Displays confirmation message (with yes/no buttons) in a modal popup.
 *
 * @author Denis
 */
final class Confirmations {

    private Confirmations() {
        // Provides only static methods.
    }

    /**
     * Shows the given message into the popup.<br/>
     * <br/>
     * There is only one instance of the popup, the previous message may be erased.
     *
     * @param title
     *         The title.
     * @param html
     *         The message.
     * @param yesCallback
     *         The callback for the yes action.
     * @param noCallback
     *         The callback for the no action.
     */
    static void show(final String title, final String html, final ConfirmCallback yesCallback, final ConfirmCallback noCallback) {

        final ConfirmOptions options = ConfirmOptions.newOptions(html);
        options.setSize(BootboxSize.SMALL);
        options.setAnimate(false);
        options.setBackdrop(false);
        options.setTitle(ClientUtils.isNotBlank(title) ? title : MessageType.getTitle(MessageType.QUESTION));
        options.setCloseButton(false);

        options.setCallback(new org.gwtbootstrap3.extras.bootbox.client.callback.ConfirmCallback() {
            @Override
            public void callback(final boolean result) {
                if (result) {
                    if (yesCallback != null) {
                        yesCallback.onAction();
                    }
                } else {
                    if (noCallback != null) {
                        noCallback.onAction();
                    }
                }
            }
        });

        Bootbox.confirm(options);
    }

}