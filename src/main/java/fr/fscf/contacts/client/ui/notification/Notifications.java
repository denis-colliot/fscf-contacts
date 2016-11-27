package fr.fscf.contacts.client.ui.notification;

import fr.fscf.contacts.client.util.MessageType;
import org.gwtbootstrap3.extras.notify.client.constants.NotifyType;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;

/**
 * Displays messages into a notification tray.
 *
 * @author Denis
 */
final class Notifications {

    private Notifications() {
        // Provides only static methods.
    }

    /**
     * Shows the given message into a new notification widget. The notifications widget are graphically stacked.
     *
     * @param title
     *         The title.
     * @param html
     *         The message.
     * @param type
     *         The message's type.
     */
    public static void show(final String title, final String html, final MessageType type) {
        Notify.notify(title, html, getNotifyType(type));
    }

    private static NotifyType getNotifyType(MessageType type) {

        if (type == null) {
            type = MessageType.DEFAULT;
        }

        switch (type) {
            case ERROR:
                return NotifyType.DANGER;
            case VALID:
                return NotifyType.SUCCESS;
            case WARNING:
                return NotifyType.WARNING;
            case INFO:
                return NotifyType.INFO;
            case QUESTION:
                return NotifyType.INFO;
            default:
                throw new IllegalArgumentException("Unsupported message type: " + type);
        }
    }

}