package fr.fscf.contacts.client.event.bus;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Singleton;
import fr.fscf.contacts.client.inject.Injector;
import fr.fscf.contacts.client.dispatch.CommandResultHandler;
import fr.fscf.contacts.client.event.page.PageRequestEvent;
import fr.fscf.contacts.client.event.zone.ZoneRequestEvent;
import fr.fscf.contacts.client.i18n.I18N;
import fr.fscf.contacts.client.navigation.*;
import fr.fscf.contacts.client.ui.notification.N10N;
import fr.fscf.contacts.client.ui.presenter.base.Presenter;
import fr.fscf.contacts.client.ui.widget.Loadable;
import fr.fscf.contacts.shared.command.SecureNavigationCommand;
import fr.fscf.contacts.shared.command.result.SecureNavigationResult;

import javax.inject.Inject;

/**
 * Application event bus implementation.
 *
 * @author Denis
 */
@Singleton
public class EventBusImpl extends HandlerManager implements EventBus {

    /**
     * Callback interface used to handle {@link PageRequestEvent} dispatch action.
     *
     * @author Denis
     */
    private interface PageRequestEventCallback {

        /**
         * Method called once the dispatch action is complete.
         *
         * @param event
         *         The returned event.
         */
        void onPageRequestEventComplete(final GwtEvent<?> event);

    }

    /**
     * Injected application injector.
     */
    private final Injector injector;

    /**
     * Last failed accessed {@link PageRequest} that will be reload on next authentication.
     */
    private PageRequest failedAccessedPageRequest;

    /**
     * Optional {@link Loadable}s to set in {@code loading} mode during page access.
     */
    private Loadable[] loadables;

    /**
     * Application event bus initialization.
     *
     * @param injector
     *         The application injector.
     */
    @Inject
    public EventBusImpl(final Injector injector) {

        super(null);

        this.injector = injector;
        this.failedAccessedPageRequest = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout() {

        // Clears the session.
        injector.getAuthenticationProvider().logout();

        // Navigates to default page.
        navigate(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fireEvent(final GwtEvent<?> event) {

        if (!(event instanceof PageRequestEvent)) {
            // Other (or null) events.
            handleEvent(event);
            return;
        }

        // Page request event.

        // Before leaving the current presenter.
        final Presenter<?> presenter = injector.getPageManager().getCurrentPresenter();
        updateZoneRequest(Zone.APP_LOADER.requestWith(RequestParameter.REQUEST, true));

        if (presenter == null) {
            handleEvent(event);
            return;
        }

        final PageRequest pageRequest = ((PageRequestEvent) event).getRequest();
        final Page page = pageRequest != null ? pageRequest.getPage() : null;

        if (injector.getPageManager().isPopupView(page)) {
            // Popup case : no 'before leaving' action.
            // TODO [BEFORE LEAVING] Associer le 'beforeLeaving' des popup à la fermeture des popups.
            handleEvent(event);

        } else {
            // Page change : 'before leaving' action launched.

            presenter.beforeLeaving(new LeavingCallback() {

                @Override
                public void leavingOk() {
                    handleEvent(event);
                }

                @Override
                public void leavingKo() {
                    handleEvent(null);
                    updateZoneRequest(Zone.APP_LOADER.requestWith(RequestParameter.REQUEST, false));
                }

            });
        }
    }

    /**
     * Handlers the given {@code event}.
     *
     * @param event
     *         The event. If the event is a {@link PageRequestEvent}, a command is sent to the server in order to
     *         retrieve user access rights.
     */
    private void handleEvent(final GwtEvent<?> event) {

        if (!(event instanceof PageRequestEvent)) {
            // Other (or null) events.
            if (event != null) {
                super.fireEvent(event);
            }
            return;
        }

        // Page request event.
        handlePageRequestEvent((PageRequestEvent) event, new PageRequestEventCallback() {

            @Override
            public void onPageRequestEventComplete(final GwtEvent<?> event) {

                updateZoneRequest(Zone.APP_LOADER.requestWith(RequestParameter.REQUEST, false));

                if (event == null) {
                    navigate(null, loadables);
                    return;
                }

                EventBusImpl.super.fireEvent(event);
            }
        });
    }

    /**
     * Executes a command to retrieve access rights for the current [authenticated/anonymous] user.
     *
     * @param event
     *         The {@link PageRequestEvent} event.
     * @param callback
     *         The {@link PageRequestEventCallback} instance executed once event has been handled (i.e. on command
     *         callback execution).
     */
    private void handlePageRequestEvent(final PageRequestEvent event, final PageRequestEventCallback callback) {

        final Page page = event.getRequest().getPage();

        if (Log.isTraceEnabled()) {
            Log.trace("User is attempting to access page '" + page + "'.");
        }

        // Determining page token to access.
        final PageRequestEvent accessedPageEvent;

        if (page != null && injector.getPageManager().getPage(page.getToken()) != null) {
            // Page is valid and registered with PageManager.
            accessedPageEvent = event;

        } else if (injector.getAuthenticationProvider().isAnonymous()) {
            // Page is invalid and user anonymous: redirecting user to default anonymous page.
            accessedPageEvent = new PageRequestEvent(Page.DEFAULT_ANONYMOUS_PAGE);

        } else {
            // Page is invalid and user authenticated: redirecting user to default home page.
            accessedPageEvent = new PageRequestEvent(Page.DEFAULT_AUTHENTICATED_PAGE);
        }

        // Executing command securing the navigation event.
        injector.getDispatch().execute(new SecureNavigationCommand(accessedPageEvent.getRequest().getPage()), new CommandResultHandler<SecureNavigationResult>() {

            @Override
            protected void onCommandSuccess(final SecureNavigationResult result) {

                final boolean wasAuthenticated = !injector.getAuthenticationProvider().isAnonymous();

                Log.debug("Secure navigation result: " + result);

                // Sets the authentication.
                injector.getAuthenticationProvider().updateCache(result.getAuthentication());

                if (result.isGranted()) {
                    // Page access is granted.
                    callback.onPageRequestEventComplete(accessedPageEvent);

                } else {
                    // Unauthorized page access.

                    if (event.isFromHistory()) {
                        // Directly from URL.
                        N10N.errorNotif(I18N.CONSTANTS.navigation_unauthorized_access());
                        callback.onPageRequestEventComplete(null);

                    } else {
                        // From application link.
                        N10N.error(I18N.CONSTANTS.navigation_unauthorized_access());

                        if (wasAuthenticated && injector.getAuthenticationProvider().isAnonymous()) {
                            // User is no longer authenticated (expired session).
                            callback.onPageRequestEventComplete(null);
                        }
                    }
                }
            }

            @Override
            protected void onCommandFailure(final Throwable caught) {
                if (Log.isErrorEnabled()) {
                    Log.error("An unexpected error occurred during 'SecureNavigationCommand' execution.", caught);
                }
                N10N.error(I18N.CONSTANTS.navigation_error());
            }

        }, loadables);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void navigate(final Page page, final Loadable... loadables) {

        final PageRequest pageRequest;

        // If a requested page (not a popup one) has been recorded, we redirect the logged in user towards its previous
        // request.
        if (page == null && failedAccessedPageRequest != null && !injector.getPageManager().isPopupView(failedAccessedPageRequest.getPage())) {

            pageRequest = failedAccessedPageRequest;
            failedAccessedPageRequest = null;
        }
        // Default case.
        else {
            pageRequest = new PageRequest(page);
        }

        navigateRequest(pageRequest, loadables);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void navigateRequest(final PageRequest request, final Loadable... loadables) {
        this.loadables = loadables;
        this.fireEvent(new PageRequestEvent(request));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateZone(final Zone zone) {
        updateZoneRequest(new ZoneRequest(zone));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateZoneRequest(final ZoneRequest zoneRequest) {
        this.fireEvent(new ZoneRequestEvent(zoneRequest));
    }

}