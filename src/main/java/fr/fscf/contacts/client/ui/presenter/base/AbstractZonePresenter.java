package fr.fscf.contacts.client.ui.presenter.base;

import com.allen_sauer.gwt.log.client.Log;
import fr.fscf.contacts.client.inject.Injector;
import fr.fscf.contacts.client.event.zone.ZoneRequestEvent;
import fr.fscf.contacts.client.event.zone.ZoneRequestHandler;
import fr.fscf.contacts.client.navigation.Zone;
import fr.fscf.contacts.client.ui.view.base.ViewInterface;

import javax.inject.Inject;

/**
 * Abstract zone presenter.
 *
 * @param <V>
 *         view interface extending the {@link ViewInterface} interface
 * @author Denis
 */
public abstract class AbstractZonePresenter<V extends ViewInterface> extends AbstractPresenter<V> implements Presenter.ZonePresenter<V> {

    /**
     * Default abstract zone presenter constructor.<br>
     * Executes {@link #bind()} method in order to register the {@link ZoneRequestEvent} to the current presenter.
     *
     * @param view
     *         View interface associated to the zone presenter.
     * @param injector
     *         Application injector.
     */
    @Inject
    public AbstractZonePresenter(final V view, final Injector injector) {
        super(view, injector); // Executes 'bind()' method.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    final public void bind() {

        view.initialize();
        onBind();

        final Zone zone = getZone();

        if (zone == null) {
            return;
        }

        // Registers ZoneRequestEvent listener.
        registerHandler(eventBus.addHandler(ZoneRequestEvent.getType(), new ZoneRequestHandler() {

            @Override
            public void onZoneRequest(final ZoneRequestEvent event) {

                if (!event.concern(zone)) {
                    return;
                }

                if (Log.isTraceEnabled()) {
                    Log.trace("Executing '" + zone + "' onZoneRequest() method.");
                }

                AbstractZonePresenter.this.onZoneRequest(event.getZoneRequest());
            }
        }));
    }

}