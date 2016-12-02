package fr.fscf.contacts.client.util;

import com.google.gwt.view.client.HasData;

/**
 * Custom {@link com.google.gwt.view.client.AsyncDataProvider} providing {@link #refresh(HasData)} method.
 *
 * @author Denis
 */
public abstract class AsyncDataProvider<T> extends com.google.gwt.view.client.AsyncDataProvider<T>
        implements HasRefresh<T> {

    @Override
    public void refresh(HasData<T> display) {
        onRangeChanged(display);
    }

}
