package fr.fscf.contacts.client.util;

import com.google.gwt.view.client.HasData;

public interface HasRefresh<T> {

    /**
     * Called when a display wants to refresh.
     *
     * @param display The display to refresh.
     */
    void refresh(HasData<T> display);

}