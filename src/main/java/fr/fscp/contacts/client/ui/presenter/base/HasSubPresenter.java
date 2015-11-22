package fr.fscp.contacts.client.ui.presenter.base;

import fr.fscp.contacts.client.navigation.PageRequest;
import fr.fscp.contacts.client.ui.view.base.HasSubView;

/**
 * Interface implemented by presenters managing one or several sub-presenters.
 *
 * @param <V>
 *         Parent presenter's view interface type.
 * @author Denis
 */
public interface HasSubPresenter<V extends HasSubView> extends Presenter<V> {

    /**
     * Interface implemented by all sub-presenters managed by a parent presenter.
     *
     * @param <P>
     *         Parent presenter type.
     * @author Denis
     */
    public static interface SubPresenter<P extends HasSubPresenter<? extends HasSubView>> {

        /**
         * Returns the parent presenter managing the current presenter.
         *
         * @return The parent presenter managing the current presenter.
         */
        P getParentPresenter();

    }

    /**
     * Method executed each time the parent presenter is <em>requested</em> through one of its sub-presenters.
     *
     * @param subPageRequest
     *         The sub-presenter page request.
     */
    void onSubPresenterRequest(PageRequest subPageRequest);

}