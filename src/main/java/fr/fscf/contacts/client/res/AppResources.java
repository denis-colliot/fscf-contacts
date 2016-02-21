package fr.fscf.contacts.client.res;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Application resources.
 *
 * @author Denis
 */
public interface AppResources extends ClientBundle {

    AppResources INSTANCE = GWT.create(AppResources.class);

    @Source("logo.png")
    ImageResource appLogo();

}