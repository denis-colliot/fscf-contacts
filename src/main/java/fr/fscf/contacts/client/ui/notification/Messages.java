package fr.fscf.contacts.client.ui.notification;

import com.google.gwt.user.client.ui.HTML;
import fr.fscf.contacts.client.util.MessageType;
import fr.fscf.contacts.shared.util.ClientUtils;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.gwtbootstrap3.client.ui.constants.ModalBackdrop;

/**
 * Displays messages into a modal popup.
 *
 * @author Denis
 */
final class Messages {

    private Messages() {
        // Provides only static methods.
    }

    // Initialize the alert widget.
    private static final Modal modal;

    private static final ModalBody modalBody;

    static {

        modal = new Modal();
        modal.setDataBackdrop(ModalBackdrop.STATIC);
        modal.setDataKeyboard(true);
        modal.setFade(true);
        modal.setClosable(true);

        modalBody = new ModalBody();
        modal.add(modalBody);

    }

    /**
     * Clears the current message.
     */
    private static void clear() {
        modal.setTitle(null);
        modalBody.clear();
        modal.hide();
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
     * @param type
     *         The message's type.
     */
    static void show(final String title, final String html, MessageType type) {

        clear();

        modal.setTitle(ClientUtils.isNotBlank(title) ? title : MessageType.getTitle(type));
        modalBody.add(new HTML(html));

        type = type != null ? type : MessageType.DEFAULT;
        switch (type) {
            case ERROR:
                modal.setColor("red");
                break;
            case INFO:
                modal.setColor("blue");
                break;
            case WARNING:
                modal.setColor("orange");
                break;
            case VALID:
                modal.setColor("green");
                break;
            case QUESTION:
                modal.setColor("blue");
                break;
        }

        modal.show();
    }

}