package fr.fscf.contacts.client.ui.view.base;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import fr.fscf.contacts.client.event.ClosePopupHandler;
import fr.fscf.contacts.client.ui.widget.Loadable;
import fr.fscf.contacts.client.ui.widget.popup.IsPopupWidget;
import fr.fscf.contacts.client.util.MessageType;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.gwtbootstrap3.client.ui.ModalComponent;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract popup view.<br/>
 * <br/>
 * Because UiBinder does not allow view inheritance (without heavy cost), popup views cannot inherit abstract popup
 * layer. Thus they have to use a specific popup widget.<br/>
 * That's why {@link AbstractPopupView} does not inherit {@link Composite} element.
 *
 * @author Denis
 */
public abstract class AbstractPopupView implements ViewPopupInterface {

    private static class CustomModal extends Modal implements IsPopupWidget {

        @Override
        public void setPageMessageVisible(boolean visible) {

        }

        @Override
        public void setPageMessage(String html, MessageType type) {

        }

        @Override
        public void setLoading(boolean loading) {

        }

        @Override
        public boolean isLoading() {
            return false;
        }

        @Override
        public void center() {
            super.show();
        }

        @Override
        public void setContent(Widget widget) {
            final ModalBody body = new ModalBody();
            body.add(widget);
            super.add(body);
        }

        @Override
        public void setZIndex(int zIndex) {
        }

        @Override
        public void setClosePopupHandler(ClosePopupHandler handler) {

        }
    }

    /**
     * <p>
     * Visible pop-ups views.
     * </p>
     * <p>
     * Using a {@link Set} ensures pop-up views references uniqueness.<br/>
     * {@link #equals(Object)} and {@link #hashCode()} methods should not be overridden (declared as {@code final}).
     * </p>
     */
    private static final Set<Modal> visiblePopups = new HashSet<>();

    /**
     * Returns if a pop-up view is currently visible.
     *
     * @return {@code true} if a pop-up is currently visible.
     */
    public static boolean isPopupDisplayed() {
        return !visiblePopups.isEmpty();
    }

    /**
     * The pop-up implementation.
     */
    protected final CustomModal popup;

    /**
     * Constructor with dimensions parameters.
     *
     * @param width  The width of the pop-up (ignored if {@code null}).
     * @param height The height of the pop-up (ignored if {@code null}).
     */
    protected AbstractPopupView(final String width, final String height) {

        this.popup = new CustomModal();

        if (width != null) {
            this.popup.setWidth(width);
        }

        if (height != null) {
            this.popup.setHeight(height);
        }

        setCloseHandler(event -> {
            event.closePopup();
            visiblePopups.remove(popup);
        });
    }

    /**
     * Initializes the pop-up widget.
     *
     * @param widget The pop-up widget.
     */
    protected final void initPopup(final Widget widget) {
        popup.setContent(widget);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCloseHandler(final ClosePopupHandler handler) {
        this.popup.setClosePopupHandler(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return popup.asWidget();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Loadable[] getLoadables() {
        // Returns null in default implementation.
        // Can be overridden by sub popup views.
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isFullPage() {
        // Should always return false.
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewRevealed() {
        // Default implementation does nothing.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void center() {
        if (popup != null) {
            popup.center();
            visiblePopups.add(popup);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        if (popup != null) {
            popup.hide();
            visiblePopups.remove(popup);
        }
    }

    public void setStyleName(final String styleName) {
        if (popup != null) {
            popup.setStyleName(styleName);
        }
    }

    public void addStyleName(final String styleName) {
        if (popup != null) {
            popup.addStyleName(styleName);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setPopupTitle(final String title) {
        popup.setTitle(title);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPopupStyleName(final String style) {
        if (popup != null && style != null) {
            popup.addStyleName(style);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePopupStyleName(final String style) {
        if (popup != null && style != null) {
            popup.removeStyleName(style);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPageMessageVisible(boolean visible) {
        popup.setPageMessageVisible(visible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPageMessage(final String html, final MessageType type) {
        popup.setPageMessage(html, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLoading() {
        return popup.isLoading();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoading(boolean loading) {
        popup.setLoading(loading);
    }

}