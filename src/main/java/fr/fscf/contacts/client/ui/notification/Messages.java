package fr.fscf.contacts.client.ui.notification;

import com.google.gwt.user.client.ui.SimplePanel;
import fr.fscf.contacts.client.event.ClosePopupEvent;
import fr.fscf.contacts.client.event.ClosePopupHandler;
import fr.fscf.contacts.client.ui.widget.popup.IsPopupWidget;
import fr.fscf.contacts.client.ui.widget.popup.PopupWidget;
import fr.fscf.contacts.shared.util.ClientUtils;
import fr.fscf.contacts.client.util.MessageType;

/**
 * Displays messages into a modal popup.
 *
 * @author Denis
 */
final class Messages {

    private Messages() {
        // Provides only static methods.
    }

    // CSS.
    private static final String CSS_POPUP = "notification";

    // Initialize the popup widget.
    private static final IsPopupWidget popup;

    private static boolean visible;

    static {

        popup = new PopupWidget(true, false);
        popup.setContent(new SimplePanel()); // not used.
        popup.addStyleName(CSS_POPUP);

        visible = false;
        popup.setClosePopupHandler(new ClosePopupHandler() {

            @Override
            public void onClosePopup(ClosePopupEvent event) {
                visible = false;
            }

        });

    }

    /**
     * Clears the current message.
     */
    private static void clear() {
        popup.setPageMessage(null, null);
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

        popup.setTitle(ClientUtils.isNotBlank(title) ? title : MessageType.getTitle(type));
        popup.setPageMessage(html, type);

        if (!visible) {
            popup.center();
            visible = true;
        }
    }

}